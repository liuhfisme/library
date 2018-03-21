package com.mine.core.quartz.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: QuartzProperty
 * Description: //TODO
 * Created by feifei.liu on 2017/9/15 21:07
 **/
@Component
@ConfigurationProperties(prefix = "quartz")
public class QuartzProperty {
    @Value("${quartz.scheduler.instanceName}")
    private String quartzInstanceName;

    @Value("${org.quartz.dataSource.myDS.driver}")
    private String myDSDriver;

    @Value("${org.quartz.dataSource.myDS.URL}")
    private String myDSURL;

    @Value("${org.quartz.dataSource.myDS.user}")
    private String myDSUser;

    @Value("${org.quartz.dataSource.myDS.password}")
    private String myDSPassword;

    @Value("${org.quartz.dataSource.myDS.maxConnections}")
    private String myDSMaxConnections;
}
