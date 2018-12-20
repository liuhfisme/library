package com.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName: EurekaServerApplication
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/10/19 14:57
 */
@SpringBootApplication
@EnableEurekaServer //声明这个一个Eureka服务器
public class EurekaCloudServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCloudServerApplication.class, args);
    }
}
