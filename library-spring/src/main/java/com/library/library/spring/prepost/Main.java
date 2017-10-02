package com.library.library.spring.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuff on 2017/3/28.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
        BeanWayService beanWayService = context.getBean(BeanWayService.class);
        JSR250WaryService jsr250WaryService = context.getBean(JSR250WaryService.class);
        context.close();
    }
}
