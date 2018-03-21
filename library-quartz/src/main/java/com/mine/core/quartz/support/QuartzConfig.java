package com.mine.core.quartz.support;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ClassName: QuartzConfig
 * Description: //TODO
 * Created by feifei.liu on 2017/9/15 21:03
 **/
public class QuartzConfig {
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
    /**
     * 设置属性
     * @return
     * @throws IOException
     */
    private Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
        prop.put("quartz.scheduler.instanceName", quartzInstanceName);
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
        prop.put("org.quartz.scheduler.jmx.export", "true");

        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.isClustered", "true");

        prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
        prop.put("org.quartz.jobStore.dataSource", "myDS");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.misfireThreshold", "120000");
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE");

        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "10");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

        prop.put("org.quartz.dataSource.myDS.driver", myDSDriver);
        prop.put("org.quartz.dataSource.myDS.URL", myDSURL);
        prop.put("org.quartz.dataSource.myDS.user", myDSUser);
        prop.put("org.quartz.dataSource.myDS.password", myDSPassword);
        System.out.println("myDSMaxConnections:" + myDSMaxConnections);
        prop.put("org.quartz.dataSource.myDS.maxConnections", myDSMaxConnections);

        prop.put("org.quartz.plugin.triggHistory.class", "org.quartz.plugins.history.LoggingJobHistoryPlugin");
        prop.put("org.quartz.plugin.shutdownhook.class", "org.quartz.plugins.management.ShutdownHookPlugin");
        prop.put("org.quartz.plugin.shutdownhook.cleanShutdown", "true");
        return prop;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dialogJobTrigger") Trigger cronJobTrigger) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in javaconfig file:
        //用于quartz集群,QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //用于quartz集群,加载quartz数据源
        //factory.setDataSource(dataSource);
        //QuartzScheduler 延时启动，应用启动完10秒后 QuartzScheduler 再启动
        factory.setStartupDelay(10);
        //用于quartz集群,加载quartz数据源配置
        factory.setQuartzProperties(quartzProperties());
        factory.setAutoStartup(true);
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        //注册触发器
        factory.setTriggers(cronJobTrigger);
        //直接使用配置文件
//        factory.setConfigLocation(new FileSystemResource(this.getClass().getResource("/quartz.properties").getPath()));
        return factory;
    }


    /**
     * 加载job
     * @return
     */
    @Bean
    public JobDetailFactoryBean updateDialogStatusJobDetail() {
        return createJobDetail(InvokingJobDetailDetailFactory.class, "updateDialogStatusGroup", "dialogJob");
    }

    /**
     * 加载触发器
     * @param jobDetail
     * @return
     */
    @Bean(name = "dialogJobTrigger")
    public CronTriggerFactoryBean dialogStatusJobTrigger(@Qualifier("updateDialogStatusJobDetail") JobDetail jobDetail) {
        return dialogStatusTrigger(jobDetail, "0 0 0/1 * * ?");
    }

    /**
     * 创建job工厂
     * @param jobClass
     * @param groupName
     * @param targetObject
     * @return
     */
    private static JobDetailFactoryBean createJobDetail(Class<?> jobClass, String groupName, String targetObject) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        factoryBean.setGroup(groupName);
        Map<String, String> map = new HashMap<>();
        map.put("targetObject", targetObject);
        map.put("targetMethod", "execute");
        factoryBean.setJobDataAsMap(map);
        return factoryBean;
    }

    /**
     * 创建触发器工厂
     * @param jobDetail
     * @param cronExpression
     * @return
     */
    private static CronTriggerFactoryBean dialogStatusTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression (cronExpression);
        return factoryBean;
    }
}
