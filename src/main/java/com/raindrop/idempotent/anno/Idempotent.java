package com.raindrop.idempotent.anno;

import java.lang.annotation.*;

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

}