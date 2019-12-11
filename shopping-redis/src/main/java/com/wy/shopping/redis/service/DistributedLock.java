package com.wy.shopping.redis.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wangyong
 * @date 2019/12/11
 * @description
 */
public class DistributedLock {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * redis加分布式锁
     *
     * @param key 锁名
     * @return <code>true</code> 加锁成功， <code>false</code> 加
     */
    public Boolean getDistributedLock(String key) {
        String verificationCode = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        Random random = new Random(1000);
        int i = random.nextInt();
        verificationCode = verificationCode + i;
        return stringRedisTemplate.opsForValue().setIfAbsent(key, verificationCode, 50, TimeUnit.SECONDS);
    }

    public Boolean unlockDistributedLock(String key, String verificationCode) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        Object execute = stringRedisTemplate.execute(RedisScript.of(script, String.class), Collections.singletonList(key), "123");

        System.out.println(execute);
//        String s = stringRedisTemplate.opsForValue().get(key);
//        if (StringUtils.isEmpty(s)) {
//            log.info("Not get any lock information!");
//            return true;
//        }
//        if (StringUtils.equals(s, verificationCode)) {
//            return stringRedisTemplate.delete(key);
//        }
//        return false;

        return true;
    }
}
