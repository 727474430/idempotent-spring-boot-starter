package com.raindrop.idempotent.base;

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
     */
    void add(String token);

    /**
     * Check the token exists form storage
     *
     * @param token
     * @return
     */
    boolean check(String token);

}
