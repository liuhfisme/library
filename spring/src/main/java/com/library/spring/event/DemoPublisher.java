package com.library.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by feifei.liu on 2017/3/28.
 */
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext; //注入ApplicationContext用来发布事件。

    /**
     * 使用ApplicationContext的publishEvent方法来发布
     * @param msg
     */
    public void publish(String msg) {
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }
}
