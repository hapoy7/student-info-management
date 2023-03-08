package com.ldr.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ldr.common.base.key.IdType;

/**
 * @summary 【主键字段】标记注解
 * @author 高振中
 * @date 2022-05-22 10:00:00
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    String value() default IdType.AUTO;/* 默认自增主键 */
}
