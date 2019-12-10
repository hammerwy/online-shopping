package com.wy.shopping.controller.annotation;

import com.wy.shopping.common.service.annotation.DbHint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
public class AnnotationTest {
    /**
     * 测试运行时注解
     */
    public static void testRuntimeAnnotation() {
        StringBuffer sb = new StringBuffer();
        Class<?> cls = TestClass.class;
        // 获取指定类型的注解
        sb.append("Class注解：").append("\n");
        DbHint hintInfo = cls.getAnnotation(DbHint.class);
        Annotation[] annotations = cls.getAnnotations();
        if (hintInfo != null) {
            sb.append(Modifier.toString(cls.getModifiers())).append(" ")
                    .append(cls.getSimpleName()).append("\n");
            System.out.println("dbhint:" + hintInfo.value());
        }
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }


        sb.append("Field注解：").append("\n");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation annotation : fieldAnnotations) {
                System.out.println(annotation.toString());
            }
        }

    }

    public static void main(String[] args) {
        testRuntimeAnnotation();
    }
}
