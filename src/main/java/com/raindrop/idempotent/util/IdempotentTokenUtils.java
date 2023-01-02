package com.raindrop.idempotent.util;

import cn.hutool.crypto.SecureUtil;
import com.raindrop.idempotent.base.IdempotentToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @name: com.raindrop.idempotent.util.IdempotentTokenUtil.java
 * @description: Idempotent token utils class
 * @author: Raindrop
 * @create Time: 2021/5/23 22:00
 */
public class IdempotentTokenUtils {

    private static IdempotentToken idempotentToken;

    /**
     * Use md5(methodName+argsList) generate token
     *
     * @param key (methodName + argsList)
     * @return
     */
    public static String tokenGenerate(String key) {
        return SecureUtil.md5(key);
    }

    /**
     * Set the key if not exists
     *
     * @param key        cache key
     * @param expireTime cache expireTime
     * @param expireUnit expireTime Unit
     * @return
     */
    public static boolean setIfAbsent(String key, long expireTime, TimeUnit expireUnit) {
        return setIfAbsent(key, null, expireTime, expireUnit);
    }

    /**
     * Set the key if not exists
     *
     * @param key        cache key
     * @param value      cache value
     * @param expireTime cache expireTime
     * @param expireUnit expireTime Unit
     * @return
     */
    public static boolean setIfAbsent(String key, String value, long expireTime, TimeUnit expireUnit) {
        return idempotentToken.add(key, value, expireTime, expireUnit);
    }

    /**
     * Check the key exists
     *
     * @param key   cache key
     * @param value cache value
     * @return
     */
    public static boolean remove(String key, String value) {
        return idempotentToken.remove(key, value);
    }

    @Autowired
    public void setIdempotentToken(IdempotentToken idempotentToken) {
        IdempotentTokenUtils.idempotentToken = idempotentToken;
    }

}
