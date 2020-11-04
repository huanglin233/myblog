package com.hl.myblog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hl.myblog.common.enums.RecordObject;
import com.hl.myblog.common.enums.RecordType;

/**
 * 记录日志注解
 * @author huanglin
 * @data   2020年10月22日-下午11:42:03
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordLog {

    /**
     * 方法描述,可使用站位符获取参数
     */
    String detail() default "";

    /**
     * 记录操作的类型
     */
    RecordType recordType() default RecordType.UNKNOWN;

    /**
     * 记录被操作的对象
     */
    RecordObject recordObject() default RecordObject.UNKNOWN;
}