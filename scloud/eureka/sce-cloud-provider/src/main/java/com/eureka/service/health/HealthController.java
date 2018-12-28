package com.eureka.service.health;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HealthController
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/12/28 15:30
 */
@RestController
public class HealthController {
    // 标识当前数据库是否可以访问
    static Boolean canVisitDb = false;

    @RequestMapping(value = "/db/{canVisitDb}", method = RequestMethod.GET)
    public String setConnectState(@PathVariable("canVisitDb") Boolean canVisitDb) {
        this.canVisitDb = canVisitDb;
        return "当前数据库是否正常："+this.canVisitDb;
    }
}
