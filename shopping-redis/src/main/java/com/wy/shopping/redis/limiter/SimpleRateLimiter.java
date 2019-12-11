package com.wy.shopping.redis.limiter;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;

/**
 * @author wangyong
 * @date 2019/12/11
 * @description simple limiter that used by limiting user action
 */
public class SimpleRateLimiter implements Limiter {

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
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
        long timeMillis = System.currentTimeMillis();
        zSetOperations.add(key, String.valueOf(timeMillis), timeMillis);

        zSetOperations.removeRangeByScore(key, 0, timeMillis - period * 1000);
        Long size = zSetOperations.zCard(key);

        return size <= maxCount;
    }

}
