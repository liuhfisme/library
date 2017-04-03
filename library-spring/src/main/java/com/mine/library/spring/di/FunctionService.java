package com.mine.library.spring.di;

import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/3/27.
 */
@Service //使用@Service注解声明当前FunctionService类是Spring容器管理的一个Bean
public class FunctionService {
    public String sayHello(String word) {
        return "Hello "+word+" !";
    }
}
