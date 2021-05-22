package com.raindrop.idempotent.model;

import com.raindrop.idempotent.base.IdempotentToken;
import com.raindrop.idempotent.util.RedisScriptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @name: com.raindrop.idempotent.model.RedisIdempotentToken.java
 * @description: Idempotent token memory storage implement
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
public class RedisIdempotentToken implements IdempotentToken {

    private static Logger logger = LoggerFactory.getLogger(RedisIdempotentToken.class);

    /**
     * Redis token key default value
     */
    private static final String DEFAULT_VALUE = "0";
    /**
     * Redis token key default expire time
     */
    private static final Long DEFAULT_EXPIRE = 60L;

    private final StringRedisTemplate redisTemplate;

    public RedisIdempotentToken(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Add the token to redis
     *
     * @param token
     */
    @Override
    public boolean add(String token, long timeout, TimeUnit timeUnit) {
        try {
            Boolean success = redisTemplate.opsForValue().setIfAbsent(token, DEFAULT_VALUE);
            if (success) {
                redisTemplate.expire(token, timeout, timeUnit);
            }
            return success;
        } catch (Exception e) {
            logger.error("Redis Idempotent add token error: \n{}", e.getMessage());
            return false;
        }
    }

    /**
     * Check the token exists in redis
     *
     * @param token
     * @return
     */
    @Override
    public boolean remove(String token) {
        try {
            return (boolean) redisTemplate.execute(RedisScriptUtils.removeTokenScript(), Collections.singletonList(token));
        } catch (Exception e) {
            logger.error("Redis Idempotent remove token error: \n{}", e.getMessage());
            return false;
        }
    }

}
