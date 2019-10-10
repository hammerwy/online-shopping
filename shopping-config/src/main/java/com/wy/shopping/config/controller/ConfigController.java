package com.wy.shopping.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wy
 * @date 2019/10/10
 * @description
 */
@RestController
@RequestMapping(value = "/config")
@RefreshScope
public class ConfigController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping(value = "/localCache")
    public boolean getConfig() {
        return useLocalCache;
    }
}
