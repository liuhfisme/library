package com.library.spring.prepost;

/**
 * Created by liuff on 2017/3/28.
 */
public class BeanWayService {

    public void init() { //相当于xml配置的init-method
        System.out.println("@Bean-init-method");
    }

    public BeanWayService(){
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }

    public void destroy() { //相当于xml配置的init-destroy
        System.out.println("@Bean-destroy-method");
    }
}
