package com.library.scloud.crm.repository.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

/**
 * 基础数据仓库配置类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-27
 */
@EnableJpaRepositories
public class RepositoryConfig extends JpaRepositoryConfigExtension {
}