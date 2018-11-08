package com.xiaoyong.hrm.support.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by atlantisholic on 2018/9/19.
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Encryption {

    String value() default "";

}
