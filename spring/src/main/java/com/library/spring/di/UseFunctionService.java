package com.library.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/3/27.
 */
@Service
public class UseFunctionService {
    //使用@Autowired将FunctionService的实体Bean注入到UseFunctionService中
    @Autowired
    FunctionService functionService;

    public String sayHello(String word) {
        return functionService.sayHello(word);
    }
}
