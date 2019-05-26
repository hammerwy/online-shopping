package com.wy.shopping.common.service.facade.redis;

import org.springframework.data.redis.core.ScanOptions;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
public interface RedisService {
    /**
     * 批量移除key
     *
     * @param keys 多个key
     */
    void remove(String... keys);

    /**
     * 移除单个key
     *
     * @param key key
     */
    void remove(String key);

    /**
     * 判断一个key是否存在
     *
     * @param key key
     * @return 存在与否
     */
    boolean exists(String key);

    /**
     * String类型 get key
     *
     * @param key key
     * @return value
     */

    Serializable get(String key);

    /**
     * 获取多个值
     *
     * @param keys key集合
     * @return value集合
     */
    List<String> multiGet(Collection<String> keys);

    /**
     * 设置键的字符串值并返回其旧值
     *
     * @param key   key
     * @param value value
     * @return 旧值
     */
    String getAndSet(String key, String value);

    /**
     * String类型 set key value
     *
     * @param key   key
     * @param value value
     * @return
     */
    void set(String key, String value);

    /**
     * string类型 set key value
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     * @param unit    时间单位
     * @see java.util.concurrent.TimeUnit
     */
    void set(String key, String value, long timeout, TimeUnit unit);

    /**
     * String类型 set key value
     *
     * @param key    key
     * @param value  value
     * @param offset 偏移量
     */
    void set(String key, String value, Long offset);

    /**
     * String类型 set key value
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     */
    void set(String key, String value, Duration timeout);

    /**
     * 如果一个key不存在，则设置key
     *
     * @param key   key
     * @param value value
     * @return 如果之前存在返回 false， 如果不存在则返回true
     */
    boolean setIfAbsent(String key, String value);

    /**
     * 设置多个key-value
     *
     * @param kvMaps 由key-value组成了map
     */
    void multiSet(Map<String, String> kvMaps);

    /**
     * 对value进行加法计算
     *
     * @param key   key
     * @param delta 加数
     * @return 和
     */
    Long increment(String key, long delta);

    /**
     * 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
     *
     * @param key         key
     * @param appendValue 追加字符串
     * @return
     */
    Integer append(String key, String appendValue);

    /**
     * list 类型 从左边添加元素
     *
     * @param key   key
     * @param value value
     * @return 添加后的list的长度
     */
    Long leftPush(String key, String value);

    /**
     * list 类型 从左边添加多个元素
     *
     * @param key   key
     * @param value 多个value
     * @return 添加后的list的长度
     */
    Long leftPushAll(String key, String... value);

    /**
     * 只有当key存在时，才会把value添加到对应的列表中
     *
     * @param key   key
     * @param value value
     * @return 添加后的list的长度，如果不存在则返回 0
     */
    Long leftPushIfPresent(String key, String value);

    /**
     * 如果列表中存在pivot元素，则把value添加到pivot的左边
     *
     * @param key   key
     * @param pivot 参考元素
     * @param value value
     * @return 添加后的list的长度
     */
    Long leftPush(String key, String pivot, String value);

    /**
     * 从右边添加一个元素
     *
     * @param key   key
     * @param value value
     * @return 添加成功后list的长度
     */
    Long rightPush(String key, String value);

    /**
     * 从右边添加多个元素
     *
     * @param key   key
     * @param value value
     * @return 添加成功后list的长度
     */
    Long rightPushAll(String key, String... value);

    /**
     * 只有当key存在时，才会把value添加到对应的列表中
     *
     * @param key   key
     * @param value value
     * @return 添加成功后list的长度
     */
    Long rightPushIfPresent(String key, String value);

    /**
     * 如果列表中存在pivot元素，则把value添加到pivot的右边
     *
     * @param key   key
     * @param pivot 参考元素
     * @param value value
     * @return 添加后的list的长度
     */
    Long rightPush(String key, String pivot, String value);

    /**
     * list set
     *
     * @param key   key
     * @param index 在列表中index位置设置value
     * @param value value
     */
    void set(String key, long index, String value);

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
    Long remove(String key, long count, String value);

    /**
     * 返回键为key的list的索引为index的元素，索引是从0开始的
     *
     * @param key   key
     * @param index index
     * @return value
     */
    String index(String key, int index);

    /**
     * 从列表左边弹出元素
     *
     * @param key key
     * @return value
     */
    String leftPop(String key);

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key     key
     * @param timeout 等待超时时长
     * @param unit    时间单位
     * @return value
     */
    String leftPop(String key, long timeout, TimeUnit unit);

