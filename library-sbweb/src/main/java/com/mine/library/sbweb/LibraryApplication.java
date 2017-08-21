package com.mine.library.sbweb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuff on 2017/4/4.
 */
@RestController
@SpringBootApplication //Spring Boot项目的核心注解，主要目的是开启自动配置
@ComponentScan("com.mine.library.sbweb")
@EnableAutoConfiguration
public class LibraryApplication {
    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;

    public static void main(String[] args) {
//        SpringApplication.run(LibraryApplication.class, args);
        SpringApplication application = new SpringApplication(LibraryApplication.class);
//        application.setBannerMode(Banner.Mode.OFF); // 关闭 banner
        application.run(args);
    }
}
