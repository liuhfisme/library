package com.eureka.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/10/18.
 */
@RestController
public class InvokerControllerr {

    @Autowired RestTemplate restTemplate;

    @RequestMapping("/router")
    public String router() {
        //根据应用名称调用服务
        String json = restTemplate.getForObject( "http://eureka-service-provider-a/hello", String.class);
        return json;
    }
}
