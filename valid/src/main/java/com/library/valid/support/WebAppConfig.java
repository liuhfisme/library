package com.library.valid.support;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-05
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleApiSignInterceptor()).addPathPatterns("/o/payroll/**");
    }

}