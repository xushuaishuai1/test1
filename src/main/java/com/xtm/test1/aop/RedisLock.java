package com.xtm.test1.aop;

import java.lang.annotation.*;

/**
 * 用于标记redis锁
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    String value() default "";

    int time() default 30;
}