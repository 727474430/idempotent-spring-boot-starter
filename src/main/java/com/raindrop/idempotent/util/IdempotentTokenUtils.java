package com.raindrop.idempotent.util;

import cn.hutool.crypto.SecureUtil;
import com.raindrop.idempotent.base.IdempotentToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

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
     * Set the token if not exists
     *
     * @param token
     * @return
     */
    public static boolean setIfAbsent(String token, long timeout, TimeUnit timeUnit) {
        return idempotentToken.add(token, timeout, timeUnit);
    }

    /**
     * Check the token exists
     *
     * @param token
     * @return
     */
    public static boolean remove(String token) {
        return idempotentToken.remove(token);
    }

    @Autowired
    public void setIdempotentToken(IdempotentToken idempotentToken) {
        IdempotentTokenUtils.idempotentToken = idempotentToken;
    }

}
