package com.raindrop.idempotent.model;

import com.raindrop.idempotent.base.IdempotentToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @name: com.raindrop.idempotent.model.MemoryIdempotentToken.java
 * @description: Idempotent token memory storage implement
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
public class MemoryIdempotentToken implements IdempotentToken {

    private static final Logger logger = LoggerFactory.getLogger(MemoryIdempotentToken.class);

    private static Set<String> tokens = new HashSet<>();

    /**
     * Add the token to memory
     *
     * @param token
     */
    @Override
    public boolean add(String token, long timeout, TimeUnit timeUnit) {
        try {
            tokens.add(token);
            return true;
        } catch (Exception e) {
            logger.error("Memory idempotent token [ " + token + " ] set error.");
            return false;
        }
    }

    /**
     * Check the token exists in memory
     *
     * @param token
     * @return
     */
    @Override
    public boolean remove(String token) {
        boolean contains = tokens.contains(token);
        if (contains) {
            tokens.remove(token);
        }
        return contains;
    }

}
