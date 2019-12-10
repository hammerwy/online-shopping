package com.wy.shopping.common.service.enumeration;

import lombok.Getter;

/**
 * @author wy
 * @description 排序规则枚举
 * @date 2019-05-10
 */
@Getter
public enum OrderEnum implements BaseEnum {
    /**
     * 降序
     */
    DESC(1, "DESC"),
    /**
     * 升序
     */
    ASC(2, "ASC");

    private int code;
    private String message;

    OrderEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
