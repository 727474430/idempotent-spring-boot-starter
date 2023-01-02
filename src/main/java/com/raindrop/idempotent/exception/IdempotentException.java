package com.raindrop.idempotent.exception;

/**
 * @name: com.raindrop.idempotent.exception.IdempotentException.java
 * @description: 幂等异常
 * @author: Wang Liang
 * @create Time: 2022/12/25 21:42
 * @copyright: 深圳拓保软件有限公司
 */
public class IdempotentException extends RuntimeException {

    /**
     * IdempotentException
     *
     * @param message error message
     */
    public IdempotentException(String message) {
        super(message);
    }

}
