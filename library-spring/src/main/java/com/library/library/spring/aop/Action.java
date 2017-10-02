package com.library.library.spring.aop;

import java.lang.annotation.*;

/**
 * Created by liuff on 2017/3/27.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
