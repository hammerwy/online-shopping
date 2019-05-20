package com.wy.shopping.common.service.exception;

import lombok.Getter;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Getter
public class BizException extends AbstractCustomException {

    private ExceptionCodeEnum exceptionCode;

    public BizException(ExceptionCodeEnum exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BizException(Throwable cause, ExceptionCodeEnum exceptionCode) {
        super(cause, exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BizException(String... message) {
        super(message);
    }

    public BizException(Throwable cause, String... message) {
        super(cause, message);
    }
}
