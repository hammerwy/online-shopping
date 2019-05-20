package com.wy.shopping.common.service.exception;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
public class AbstractCustomException extends RuntimeException {
    public AbstractCustomException(String... message) {
        super(String.join("", message));
    }

    public AbstractCustomException(Throwable cause, String... message) {
        super(String.join("", message), cause);
    }
}
