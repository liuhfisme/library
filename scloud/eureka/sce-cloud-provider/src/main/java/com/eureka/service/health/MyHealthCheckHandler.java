package com.eureka.service.health;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyHealthCheckHandler
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/12/28 15:33
 */
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {
    private static final Logger log = LoggerFactory.getLogger(MyHealthCheckHandler.class);
    @Autowired
    private MyHealthIndicator indicator;

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        Status s = indicator.health().getStatus();
        if (Status.UP.equals(s)) {
            log.debug("health debug. status:[{}]", s);
            return InstanceInfo.InstanceStatus.UP;
        } else {
            log.warn("health warn. status:[{}]", s);
            return InstanceInfo.InstanceStatus.DOWN;
        }
    }
}
