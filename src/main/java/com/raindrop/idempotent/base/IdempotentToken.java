package com.raindrop.idempotent.base;

import java.util.concurrent.TimeUnit;

/**
 * @name: com.raindrop.idempotent.base.IdempotentToken.java
 * @description: Idempotent token interface
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
public interface IdempotentToken {

    /**
     * Add the key to storage
     *
     * @param key        cache key
     * @param value      cache value
     * @param expireTime cache expireTime
     * @param expireUnit expireTime Unit
     */
    boolean add(String key, String value, long expireTime, TimeUnit expireUnit);

    /**
     * Remove the key exists form storage
     *
     * @param key   cache key
     * @param value cache value
     * @return
     */
    boolean remove(String key, String value);

}
