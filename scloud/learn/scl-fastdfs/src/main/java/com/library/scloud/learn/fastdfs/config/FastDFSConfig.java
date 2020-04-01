package com.library.scloud.learn.fastdfs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 配置.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-04-01
 */
@Configuration
@Import(FdfsClientConfig.class)
/**
 * 解决jmx重复注册bean的问题
 */
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDFSConfig {
}
