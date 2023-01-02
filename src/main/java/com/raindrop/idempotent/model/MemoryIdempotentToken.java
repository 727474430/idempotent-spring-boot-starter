package com.raindrop.idempotent.model;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.raindrop.idempotent.base.IdempotentToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @name: com.raindrop.idempotent.model.MemoryIdempotentToken.java
 * @description: Idempotent token memory storage implement
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
public class MemoryIdempotentToken implements IdempotentToken {

    private static final Logger logger = LoggerFactory.getLogger(MemoryIdempotentToken.class);
    /**
     * Memory token key default value
     */
    private static final String DEFAULT_VALUE = "0";
    /**
     * Memory cache manager
     */
    private static Cache<Object, Object> cache = Caffeine.newBuilder()
            .initialCapacity(10)
            .maximumSize(100)
            .expireAfterWrite(10L, TimeUnit.SECONDS)
            .build();

    /**
     * Add the token to memory
     *
     * @param key        cache key
     * @param value      cache value
     * @param expireTime cache expireTime
     * @param expireUnit expireTime Unit
     */
    @Override
    public boolean add(String key, String value, long expireTime, TimeUnit expireUnit) {
        try {
            Object o = cache.getIfPresent(key);
            if (Objects.isNull(o)) {
                cache.put(key, null == value ? DEFAULT_VALUE : value);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Memory idempotent token [ " + key + " ] set error.");
            return false;
        }
    }

    /**
     * Check the token exists in memory
     *
     * @param key   cache key
     * @param value cache value
     * @return
     */
    @Override
    public boolean remove(String key, String value) {
        Object v = cache.getIfPresent(key);
        if (value.equals(v)) {
            cache.invalidate(key);
            return true;
        }
        return false;
    }

}
