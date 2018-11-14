package com.library.spring.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuff on 2017/3/28.
 */
@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000) // 使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime() {
        System.out.println("每隔五秒执行一次 "+dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 39 23 ? * *") //使用cron属性可按照指定时间执行，本例指的是每天23点39分执行
    public void fixTimeExecution() {
        System.out.println("在指定时间 "+dateFormat.format(new Date())+"执行");
    }
}
