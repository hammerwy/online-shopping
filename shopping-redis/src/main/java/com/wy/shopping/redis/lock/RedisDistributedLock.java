package com.wy.shopping.redis.lock;

import com.wy.shopping.common.service.content.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @date 2019/11/28
 * @description
 */
@Slf4j
@Component
public class RedisDistributedLock implements DistributedLock {

    /**
     * 释放锁，lua脚本
     */
    private final static String RELEASE_LOCK =
            "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                    "    local lockRelease = redis.call(\"del\",KEYS[1])\n" +
                    "        if lockRelease then\n" +
                    "            return \"1\"\n" +
                    "        end\n" +
                    "            return \"0\"\n" +
                    "end\n" +
                    "return \"-1\"";
    /**
     * 当成功释放锁时，返回的信息
     */
    private final static String RELEASE_LOCK_SUCCESSFULLY_STATUS = "1";



    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String acquire(String lockKey, long timeout, TimeUnit timeUnit) {
        // 生成身份唯一标识码，格式为：时间（到毫秒）+随机数
        final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        Random random = new Random();
        final int randomInt = random.nextInt(1000);
        String verificationCode = timestamp + randomInt;
        String sourceLockKey = genLockKey(lockKey);
        // 写入redis中
        final Boolean isLocked = stringRedisTemplate.opsForValue()
                .setIfAbsent(sourceLockKey, verificationCode, timeout, timeUnit);
        log.info("申请分布式锁信息：lockKey = {}, verificationCode = {}, lock's status = {}",
                sourceLockKey, verificationCode, isLocked);
        if (BooleanUtils.isFalse(isLocked)) {
            return null;
        }
        return verificationCode;
    }

    @Override
    public Boolean release(String lockKey, String verificationCode) {
        String sourceLockKey = genLockKey(lockKey);

        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(RELEASE_LOCK);
        redisScript.setResultType(String.class);

        final String releaseLockStatus = stringRedisTemplate
                .execute(redisScript, Collections.singletonList(sourceLockKey), verificationCode);
        if (Objects.isNull(releaseLockStatus)) {
            final String value = stringRedisTemplate.opsForValue().get(sourceLockKey);
            if (StringUtils.isNotEmpty(value)) {
                log.info("释放锁时返回为空, 但是锁仍存在，lock key = {}, value = {}", sourceLockKey, value);
                return false;
            }
            log.info("锁释放成功！lock key = {}", sourceLockKey);
            return true;
        }
        return RELEASE_LOCK_SUCCESSFULLY_STATUS.equals(releaseLockStatus);
    }

    private String genLockKey(String sourceKey) {
        return String.join(":", LOCK_KEY_PREFIX, SpringContext.getActiveProfile(), sourceKey);
    }

    public static void main(String[] args) {
        String a = "𧿹😀";
        System.out.println(a.length());
        System.out.println(a.codePointCount(0, a.length()));
    }
}
