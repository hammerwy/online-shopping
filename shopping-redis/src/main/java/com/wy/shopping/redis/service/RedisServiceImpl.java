package com.wy.shopping.redis.service;

import com.wy.shopping.common.service.facade.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
@Slf4j
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 批量移除key
     *
     * @param keys 多个key
     */
    @Override
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 移除单个key
     *
     * @param key key
     */
    @Override
    public void remove(String key) {
        if (exists(key)) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断一个key是否存在
     *
     * @param key key
     * @return 存在与否
     */
    @Override
    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * String类型 get key
     *
     * @param key key
     * @return value
     */
    @Override
    public Serializable get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取多个值
     *
     * @param keys key集合
     * @return value集合
     */
    @Override
    public List<String> multiGet(Collection<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 设置键的字符串值并返回其旧值
     *
     * @param key   key
     * @param value value
     * @return 旧值
     */
    @Override
    public String getAndSet(String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * String类型 set key value
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * string类型 set key value
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     * @param unit    时间单位
     * @see java.util.concurrent.TimeUnit
     */
    @Override
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * String类型 set key value
     *
     * @param key    key
     * @param value  value
     * @param offset 偏移量
     */
    @Override
    public void set(String key, String value, Long offset) {
        stringRedisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * String类型 set key value
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     */
    @Override
    public void set(String key, String value, Duration timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout);
    }

    /**
     * 如果一个key不存在，则设置key
     *
     * @param key   key
     * @param value value
     * @return 如果之前存在返回 false， 如果不存在则返回true
     */
    @Override
    public boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 设置多个key-value
     *
     * @param kvMaps 由key-value组成了map
     */
    @Override
    public void multiSet(Map<String, String> kvMaps) {
        stringRedisTemplate.opsForValue().multiSet(kvMaps);
    }

    /**
     * 对value进行加法计算
     *
     * @param key   key
     * @param delta 加数
     * @return 和
     */
    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
     *
     * @param key         key
     * @param appendValue 追加字符串
     * @return
     */
    @Override
    public Integer append(String key, String appendValue) {
        return stringRedisTemplate.opsForValue().append(key, appendValue);
    }

    /**
     * list 类型 从左边添加元素
     *
     * @param key   key
     * @param value value
     * @return 添加后的list的长度
     */
    @Override
    public Long leftPush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * list 类型 从左边添加多个元素
     *
     * @param key   key
     * @param value 多个value
     * @return 添加后的list的长度
     */
    @Override
    public Long leftPushAll(String key, String... value) {
        return stringRedisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 只有当key存在时，才会把value添加到对应的列表中
     *
     * @param key   key
     * @param value value
     * @return 添加后的list的长度，如果不存在则返回 0
     */
    @Override
    public Long leftPushIfPresent(String key, String value) {
        return stringRedisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 如果列表中存在pivot元素，则把value添加到pivot的左边
     *
     * @param key   key
     * @param pivot 参考元素
     * @param value value
     * @return 添加后的list的长度
     */
    @Override
    public Long leftPush(String key, String pivot, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 从右边添加一个元素
     *
     * @param key   key
     * @param value value
     * @return 添加成功后list的长度
     */
    @Override
    public Long rightPush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 从右边添加多个元素
     *
     * @param key   key
     * @param value value
     * @return 添加成功后list的长度
     */
    @Override
    public Long rightPushAll(String key, String... value) {
        return stringRedisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 只有当key存在时，才会把value添加到对应的列表中
     *
     * @param key   key
     * @param value value
     * @return 添加成功后list的长度
     */
    @Override
    public Long rightPushIfPresent(String key, String value) {
        return stringRedisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 如果列表中存在pivot元素，则把value添加到pivot的右边
     *
     * @param key   key
     * @param pivot 参考元素
     * @param value value
     * @return 添加后的list的长度
     */
    @Override
    public Long rightPush(String key, String pivot, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, pivot, value);
    }

    /**
     * list set
     *
     * @param key   key
     * @param index 在列表中index位置设置value
     * @param value value
     */
    @Override
    public void set(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * 计数参数以下列方式影响操作：
     * count> 0：删除等于从头到尾移动的值的元素。
     * count <0：删除等于从尾到头移动的值的元素。
     * count = 0：删除等于value的所有元素。
     *
     * @param key   key
     * @param count 计数事件
     * @param value value
     * @return 添加后的list的长度
     */
    @Override
    public Long remove(String key, long count, String value) {
        return stringRedisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 返回键为key的list的索引为index的元素，索引是从0开始的
     *
     * @param key   key
     * @param index index
     * @return value
     */
    @Override
    public String index(String key, int index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 从列表左边弹出元素
     *
     * @param key key
     * @return value
     */
    @Override
    public String leftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key     key
     * @param timeout 等待超时时长
     * @param unit    时间单位
     * @return value
     */
    @Override
    public String leftPop(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 从列表的右边弹出元素
     *
     * @param key key
     * @return value
     */
    @Override
    public String rightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key     key
     * @param timeout 等待超时时长
     * @param unit    时间单位
     * @return value
     */
    @Override
    public String rightPop(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 用于移除源列表的最后一个元素，并将该元素添加到目标列表的左边并返回。如果目标列表不存在，则新建一个列表
     *
     * @param sourceListKey      源列表
     * @param destinationListKey 目标列表
     * @return 返回该元素
     */
    @Override
    public String rightPopAndLeftPush(String sourceListKey, String destinationListKey) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceListKey, destinationListKey);
    }

    /**
     * 用于移除源列表的最后一个元素，并将该元素添加到目标列表的左边并返回。如果目标列表不存在，则新建一个列表;
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param sourceListKey      源列表
     * @param destinationListKey 目标列表
     * @return 返回该元素
     */
    @Override
    public String rightPopAndLeftPush(String sourceListKey, String destinationListKey, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceListKey, destinationListKey, timeout, unit);
    }

    /**
     * Hasho数据结构 判断一个哈希hashkey是否存在
     *
     * @param key     key
     * @param hashKey key中的二级hashkey
     * @return true：存在；false：不存在
     */
    @Override
    public boolean haveKey(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 删除一个或多个hashkey
     *
     * @param key     key
     * @param hashKey 哈希hashkey
     * @return 删除后的
     */
    @Override
    public Long delete(String key, Object... hashKey) {
        return stringRedisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key     key
     * @param hashKey 哈希hashkey
     * @return 删除后的
     */
    @Override
    public Object get(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 从哈希中获取给定hashKey的值
     *
     * @param key      key
     * @param hashKeys 多个hashkey
     * @return value
     */
    @Override
    public List<Object> multiGet(String key, Collection<Object> hashKeys) {
        return stringRedisTemplate.opsForHash().multiGet(key, hashKeys);
    }


    /**
     * 添加哈希数据
     *
     * @param key  key
     * @param map  哈希map
     * @param <HK> 哈希key泛型
     * @param <HV> 哈希value泛型
     */
    @Override
    public <HK, HV> void putAll(String key, Map<HK, HV> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 根据key获取整个hash
     *
     * @param key key
     * @return hashmap
     */
    @Override
    public Map<Object, Object> getHash(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 通过游标的方式来查找哈希里的成员
     *
     * @param key key
     * @return 结果
     */
    @Override
    public Map<Object, Object> getHashByCursor(String key) {
        return getHashByCursor(key, ScanOptions.NONE);
    }

    /**
     * 通过游标的方式来根据scanOptions条件来查找哈希里的成员
     *
     * @param key         key
     * @param scanOptions 条件
     * @return 符合条件的结果
     */
    @Override
    public Map<Object, Object> getHashByCursor(String key, ScanOptions scanOptions) {
        Map<Object, Object> result = new HashMap<>();
        Cursor<Map.Entry<Object, Object>> cursor = stringRedisTemplate.opsForHash().scan(key, scanOptions);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> element = cursor.next();
            result.put(element.getKey(), element.getValue());
        }
        return result;
    }

    /**
     * set类型,无序集合中添加元素，返回添加个数
     * 也可以直接在add里面添加多个值 如：template.opsForSet().add("setTest","aaa","bbb")
     *
     * @param key   key
     * @param value value
     * @return 添加个数
     */
    @Override
    public Long add(String key, String... value) {
        return stringRedisTemplate.opsForSet().add(key, value);
    }

    /**
     * 删除无序集合中一个或多个元素
     *
     * @param key   key
     * @param value value
     * @return 移除的个数
     */
    @Override
    public Long remove(String key, Object... value) {
        return stringRedisTemplate.opsForSet().remove(key, value);
    }

    /**
     * 移除并返回无序集合中的一个元素
     *
     * @param key key
     * @return 返回值
     */
    @Override
    public String pop(String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    /**
     * 把元素从source集合移动到destination集合
     *
     * @param sourceKey      key
     * @param value          value
     * @param destinationKey 目标key
     * @return true:移动成功；false：移动失败
     */
    @Override
    public boolean move(String sourceKey, String value, String destinationKey) {
        return stringRedisTemplate.opsForSet().move(sourceKey, value, destinationKey);
    }

    /**
     * 返回当前无序集合的大小
     *
     * @param key key
     * @return size
     */
    @Override
    public Long size(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * 判断元素是否是当前无序集合的成员
     *
     * @param key    key
     * @param member member
     * @return true：是；false：不是
     */
    @Override
    public boolean isMember(String key, Object member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * 求两个无序集合的交集
     *
     * @param key      key
     * @param otherKey otherkey
     * @return 相交元素
     */
    @Override
    public Set<String> intersect(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * 求当前集合与多个集合的交集
     *
     * @param key       key
     * @param otherKeys otherkeys
     * @return 相交元素
     */
    @Override
    public Set<String> intersect(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * 求当前集合与其他多个集合的并集
     *
     * @param key       key
     * @param otherKeys otherkeys
     * @return 并集
     */
    @Override
    public Set<String> union(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 求当前集合与另一个集合的并集
     *
     * @param key      key
     * @param otherKey otherkey
     * @return 并集
     */
    @Override
    public Set<String> union(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().union(key, otherKey);
    }
}
