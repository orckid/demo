package com.orc.demo.util.annotation;

import com.orc.demo.common.LogOptEnum;

import java.lang.annotation.*;

/**
 * @author orcki
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    String annoValue() default "";

    LogOptEnum optEnum();
}
