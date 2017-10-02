package com.library.library.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuff on 2017/3/29.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoService demoService = context.getBean(DemoService.class);
        demoService.outputResult();
        context.close();
    }
}
