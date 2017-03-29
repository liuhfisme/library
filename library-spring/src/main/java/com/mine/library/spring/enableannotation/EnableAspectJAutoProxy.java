package com.mine.library.spring.enableannotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by liuff on 2017/3/29.
 * 动态注册Bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {
    boolean proxyTargetClass() default false;
}
