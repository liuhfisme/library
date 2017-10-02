package com.library.library.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by feifei.liu on 2017/3/28.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        demoPublisher.publish("Hello Application Event!");
        context.close();
    }
}
