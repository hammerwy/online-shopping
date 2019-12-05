package com.wy.shopping.common.service.beans;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author wy
 * @date 2019/11/14
 * @description
 */
public class TestCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final Environment environment = context.getEnvironment();
        return environment.containsProperty("test");
    }
}
