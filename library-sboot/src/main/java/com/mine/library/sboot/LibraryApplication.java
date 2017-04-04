package com.mine.library.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuff on 2017/4/4.
 */
@RestController
@SpringBootApplication //Spring Boot项目的核心注解，主要目的是开启自动配置
public class LibraryApplication {
    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot !";
    }

    public static void main(String[] args) {
//        SpringApplication.run(LibraryApplication.class, args);
        SpringApplication application = new SpringApplication(LibraryApplication.class);
//        application.setBannerMode(Banner.Mode.OFF); // 关闭 banner
        application.run(args);
    }
}
