package com.raindrop.idempotent.enums;

/**
 * @name: com.raindrop.idempotent.enums.TokenStorageEnum.java
 * @description: Idempotent token storage support types
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
public enum TokenStorageEnum {

    /**
     * Redis storage
     */
    REDIS("redis"),
    /**
     * Memory storage
     */
    MEMORY("memory");

    String type;

    TokenStorageEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
