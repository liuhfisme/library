package com.mine.library.sboot;

import com.mine.library.sboot.authorsettings.AuthorSettings;
import com.mine.library.sboot.datarest.MyRepositoryRestMvcConfiguration;
import com.mine.library.sboot.jpa.support.CustomRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuff on 2017/4/4.
 */
@RestController
@SpringBootApplication //Spring Boot项目的核心注解，主要目的是开启自动配置
@ComponentScan("com.mine.library.sboot")
@EnableAutoConfiguration
@EnableTransactionManagement //开启声明式事务支持，自动扫描注解@Transactional
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class,basePackages = {"com.mine.library.sboot"})
@Import({MyRepositoryRestMvcConfiguration.class})
public class LibraryApplication {
    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;
    @Autowired
    private AuthorSettings authorSettings;

    @RequestMapping("/")
    String index() {
        //return "Hello Spring Boot !";
        //return "book name is:"+bookName+" and book author is:"+bookAuthor;
        return "author name is "+authorSettings.getName()+" and author age is "+authorSettings.getAge();
    }

    public static void main(String[] args) {
//        SpringApplication.run(LibraryApplication.class, args);
        SpringApplication application = new SpringApplication(LibraryApplication.class);
//        application.setBannerMode(Banner.Mode.OFF); // 关闭 banner
        application.run(args);
    }
}
