package com.eureka.health.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //声明这个一个Eureka服务器
public class EurekaHealthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaHealthServerApplication.class, args);
	}
}
