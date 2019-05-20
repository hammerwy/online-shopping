package com.wy.shopping.common.service.enumeration;

import lombok.Getter;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Getter
public enum HttpMethodEnum {
    /**
     * get
     */
    GET(1, "GET"),
    /**
     * post
     */
    POST(2, "POST"),
    /**
     * put
     */
    PUT(3, "PUT"),
    /**
     * delete
     */
    DELETE(4, "DELETE");

    private int code;
    private String msg;

    HttpMethodEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
