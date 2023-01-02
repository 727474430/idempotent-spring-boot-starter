package com.raindrop.idempotent.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @name: com.raindrop.idempotent.anno.Idempotent.java
 * @description: Interface idempotent label
 * @author: Raindrop
 * @create Time: 2020/5/17 10:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {

    /**
     * Idempotent check timeout. default 10 seconds
     *
     * @return
     */
    long timeout() default 10L;

    /**
     * Timeout time unit. default seconds
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * Tips message
     *
     * @return
     */
    String tips() default "请求失败，重复请求接口，请稍后再试！";

    /**
     * End of execution, delete key, default not delete
     *
     * @return
     */
    boolean delKey() default false;

}