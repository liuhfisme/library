package com.mine.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Jenkins自动构建示例.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-04-22
 */
@SpringBootApplication
public class JenkinsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JenkinsApplication.class, args);
    }
}