package com.raindrop.idempotent.properties;

import com.raindrop.idempotent.enums.TokenStorageEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @name: com.raindrop.idempotent.properties.IdempotentProperties.java
 * @description: Idempotent starter properties
 * @author: Raindrop
 * @create Time: 2020/05/12 11:00 PM
 */
@ConfigurationProperties("idempotent")
public class IdempotentProperties {

    /**
     * Whether open idempotent
     */
    private Boolean enable = Boolean.FALSE;
    /**
     * Token storage type. default memory
     */
    private TokenStorageEnum tokenStorage = TokenStorageEnum.MEMORY;
    /**
     * Redis host address
     */
    private String redisHost;
    /**
     * Redis host port
     */
    private Integer redisPort;
    /**
     * Redis database number
     */
    private Integer redisDatabase;
    /**
     * Redis auth password
     */
    private String redisPassword;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public TokenStorageEnum getTokenStorage() {
        return tokenStorage;
    }

    public void setTokenStorage(TokenStorageEnum tokenStorage) {
        this.tokenStorage = tokenStorage;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public Integer getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(Integer redisPort) {
        this.redisPort = redisPort;
    }

    public Integer getRedisDatabase() {
        return redisDatabase;
    }

    public void setRedisDatabase(Integer redisDatabase) {
        this.redisDatabase = redisDatabase;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public void setRedisPassword(String redisPassword) {
        this.redisPassword = redisPassword;
    }

}
