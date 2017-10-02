package com.library.library.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by liuff on 2017/3/27.
 */
@Configuration
@ComponentScan("com.library.library.spring.aop")
@EnableAspectJAutoProxy // 注解开启Spring对AspectJ的支持
public class AopConfig {
}
