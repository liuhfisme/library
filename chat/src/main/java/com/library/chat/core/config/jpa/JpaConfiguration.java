package com.library.chat.core.config.jpa;

import com.library.chat.core.config.jpa.support.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * ClassName: JpaConfiguration
 * @Description: Spring Data Jpa 配置类
 * <p>repositoryBaseClass指定自定义通用Repository</p>
 * <p>basePackages指定扫描指定包下的repository自动注入</p>
 * @author feifei.liu
 * @date 2017/6/28 18:54
 */
@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class, basePackages = {"com.library"})
public class JpaConfiguration {
}
