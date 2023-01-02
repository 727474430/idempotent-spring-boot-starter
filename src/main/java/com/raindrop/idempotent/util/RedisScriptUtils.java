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
     * Redis remove key script. this is an atomic method
     *
     * @return
     */
    public static RedisScript<Long> removeTokenScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("META-INF/scripts/remove_key.lua")));
        return redisScript;
    }

}
