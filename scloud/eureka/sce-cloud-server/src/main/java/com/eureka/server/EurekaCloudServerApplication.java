package com.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServerApplication
 * @Description Eureka服务器
 * @author feifei.liu
 * @date 2018/10/19 14:57
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaCloudServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCloudServerApplication.class, args);
    }
}
