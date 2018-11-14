package com.library.spring.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/3/27.
 */
@Service
public class UseFunctionService {
    @Autowired
    FunctionService functionService;
    public String sayHello(String word) {
        return functionService.sayHello(word);
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }
}
