package com.raindrop.idempotent.aop;

import com.raindrop.idempotent.util.IdempotentTokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: com.raindrop.idempotent.aop.IdempotentAop.java
 * @description: Aspect to idempotent form api
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
@Aspect
public class IdempotentAop {

    private static Logger logger = LoggerFactory.getLogger(IdempotentAop.class);

    private String tokenHeader;

    public IdempotentAop(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public IdempotentAop() {

    }

    @Pointcut("@annotation(com.raindrop.idempotent.anno.Idempotent)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isEmpty(token)) {
            return "请求失败，重复请求接口！";
        }

        boolean tokenExists = IdempotentTokenUtils.tokenCheck(token);
        if (!tokenExists) {
            return "请求失败，错误的令牌！";
        }

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            logger.error("Idempotent aop around process error: \n{}", throwable.getMessage());
        }
        return result;
    }


}
