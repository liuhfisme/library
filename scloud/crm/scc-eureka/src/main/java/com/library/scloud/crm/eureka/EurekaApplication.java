package com.library.scloud.crm.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-23
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnableEurekaServer.class, args);
    }
}