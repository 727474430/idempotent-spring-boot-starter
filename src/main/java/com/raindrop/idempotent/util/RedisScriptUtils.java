package com.raindrop.idempotent.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @name: com.raindrop.idempotent.util.RedisScriptUtils.java
 * @description: Idempotent token memory storage implement
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
public class RedisScriptUtils {

    /**
     * Get and remove key script
     *
     * @return
     */
    public static RedisScript removeTokenScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("META-INF/scripts/remove_key.lua")));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }

}
