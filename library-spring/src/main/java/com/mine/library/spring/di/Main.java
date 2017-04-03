package com.mine.library.spring.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuff on 2017/3/27.
 */
public class Main {
    public static void main(String[] args) {
        //使用AnnotationConfigApplicationContext作为Spring容器，接受一个配置类作为参数
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        //获得声明配置的UseFunctionService的Bean
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.sayHello("DI"));
        context.close();
    }
}
