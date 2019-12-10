package com.wy.shopping.common.service.logback;

import ch.qos.logback.classic.PatternLayout;
import com.sun.javafx.css.converters.ColorConverter;
import org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter;
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter;

/**
 * @author wy
 * @date 2019/8/27
 * @description
 */
public class CustomColorLogLayout extends PatternLayout {
    static {
        defaultConverterMap.put("clr", ColorConverter.class.getName());
        defaultConverterMap.put("wex", WhitespaceThrowableProxyConverter.class.getName());
        defaultConverterMap.put("wEx", ExtendedWhitespaceThrowableProxyConverter.class.getName());
    }
}
