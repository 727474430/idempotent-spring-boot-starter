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
     * Token header name. default token
     */
    private String tokenHeader = "token";

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

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }
}
