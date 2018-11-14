package com.library.scheduler;

import com.library.scheduler.core.jpa.support.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ScheduleApplication
 * Description: //TODO
 * Created by feifei.liu on 2017/9/15 21:48
 **/
@RestController
@SpringBootApplication //Spring Boot项目的核心注解，主要目的是开启自动配置
@ComponentScan("com.library")
@EnableAutoConfiguration
@EnableTransactionManagement //开启声明式事务支持，自动扫描注解@Transactional
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class,basePackages = {"com.library"})
public class ScheduleApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ScheduleApplication.class);
        springApplication.run(args);
    }
}
