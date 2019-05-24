package com.wy.shopping.common.service.utils;

import com.wy.shopping.common.service.entity.user.MenuEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author wy
 * @date 2019-05-23
 * @description
 */
@Slf4j
public class PojoUtil {

    /**
     * 判断对象的全部属性是否为空
     *
     * @param object 被检测的对象
     * @param <T>    对象泛型
     * @return 逻辑结果
     */
    public static <T> boolean propertiesIsNull(T object) {
        return propertiesIsNull(object, false);
    }

    /**
     * 判断对象的某些属性是否为null
     *
     * @param object     检查的对象
     * @param flag       标识位：true：包含列出来的属性； false：不包含列出的属性
     * @param properties 检测或不检测的属性集合
     * @param <T>        检测对象的泛型
     * @return 逻辑结果
     */
    public static <T> boolean propertiesIsNull(T object, boolean flag, String... properties) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (flag != isContain(properties, fieldName)) {
                continue;
            }
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue instanceof CharSequence) {
                if (!StringUtils.isEmpty(fieldValue)) {
                    return false;
                }
            } else if (!Objects.isNull(fieldValue)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param target           目标对象
     * @param source           源对象
     * @param ignoreProperties 被忽略的属性
     * @param <T>              目标泛型
     * @param <S>              源泛型
     */
    public static <T, S> void coverProperties(S source, T target, String... ignoreProperties) {
        coverProperties(source, target, null, ignoreProperties);
    }

    /**
     * 两个对象赋值，只有当源对象不为空的时候才会对目标对象的属性覆盖，否则保留目标对象的属性值
     *
     * @param target           目标对象
     * @param source           源对象
     * @param fieldMapper      字段的映射规则
     * @param ignoreProperties 被忽略的属性
     * @param <T>              目标泛型
     * @param <S>              源泛型
     */

    public static <T, S> void coverProperties(S source, T target, Function<String, String> fieldMapper, String... ignoreProperties) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        /*
        设置字段的默认映射规则：直接复制
         */
        if (Objects.isNull(fieldMapper)) {
            fieldMapper = s -> s;
        }
        /*

         */
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(target.getClass());
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String targetPropertyName = propertyDescriptor.getName();
            if (isContain(ignoreProperties, targetPropertyName)) {
                continue;
            }
            String sourcePropertyName = fieldMapper.apply(targetPropertyName);
            copyValue(source, target, sourcePropertyName, propertyDescriptor);
        }
    }

    /**
     * 复制属性值
     *
     * @param target                   目标对象
     * @param source                   源对象
     * @param sourcePropertyName       源属性名称
     * @param targetPropertyDescriptor 目标属性描述
     * @param <T>                      目标泛型
     * @param <S>                      源泛型
     */
    private static <T, S> void copyValue(S source, T target, String sourcePropertyName, PropertyDescriptor targetPropertyDescriptor) {
        PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), sourcePropertyName);
        if (Objects.isNull(sourcePropertyDescriptor)) {
            log.debug("Source class has no property named {}", sourcePropertyName);
            return;
        }
        setValue(source, target, sourcePropertyDescriptor, targetPropertyDescriptor);
    }

    /**
     * 为目标对象赋值
     *
     * @param target                   目标对象
     * @param source                   源对象
     * @param sourcePropertyDescriptor 源类属性描述
     * @param targetPropertyDescriptor 目标类属性描述
     * @param <T>                      目标类泛型
     * @param <S>                      源类泛型
     */
    private static <T, S> void setValue(S source, T target, PropertyDescriptor sourcePropertyDescriptor, PropertyDescriptor targetPropertyDescriptor) {
        Method readMethod = sourcePropertyDescriptor.getReadMethod();
        Object sourceValue;
        try {
            //读取源对象属性值
            sourceValue = readMethod.invoke(source);
            /*
            如果源对象的当前属性值为空，不进行赋值
             */
            if (Objects.isNull(sourceValue)) {
                return;
            }
            Method targetWriteMethod = targetPropertyDescriptor.getWriteMethod();
            if (Objects.isNull(targetWriteMethod)) {
                return;
            }
            setAccess(targetWriteMethod);
            targetWriteMethod.invoke(target, sourceValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改set方法为可以访问
     *
     * @param method set方法
     */
    private static void setAccess(Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            method.setAccessible(true);
        }
    }

    /**
     * 判断元素是否包含于另一个数组中
     *
     * @param properties 不需要被覆盖的属性值集合
     * @param element    当前属性
     * @return 是否被包含
     */
    private static boolean isContain(String[] properties, String element) {
        for (String property : properties) {
            if (property.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MenuEntity menuEntity1 = MenuEntity.builder().id("1").menuName("name1").parentId("1").url("www.baidu.com").build();
        MenuEntity menuEntity2 = MenuEntity.builder().parentId("2").url("www.ali.com").build();
        MenuEntity menuEntity3 = MenuEntity.builder().build();
        System.out.println(menuEntity1);
        coverProperties(menuEntity2, menuEntity1);
        System.out.println(menuEntity1);
        System.out.println(propertiesIsNull(menuEntity2));
        System.out.println(propertiesIsNull(menuEntity3));
        System.out.println(propertiesIsNull(menuEntity2, true, "menuName", "url"));

    }
}
