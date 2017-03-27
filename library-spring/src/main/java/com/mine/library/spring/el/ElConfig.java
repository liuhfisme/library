package com.mine.library.spring.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * Created by liuff on 2017/3/27.
 */
@Configuration
@ComponentScan("com.mine.library.spring.el")
@PropertySource("classpath:com/mine/library/spring/el/demo.properties")
public class ElConfig {
    @Value("I Love You！")
    private String normal;
    @Value("#{systemProperties['os.name']}")
    private String osName;
    @Value("#{ T(java.lang.Math).random()*100.0}")
    private String randomNumber;
    @Value("#{demoService.another}")
    private String fromAnother;
    @Value("classpath:com/mine/library/spring/el/demo.txt")
    private Resource demoFile;
    @Value("http://www.baidu.com")
    private Resource demoUrl;
    @Value("${book.name}")
    private String bookName;

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(demoFile.getInputStream()));
            System.out.println(IOUtils.toString(demoUrl.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
