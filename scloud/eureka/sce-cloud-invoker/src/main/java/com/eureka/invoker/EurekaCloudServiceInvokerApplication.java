package com.eureka.invoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/10/18.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaCloudServiceInvokerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCloudServiceInvokerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
