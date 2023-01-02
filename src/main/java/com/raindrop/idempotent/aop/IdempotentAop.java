package com.raindrop.idempotent.aop;

import cn.hutool.core.util.StrUtil;
import com.raindrop.idempotent.anno.Idempotent;
import com.raindrop.idempotent.exception.IdempotentException;
import com.raindrop.idempotent.util.IdempotentTokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @name: com.raindrop.idempotent.aop.IdempotentAop.java
 * @description: Aspect to idempotent form api
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
@Aspect
public class IdempotentAop {

    private static final Logger logger = LoggerFactory.getLogger(IdempotentAop.class);

    @Around("@annotation(idempotent)")
    public Object around(ProceedingJoinPoint pjp, Idempotent idempotent) {
        String key = getTokenKey(pjp);
        String token = IdempotentTokenUtils.tokenGenerate(key);
        String value = UUID.randomUUID().toString().replaceAll("-", "");

        boolean success = IdempotentTokenUtils.setIfAbsent(token, value, idempotent.timeout(), idempotent.timeUnit());
        if (!success) {
            logger.info("Request repeat, Idempotent check failure, key: {} ", key);
            throw new IdempotentException(idempotent.tips());
        }

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.error("Idempotent aop around process error, key name: {}", key, e);
        } finally {
            if (idempotent.delKey()) {
                IdempotentTokenUtils.remove(token, value);
            }
        }
        return result;
    }

    private String getTokenKey(ProceedingJoinPoint pjp) {
        Object target = pjp.getTarget();
        String methodName = pjp.getSignature().toString();
        String argsList = StrUtil.join(",", pjp.getArgs());
        return methodName + ":" + argsList;
    }

}
