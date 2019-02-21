package com.library.valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-17
 */
@SpringBootApplication
public class ValidApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ValidApplication.class);
        application.run(args);
    }
}