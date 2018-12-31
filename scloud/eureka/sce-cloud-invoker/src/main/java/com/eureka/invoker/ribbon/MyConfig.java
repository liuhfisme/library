package com.eureka.invoker.ribbon;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: MyConfig
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/12/28 17:05
 */
public class MyConfig {
    @Bean
    public IRule getRule() {
        return new MyRule();
    }

    @Bean
    public IPing getPing() {
        return new MyPing();
    }

    @RibbonClient(name = "eureka-cloud-service-provider", configuration = MyConfig.class)
    public class CloudProviderConfig {

    }
}
