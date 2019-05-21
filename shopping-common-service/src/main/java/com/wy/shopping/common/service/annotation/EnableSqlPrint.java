package com.wy.shopping.common.service.annotation;

import com.wy.shopping.common.service.config.SqlPrintConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SqlPrintConfig.class)
public @interface EnableSqlPrint {
}
