package com.wy.shopping.user;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wy
 */
@EnableDubbo
@DubboComponentScan(basePackages = "com.wy.shopping.user.service")
@MapperScan(basePackages = "com.wy.shopping.user.service.repository")
@SpringBootApplication
public class ShoppingUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingUserApplication.class, args);
    }

}
