package com.wy.shopping.common.service.enumeration.user;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import lombok.Getter;

/**
 * @author wy
 * @description 用户的状态
 * @date 2019-05-10
 */
@Getter
public enum UserStateEnum implements BaseEnum {
    /**
     * 启用
     */
    ON(1, "启用"),
    /**
     * 禁用
     */
    OFF(0, "禁用");
    ;
    private int code;
    private String message;

    UserStateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
