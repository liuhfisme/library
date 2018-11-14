package com.library.spring.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuff on 2017/3/28.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod"); // 先将活动的Profile设置为prod
        context.register(ProfileConfig.class); //后置注册Bean配置类，不然会报Bean未定义的错误
        context.refresh(); //刷新容器

        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());

        /*context.getEnvironment().setActiveProfiles("dev"); // 先将活动的Profile设置为dev
        context.register(ProfileConfig.class); //后置注册Bean配置类，不然会报Bean未定义的错误
        context.refresh(); //刷新容器
        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());*/
        context.close();
    }
}
