package com.wy.shopping.common.service.enumeration.user;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import lombok.Getter;

/**
 * @author wy
 * @description 用户类型枚举
 * @date 2019-05-10
 */
@Getter
public enum UserTypeEnum implements BaseEnum {
    /**
     * 个人用户
     */
    PERSON(1,"个人用户"),
    /**
     * 企业用户
     */
    COMPANY(2,"企业用户"),
    /**
     * 管理员
     */
    ADMIN(3,"管理员");
    ;
    private int code;
    private String message;

    UserTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
