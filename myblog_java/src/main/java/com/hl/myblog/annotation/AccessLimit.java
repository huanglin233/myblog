package com.hl.myblog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口访问频繁访问限制注解
 * 
 * @author huanglin
 * @data   2021年1月8日-下午10:21:37
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    /*秒数*/
    int seconds();
    /*访问次数*/
    int maxCount();
}