package com.wy.shopping.common.service.facade.redis;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
public interface RedisService {
    /**
     * 批量删除
     * @param keys key数组
     */
    void remove(final String... keys);

    /**
     * 批量删除指定key
     * @param pattern
     */
    void removePattern(final String pattern);

    /**
     * 删除指定key
     * @param key
     */
    void remove(final String key);

    /**
     * 判断指定key是否存在
     * @param key
     * @return
     */
    boolean exists(final String key);

    /**
     * 获取指定key
     * @param key
     * @return
     */
    Serializable get(final String key);

    /**
     * 添加key-value（使用默认失效时间）
     * @param key
     * @param value
     * @return
     */
    boolean set(final String key, Serializable value);

    /**
     * 添加key-value（指定失效时间）
     * @param key
     * @param value
     * @param expireTime 失效时间（单位秒）
     * @return
     */
    boolean set(final String key, Serializable value, Long expireTime);

    /**
     * 存储map
     * @param key
     * @param map
     * @param expireTime 失效时间为null则永久生效（单位秒）
     * @return
     */
    <K,HK,HV> boolean setMap(K key, Map<HK, HV> map, Long expireTime);


    /**
     * 获取map
     * @param key
     * @param <K>
     * @param <HK>
     * @param <HV>
     * @return
     */
    <K,HK,HV> Map<HK,HV> getMap(final K key);
}
