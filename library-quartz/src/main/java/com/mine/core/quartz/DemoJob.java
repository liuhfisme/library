package com.mine.core.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

/**
 * ClassName: DemoJob
 * Description: //TODO
 * Created by feifei.liu on 2017/6/14 17:15
 **/
public class DemoJob implements Job {
    @Override
    public void execute(JobExecutionContext executionContext) throws JobExecutionException {
        JobDataMap dataMap = executionContext.getJobDetail().getJobDataMap();
        System.out.println("任务被定时执行了！！！！！！！！");
        for (Map.Entry<String, Object> entry:dataMap.entrySet()) {
            System.out.println(entry.getKey()+"::"+entry.getValue());
        }
    }
}
