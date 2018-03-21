package com.library.library.spring.el;

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
@ComponentScan("com.library.library.spring.el")
@PropertySource("classpath:com/library/library/spring/el/demo.properties") //使用@PropertySource注入配置文件
public class ElConfig {
    @Value("I Love You！") //注入普通字符
    private String normal;

    @Value("#{systemProperties['os.name']}") //注入操作系统属性
    private String osName;

    @Value("#{ T(java.lang.Math).random()*100.0}") //注入表达式结果
    private String randomNumber;

    @Value("#{demoService.another}") //输入其它Bean属性
    private String fromAnother;

    @Value("classpath:com/library/library/spring/el/demo.txt") //输入文件资源
    private Resource demoFile;

    @Value("http://www.baidu.com") //注入网址资源
    private Resource demoUrl;

    @Value("${book.name}") //注入配置文件属性
    private String bookName;

    @Autowired
    private Environment environment; //注入Spring环境容器environment

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
