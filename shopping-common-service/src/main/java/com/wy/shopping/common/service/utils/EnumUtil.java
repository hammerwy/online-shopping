package com.wy.shopping.common.service.utils;

import com.wy.shopping.common.service.enumeration.BaseEnum;

/**
 * @author wy
 * @description 枚举工具类
 * @date 2019-05-18
 */
public class EnumUtil {
    /**
     * 根据code得到枚举实例
     *
     * @param enumClass 枚举类
     * @param code      code
     * @param <E>       枚举泛型
     * @return 枚举类实例
     */
    public static <E extends BaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E enumConstant : enumConstants) {
            if (enumConstant.getCode() == code) {
                return enumConstant;
            }
        }
        return null;
    }


}
