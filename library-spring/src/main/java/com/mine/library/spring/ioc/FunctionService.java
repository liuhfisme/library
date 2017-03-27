package com.mine.library.spring.ioc;

import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/3/27.
 */
@Service
public class FunctionService {
    public String sayHello(String word) {
        return "Hello "+word+" !";
    }
}
