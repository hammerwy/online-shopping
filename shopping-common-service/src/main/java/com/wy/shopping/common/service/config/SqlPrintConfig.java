package com.wy.shopping.common.service.config;

import com.wy.shopping.common.service.Interceptor.SqlPrintInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
public class SqlPrintConfig {
    @Bean
    public SqlPrintInterceptor getSqlPrintInterceptor(){
        return new SqlPrintInterceptor();
    }
}
