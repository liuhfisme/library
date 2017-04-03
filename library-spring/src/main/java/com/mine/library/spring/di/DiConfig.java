package com.mine.library.spring.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuff on 2017/3/27.
 */
@Configuration //@Configuration声明当前类是一个配置类
@ComponentScan("com.mine.library.spring.di")
//使用@ComponentScan自动扫描包下所有使用@Service、@Component、@Repository和@Controller的类，并注册为Bean
public class DiConfig {
}
