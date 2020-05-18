package com.raindrop.idempotent.model;

import com.raindrop.idempotent.base.IdempotentToken;

import java.util.HashSet;
import java.util.Set;

/**
 * @name: com.raindrop.idempotent.model.MemoryIdempotentToken.java
 * @description: Idempotent token memory storage implement
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
public class MemoryIdempotentToken implements IdempotentToken {

    private static Set<String> tokens = new HashSet<>();

    /**
     * Add the token to memory
     *
     * @param token
     */
    @Override
    public void add(String token) {
        tokens.add(token);
    }

    /**
     * Check the token exists in memory
     *
     * @param token
     * @return
     */
    @Override
    public boolean check(String token) {
        boolean contains = tokens.contains(token);
        if (contains) {
            tokens.remove(token);
        }
        return contains;
    }

}
