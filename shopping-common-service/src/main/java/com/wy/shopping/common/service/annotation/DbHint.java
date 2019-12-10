package com.wy.shopping.common.service.annotation;

import java.lang.annotation.*;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DbHint {
    String value() default "";
}
