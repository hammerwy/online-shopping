package com.wy.shopping.redis.service;

import com.wy.shopping.common.service.facade.redis.RedisService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @date 2019-05-25
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceImplTest {

    @Reference(version = "1.0.0")
    private RedisService redisService;

    @Test
    public void putAll() {
        String key = "primary:hashkey";
        Map<String, String> hashkeys = new HashMap<>();
        hashkeys.put("subkey1", "subvalue1");
        hashkeys.put("subkey2", "subvalue2");
        hashkeys.put("subkey3", "subvalue3");
        hashkeys.put("subkey4", "subvalue4");
        hashkeys.put("subkey5", "subvalue5");
        hashkeys.put("subkey6", "subvalue6");
        redisService.putAll(key, hashkeys);
    }

    @Test
    public void getHashByCursor() {
        String key = "primary:hashkey";
        Map<Object, Object> resultMap = redisService.getHashByCursor(key);
        System.out.println(resultMap);
    }

}