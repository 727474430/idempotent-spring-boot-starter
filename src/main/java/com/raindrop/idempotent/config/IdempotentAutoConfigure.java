package com.raindrop.idempotent.config;

import com.raindrop.idempotent.aop.IdempotentAop;
import com.raindrop.idempotent.base.IdempotentToken;
import com.raindrop.idempotent.model.MemoryIdempotentToken;
import com.raindrop.idempotent.model.RedisIdempotentToken;
import com.raindrop.idempotent.properties.IdempotentProperties;
import com.raindrop.idempotent.util.IdempotentTokenUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @name: com.raindrop.idempotent.config.IdempotentAop.java
 * @description: Idempotent starter auto configure
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
@Configuration
@ConditionalOnClass()
@EnableConfigurationProperties(IdempotentProperties.class)
@ConditionalOnProperty(prefix = "idempotent", value = "enable", havingValue = "true")
public class IdempotentAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(IdempotentAop.class)
    public IdempotentAop idempotentAop() {
        return new IdempotentAop();
    }

    @Bean
    @ConditionalOnMissingBean(IdempotentTokenUtils.class)
    public IdempotentTokenUtils tokenUtils() {
        return new IdempotentTokenUtils();
    }

    @Bean
    @ConditionalOnProperty(prefix = "idempotent", value = "tokenStorage", havingValue = "memory", matchIfMissing = true)
    public IdempotentToken memoryIdempotentToken() {
        return new MemoryIdempotentToken();
    }

    @Bean
    @ConditionalOnProperty(prefix = "idempotent", value = "tokenStorage", havingValue = "redis")
    public IdempotentToken redisIdempotentToken(StringRedisTemplate redisTemplate) {
        return new RedisIdempotentToken(redisTemplate);
    }

}
