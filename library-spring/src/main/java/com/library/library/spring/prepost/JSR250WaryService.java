package com.library.library.spring.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by liuff on 2017/3/28.
 */
public class JSR250WaryService {

    @PostConstruct //在构造函数执行完之后执行
    public void init() {
        System.out.println("jsr250-init-method");
    }

    public JSR250WaryService(){
        super();
        System.out.println("初始化构造函数-JSR250WayService");
    }

    @PreDestroy //在Bean销毁之前执行
    public void destroy() {
        System.out.println("jsr250-destroy-method");
    }
}
