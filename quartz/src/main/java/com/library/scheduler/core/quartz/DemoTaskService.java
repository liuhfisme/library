package com.library.scheduler.core.quartz;

import com.library.scheduler.core.quartz.support.QuartzUtils;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: ScheduledTaskService
 * Description: //TODO
 * Created by feifei.liu on 2017/6/13 14:03
 **/
@Service
public class DemoTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 10000) //使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime() {
        System.out.println("每隔五秒执行一次 " + dateFormat.format(new Date()));
    }

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

//    @Scheduled(cron = "30 46 20 ? * *") //使用cron属性可按照指定时间执行，本例指的是每天23点39分执行
    public void fixTimeExecution() {
        System.out.println("在指定时间 " + dateFormat.format(new Date()) + "执行");

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("hello Job","任务你好!");
        QuartzUtils.addJob("11","xxx",DemoJob.class,"0 24 21 ? * *");
    }
    public void removeJob() {
        QuartzUtils.removeJob("xxx_1","xxx");
        QuartzUtils.removeJob("xxx_2","xxx");
        QuartzUtils.removeJob("xxxxxx_2","xxx");
        QuartzUtils.removeJob("xxxxxxxxx_2","xxx");
        QuartzUtils.removeJob("xxxxxxxxxxx_2","xxx");
        QuartzUtils.removeJob("xxxxxxxxxxx222_2","xxx");
    }
}
