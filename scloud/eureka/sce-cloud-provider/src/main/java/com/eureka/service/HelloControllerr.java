package com.eureka.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by Administrator on 2018/10/17.
 */
@RestController
public class HelloControllerr {

    @RequestMapping("/hello")
    public HelloWorld hello(HttpServletRequest request) {
        HelloWorld helloWorld = new HelloWorld(
                UUID.randomUUID().toString().replace("-",""),
                "feifie.liu",
                27,request.getRequestURL().toString());
        return helloWorld;
    }
}
