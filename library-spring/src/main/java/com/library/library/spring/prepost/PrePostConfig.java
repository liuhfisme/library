package com.library.library.spring.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuff on 2017/3/28.
 */
@Configuration
@ComponentScan("com.library.library.spring.prepost")
public class PrePostConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy") //指定BeanWayService类的init和destroy方法在构造之后、Bean销毁之前执行
    BeanWayService beanWayService() {
        return new BeanWayService();
    }

    @Bean
    JSR250WaryService jsr250WaryService() {
        return new JSR250WaryService();
    }
}
