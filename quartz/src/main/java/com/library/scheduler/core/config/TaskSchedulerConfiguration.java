package com.library.scheduler.core.config;

import com.library.scheduler.core.quartz.support.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * ClassName: TaskSchedulerConfiguration
 * @Description: 计划任务配置类
 * @author feifei.liu
 * @date 2017/6/6 20:41
 */
@Configuration
@EnableScheduling
public class TaskSchedulerConfiguration {

    @Value("classpath:/quartz.properties")
    private Resource resource;

    @Bean
    public Scheduler schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setConfigLocation(resource);
        schedulerFactoryBean.setAutoStartup(true); //是否随项目启动
        schedulerFactoryBean.setStartupDelay(60); //延迟60秒启动Scheduler
        return schedulerFactoryBean.getScheduler();
    }

    @Bean
    public QuartzUtils quartzUtils() {
        QuartzUtils quartzUtils = new QuartzUtils();
        quartzUtils.setScheduler(schedulerFactoryBean());
        return quartzUtils;
    }
}
