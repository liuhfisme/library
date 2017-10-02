package com.library.library.spring.annotation;

import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/3/29.
 */
@Service
public class DemoService {
    public void outputResult() {
        System.out.println("从组合注解配置照样获得的bean");
    }
}
