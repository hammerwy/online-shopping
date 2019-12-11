package com.wy.shopping.redis.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;

/**
 * @author wangyong
 * @date 2019/12/11
 * @description simple limiter that used by limiting user action
 */
public class SimpleRateLimiter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 行为限制器
     * @param userId 用户id
     * @param actionKey
     * @param period
     * @param maxCount
     * @return
     */
    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        ZSetOperations<String, String> stringStringZSetOperations = stringRedisTemplate.opsForZSet();
        long timeMillis = System.currentTimeMillis();
        stringStringZSetOperations.add(key, String.valueOf(timeMillis), timeMillis);

        stringStringZSetOperations.removeRangeByScore(key, 0, timeMillis - period * 1000);
        Long size = stringStringZSetOperations.zCard(key);

        return size <= maxCount;
    }

}
