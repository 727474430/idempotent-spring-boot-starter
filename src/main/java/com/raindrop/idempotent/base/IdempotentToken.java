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
     * Add the token to storage
     *
     * @param token
     * @param timeout
     * @param timeUnit
     */
    boolean add(String token, long timeout, TimeUnit timeUnit);

    /**
     * Remove the token exists form storage
     *
     * @param token
     * @return
     */
    boolean remove(String token);

}
