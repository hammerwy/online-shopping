package com.wy.shopping.redis.service;

import com.wy.shopping.common.service.facade.redis.RedisService;
import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
@Service
public class RedisServiceImpl implements RedisService {
    /**
     *
     * @param keys key数组
     */
    @Override
    public void remove(String... keys) {

    }

    @Override
    public void removePattern(String pattern) {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public Serializable get(String key) {
        return null;
    }

    @Override
    public boolean set(String key, Serializable value) {
        return false;
    }

    @Override
    public boolean set(String key, Serializable value, Long expireTime) {
        return false;
    }

    @Override
    public <K, HK, HV> boolean setMap(K key, Map<HK, HV> map, Long expireTime) {
        return false;
    }

    @Override
    public <K, HK, HV> Map<HK, HV> getMap(K key) {
        return null;
    }


    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        executorService.submit(()->{
            test test= new test();
            for (int i = 0; i < 100000; i++) {
                com.wy.shopping.redis.service.test.offer();
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        executorService.submit(()->{
            test test= new test();
            for (int i = 0; i < 100000; i++) {
                test.offer();
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}
