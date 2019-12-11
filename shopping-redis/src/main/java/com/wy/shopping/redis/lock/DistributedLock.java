package com.wy.shopping.redis.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @date 2019/11/27
 * @description
 */
public interface DistributedLock {
    /**
     * redis中key的前缀, lock + 业务名
     */
    String LOCK_KEY_PREFIX = "lock:shop";

    /**
     * 申请锁
     *
     * @param lockKey  锁的标识，用于区别资源
     * @param timeout  锁的超时时间
     * @param timeUnit 时间单位
     * @return 身份唯一标识码，用于解锁
     */
    String acquire(String lockKey, long timeout, TimeUnit timeUnit);

    /**
     * 释放锁
     *
     * @param lockKey          锁的标识
     * @param verificationCode 身份标识码
     * @return <code>true</code> 解锁成功 <code>false</code> 解锁失败
     */
    Boolean release(String lockKey, String verificationCode);
}
