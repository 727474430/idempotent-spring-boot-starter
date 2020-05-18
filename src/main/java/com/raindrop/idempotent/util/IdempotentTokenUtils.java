package com.raindrop.idempotent.util;

import com.raindrop.idempotent.base.IdempotentToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdempotentTokenUtils {

    private static IdempotentToken idempotentToken;

    /**
     * Generate token. default uuid
     *
     * @return
     */
    public static String tokenGenerate() {
        String token = UUID.randomUUID().toString();
        idempotentToken.add(token);
        return token;
    }

    /**
     * Check the token exists
     *
     * @param token
     * @return
     */
    public static boolean tokenCheck(String token) {
        return idempotentToken.check(token);
    }

    @Autowired
    public void setIdempotentToken(IdempotentToken idempotentToken) {
        IdempotentTokenUtils.idempotentToken = idempotentToken;
    }

}
