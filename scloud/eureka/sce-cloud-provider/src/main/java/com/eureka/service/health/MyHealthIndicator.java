package com.eureka.service.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyHealthIndicator
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/12/28 15:29
 */
@Component
public class MyHealthIndicator implements HealthIndicator {
    private static final Logger log = LoggerFactory.getLogger(MyHealthIndicator.class);

    @Override
    public Health health() {
        if (HealthController.canVisitDb) {
            log.debug("health debug, up. canVisitDb:[{}]", HealthController.canVisitDb);
            return new Health.Builder(Status.UP).build();
        } else {
            log.warn("health debug, down. canVisitDb:[{}]", HealthController.canVisitDb);
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
