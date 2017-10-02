package com.library.library.spring.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuff on 2017/3/27.
 */
@Configuration //声明一个配置类，此处没有用到包扫描，是因为所有的Bean都在此类中定义了
public class JavaConfig {

    @Bean //使用@Bean注解说明当前方法functionService的返回值是一个Bean，Bean的名称为方法名
    public FunctionService functionService() {
        return new FunctionService();
    }

    @Bean //通过setFunctionService方法将上面声明好的Bean注入到UseFunctionService类中
    public UseFunctionService  useFunctionService() {
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService());
        return useFunctionService;
    }

    /*@Bean 另外一种注入方式，直接传参数
    public UseFunctionService  useFunctionService(FunctionService functionService) {
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService);
        return useFunctionService;
    }*/

}
