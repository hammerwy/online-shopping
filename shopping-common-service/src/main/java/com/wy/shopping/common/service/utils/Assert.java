package com.wy.shopping.common.service.utils;

import com.wy.shopping.common.service.exception.BizException;
import com.wy.shopping.common.service.exception.ExceptionCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
public class Assert {

    public static Assert startCheck() {
        return new Assert();
    }

    public static Assert startCheck(Object object, ExceptionCodeEnum exceptionCode) {
        if (Objects.isNull(object)) {
            throw new BizException(exceptionCode);
        }
        return new Assert();
    }

    public Assert notNull(Object object, ExceptionCodeEnum exceptionCode) {
        if (Objects.isNull(object)) {
            throw new BizException(exceptionCode);
        }
        return this;
    }

    /**
     * 判断字符串是否为空
     *
     * @param string        输入的字符串
     * @param exceptionCode 如果为空，抛出的错误信息
     * @return Assert
     */
    public Assert notEmpty(String string, ExceptionCodeEnum exceptionCode) {
        if (StringUtils.isEmpty(string)) {
            throw new BizException(exceptionCode);
        }
        return this;
    }

    /**
     * 判断多个字符串是否同时为空
     *
     * @param exceptionCode 抛出的错误信息
     * @param multiParam    多个字符串参数
     * @return Assert
     */
    public Assert anyNotEmpty(ExceptionCodeEnum exceptionCode, String... multiParam) {
        if (StringUtils.isAllEmpty(multiParam)) {
            throw new BizException(exceptionCode);
        }
        return this;
    }

    public Assert notEmpty(Collection collection, ExceptionCodeEnum exceptionCode) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(exceptionCode);
        }
        return this;
    }

}