    /**
     * 从列表的右边弹出元素
     *
     * @param key key
     * @return value
     */
    String rightPop(String key);

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key     key
     * @param timeout 等待超时时长
     * @param unit    时间单位
     * @return value
     */
    String rightPop(String key, long timeout, TimeUnit unit);

    /**
     * 用于移除源列表的最后一个元素，并将该元素添加到目标列表的左边并返回。如果目标列表不存在，则新建一个列表
     *
     * @param sourceListKey      源列表
     * @param destinationListKey 目标列表
     * @return 返回该元素
     */
    String rightPopAndLeftPush(String sourceListKey, String destinationListKey);

    /**
     * 用于移除源列表的最后一个元素，并将该元素添加到目标列表的左边并返回。如果目标列表不存在，则新建一个列表;
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param sourceListKey      源列表
     * @param destinationListKey 目标列表
     * @return 返回该元素
     */
    String rightPopAndLeftPush(String sourceListKey, String destinationListKey, long timeout, TimeUnit unit);

    /**
     * Hasho数据结构 判断一个哈希hashkey是否存在
     *
     * @param key     key
     * @param hashKey key中的二级hashkey
     * @return true：存在；false：不存在
     */
    boolean haveKey(String key, String hashKey);

    /**
     * 删除一个或多个hashkey
     *
     * @param key     key
     * @param hashKey 哈希hashkey
     * @return 删除后的
     */
    Long delete(String key, Object... hashKey);

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key     key
     * @param hashKey 哈希hashkey
     * @return 删除后的
     */
    Object get(String key, String hashKey);

    /**
     * 从哈希中获取给定hashKey的值
     *
     * @param key      key
     * @param hashKeys 多个hashkey
     * @return value
     */
    List<Object> multiGet(String key, Collection<Object> hashKeys);


    /**
     * 添加哈希数据
     *
     * @param key  key
     * @param map  哈希map
     * @param <HK> 哈希key泛型
     * @param <HV> 哈希value泛型
     */
    <HK, HV> void putAll(String key, Map<HK, HV> map);

    /**
     * 根据key获取整个hash
     *
     * @param key key
     * @return hashmap
     */
    Map<Object, Object> getHash(String key);

    /**
     * 通过游标的方式来查找哈希里的成员
     *
     * @param key key
     * @return 结果
     */
    Map<Object, Object> getHashByCursor(String key);

    /**
     * 通过游标的方式来根据scanOptions条件来查找哈希里的成员
     *
     * @param key         key
     * @param scanOptions 条件
     * @return 符合条件的结果
     */
    Map<Object, Object> getHashByCursor(String key, ScanOptions scanOptions);

    /**
     * set类型,无序集合中添加元素，返回添加个数
     * 也可以直接在add里面添加多个值 如：template.opsForSet().add("setTest","aaa","bbb")
     *
     * @param key   key
     * @param value value
     * @return 添加个数
     */
    Long add(String key, String... value);

    /**
     * 删除无序集合中一个或多个元素
     *
     * @param key   key
     * @param value value
     * @return 移除的个数
     */
    Long remove(String key, Object... value);

    /**
     * 移除并返回无序集合中的一个元素
     *
     * @param key key
     * @return 返回值
     */
    String pop(String key);

    /**
     * 把元素从source集合移动到destination集合
     *
     * @param sourceKey      key
     * @param value          value
     * @param destinationKey 目标key
     * @return
     */
    boolean move(String sourceKey, String value, String destinationKey);

    /**
     * 返回当前无序集合的大小
     *
     * @param key key
     * @return size
     */
    Long size(String key);

    /**
     * 判断元素是否是当前无序集合的成员
     *
     * @param key    key
     * @param member member
     * @return true：是；false：不是
     */
    boolean isMember(String key, Object member);

    /**
     * 求两个无序集合的交集
     *
     * @param key      key
     * @param otherKey otherkey
     * @return 相交元素
     */
    Set<String> intersect(String key, String otherKey);

    /**
     * 求当前集合与多个集合的交集
     *
     * @param key       key
     * @param otherKeys otherkeys
     * @return 相交元素
     */
    Set<String> intersect(String key, Collection<String> otherKeys);

    /**
     * 求当前集合与其他多个集合的并集
     *
     * @param key       key
     * @param otherKeys otherkeys
     * @return 并集
     */
    Set<String> union(String key, Collection<String> otherKeys);

    /**
     * 求当前集合与另一个集合的并集
     *
     * @param key      key
     * @param otherKey otherkey
     * @return 并集
     */
    Set<String> union(String key, String otherKey);
}
