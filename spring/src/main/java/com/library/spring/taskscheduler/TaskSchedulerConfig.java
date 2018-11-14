package com.library.spring.taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by liuff on 2017/3/28.
 */
@Configuration
@ComponentScan("com.library.spring.taskscheduler")
@EnableScheduling //开启对计划任务的支持
public class TaskSchedulerConfig {
}
