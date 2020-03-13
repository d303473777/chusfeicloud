package com.dzb.springcloud.annotation;

import java.lang.annotation.*;

/**
 * @author Ding ZhenBei
 * @data 2020/3/12 18:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysoLog {
    String value() default "";
}
