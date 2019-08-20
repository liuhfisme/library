package com.library.scloud.learn.config.server.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Config Client 服务.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-08-19
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${xxx}")
    private String xxx;

    @GetMapping("/test")
    public String test() {
        return "xxx:"+xxx;
    }
}
