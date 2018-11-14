package com.library.spring.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by liuff on 2017/3/28.
 */
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev") //使用@Profile实例化dev环境下的Bean
    public DemoBean devDemoBean() {
        return new DemoBean("from development profile");
    }

    @Bean
    @Profile("prod") //使用@Profile实例化prod环境下的Bean
    public DemoBean prodDemoBean() {
        return new DemoBean("from production profile");
    }
}
