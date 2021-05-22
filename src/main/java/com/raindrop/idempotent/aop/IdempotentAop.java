package com.raindrop.idempotent.aop;

import cn.hutool.core.util.StrUtil;
import com.raindrop.idempotent.anno.Idempotent;
import com.raindrop.idempotent.util.IdempotentTokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        Object target = pjp.getTarget();
        String methodName = pjp.getSignature().toString();
        String argsList = StrUtil.join(",", pjp.getArgs());
        String token = IdempotentTokenUtils.tokenGenerate(methodName + argsList);

        boolean success = IdempotentTokenUtils.setIfAbsent(token, idempotent.timeout(), idempotent.timeUnit());
        if (!success) {
            logger.info("方法 {}.{} 参数 {} 存在重复的请求，无法通过幂等校验！",
                    target.getClass().getSimpleName(), methodName, argsList);
            throw new IllegalArgumentException(idempotent.tips());
        }

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.error("Idempotent aop around process error, method name: {}", methodName, e.getMessage());
        } finally {
            if (idempotent.delKey()) {
                IdempotentTokenUtils.remove(token);
            }
        }
        return result;
    }

}
