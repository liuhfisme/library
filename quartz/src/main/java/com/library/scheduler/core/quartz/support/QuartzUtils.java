package com.library.scheduler.core.quartz.support;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 
 * ClassName: QuartzUtils
 * @Description: Quartz2.x
 * @author zhibin.zhu
 * @date 2015年11月4日 下午3:37:20
 */
@SuppressWarnings("unused")
public class QuartzUtils {
		private static SchedulerFactory sf = new StdSchedulerFactory();
	    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
		private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
	    private Scheduler scheduler;
	     
	    public static final String DATA_KEY = "STATE_DATA";
	    
	    /**
	     * 
	     * @Description: 添加任务
	     * @param name
	     * @param group
	     * @param clazz
	     * @param cronExpression   
	     * @return void  
	     * @throws
	     * @author zhibin.zhu
	     * @date 2015年11月4日 下午3:40:08
	     */
	    public static void addJob(String name, String group, Class<? extends Job> clazz, String cronExpression) {
	        try {      
	            //构造任务
	            JobDetail job = newJob(clazz).withIdentity(name, group).build();
	             
	            //构造任务触发器
	            Trigger trg = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression)).build();

	            //将作业添加到调度器
	            Scheduler schedulers = sf.getScheduler();
	            schedulers.scheduleJob(job, trg);
	            schedulers.start();
//	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "]");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
//	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
	        }
	    }
	    /**
	     *
	     * @Description: 自动化的添加任务
	     * @param @param name
	     * @param @param group
	     * @param @param clazz
	     * @param @param cronExpression
	     * @param @param map
	     * @return void
	     * @throws
	     * @author zhibin.zhu
	     * @date 2016年3月1日
	     */
		public static void addJob(String name, String group, Class<? extends Job> clazz, String cronExpression, Map<String,Object> map) {
	        try {
	            //构造任务
	            JobDetail job = newJob(clazz).withIdentity(name, group).build();

	            if(map.get("email_user")!=null && !"".equals(map.get("email_user"))){
	            	job.getJobDataMap().put("email_user", map.get("email_user"));
	            }

	            if(map.get("tableName")!=null && !"".equals(map.get("tableName"))){
	            	job.getJobDataMap().put("tableName", map.get("tableName"));
	            }
	            if(map.get("projectId")!=null && !"".equals(map.get("projectId"))){
	            	job.getJobDataMap().put("projectId", map.get("projectId"));
	            }
	            if(map.get("functionId")!=null && !"".equals(map.get("functionId"))){
	            	job.getJobDataMap().put("functionId", map.get("functionId"));
	            }
				if(map.get("reportFormPlanId")!=null && !"".equals(map.get("reportFormPlanId"))){
					job.getJobDataMap().put("reportFormPlanId", map.get("reportFormPlanId"));
				}
				if(map.get("ewsAutomationId")!=null && !"".equals(map.get("ewsAutomationId"))){
					job.getJobDataMap().put("ewsAutomationId", map.get("ewsAutomationId"));
				}
				if(map.get("ewsTaskId")!=null && !"".equals(map.get("ewsTaskId"))){
					job.getJobDataMap().put("ewsTaskId", map.get("ewsTaskId"));
				}
	            //构造任务触发器
	            Trigger trg = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression)).build();

	            //将作业添加到调度器
	            Scheduler schedulers = sf.getScheduler();
	            schedulers.scheduleJob(job, trg);
	            schedulers.start();
//	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "]");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
//	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
	        }
	    }
	    /**
	     *
	     * @Description: 添加任务发邮件
	     * @param name
	     * @param group
	     * @param clazz
	     * @param cronExpression
	     * @return void
	     * @throws
	     * @author zhibin.zhu
	     * @date 2015年11月4日 下午3:40:08
	     */
	    public static void addJobTaskEmail(String name, String group, Class<? extends Job> clazz, String cronExpression, String email) {
	        try {
	            //构造任务
	            JobDetail job = newJob(clazz).withIdentity(name, group).build();
	            job.getJobDataMap().put("email", email);
	            //构造任务触发器
	            Trigger trg = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression)).build();

	            //将作业添加到调度器
	            Scheduler schedulers = sf.getScheduler();
	            schedulers.scheduleJob(job, trg);
	            schedulers.start();
//	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "] ");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
//	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
	        }
	    }
//	    /**
//	     *
//	     * @Description: 添加任务发消息
//	     * @param name
//	     * @param group
//	     * @param clazz
//	     * @param cronExpression
//	     * @return void
//	     * @throws
//	     * @author zhibin.zhu
//	     * @date 2015年11月4日 下午3:40:08
//	     */
//	    public static void addJobTaskMsg(String name, String group, Class<? extends Job> clazz, String cronExpression, QixinMessage qixinmsg, String userId) {
//	        try {
//	            //构造任务
//	            JobDetail job = newJob(clazz).withIdentity(name, group).build();
//	            JSONObject qixinmsgJson = JSONObject.fromObject(qixinmsg);
//	            job.getJobDataMap().put("qixinmsgJson", qixinmsgJson);
//	            job.getJobDataMap().put("userId", userId);
//	            //构造任务触发器
//	            Trigger trg = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression)).build();
//
//	            //将作业添加到调度器
//	            Scheduler schedulers = sf.getScheduler();
//	            schedulers.scheduleJob(job, trg);
//	            schedulers.start();
////	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "]");
//	        } catch (SchedulerException e) {
//	            e.printStackTrace();
////	            System.out.println("创建作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
//	        }
//	    }
	    /**
	     * 
	     * @Description: 清空回收站的任务
	     * @param    
	     * @return void  
	     * @throws
	     * @author zhibin.zhu
	     * @date 2016年6月22日
	     *//*
	    public static void addRecycleBinJob(){
	    	removeJob("清空回收站", "清空回收站");
	    	addJob("清空回收站", "清空回收站", new RecycleBinJob().getClass(), "0 30 1 * * ?");
	    }*/
	    /**
	     * 
	     * @Description: 删除任务
	     * @param name
	     * @param group   
	     * @return void  
	     * @throws
	     * @author zhibin.zhu
	     * @date 2015年11月4日 下午3:40:38
	     */
	    public static void removeJob(String name, String group){
	        try {
	            TriggerKey tk = TriggerKey.triggerKey(name, group);
	            Scheduler schedulers = sf.getScheduler();
	            if(tk!=null){
	            	schedulers.pauseTrigger(tk);//停止触发器  
	 	            schedulers.unscheduleJob(tk);//移除触发器
	            }
	            JobKey jobKey = JobKey.jobKey(name, group);
	            if(jobKey!=null){
	            	schedulers.deleteJob(jobKey);//删除作业
	            }
//	            System.out.println("删除作业=> [作业名称：" + name + " 作业组：" + group + "] ");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
//	            System.out.println("删除作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
	        }
	    }
	    /**
	     * 
	     * @Description: 暂停任务
	     * @param name
	     * @param group   
	     * @return void  
	     * @throws
	     * @author zhibin.zhu
	     * @date 2015年11月4日 下午3:40:54
	     */
	    public static void pauseJob(String name, String group){
	        try {
	            JobKey jobKey = JobKey.jobKey(name, group);
	            Scheduler schedulers = sf.getScheduler();
	            schedulers.pauseJob(jobKey);
//	            System.out.println("暂停作业=> [作业名称：" + name + " 作业组：" + group + "]");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
//	            System.out.println("暂停作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
	        }
	    }
	    /**
	     * 
	     * @Description: 恢复任务对应暂停任务
	     * @param name
	     * @param group   
	     * @return void  
	     * @throws
	     * @author zhibin.zhu
	     * @date 2015年11月4日 下午3:41:11
	     */
	    public static void resumeJob(String name, String group){
	        try {
	            JobKey jobKey = JobKey.jobKey(name, group);
	            Scheduler schedulers = sf.getScheduler();
	            schedulers.resumeJob(jobKey);
//	            System.out.println("恢复作业=> [作业名称：" + name + " 作业组：" + group + "]");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
//	            System.out.println("恢复作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
	        }       
	    }
	    /**
	     *  
	     * @Description: 修改任务
	     * @param name
	     * @param group
	     * @param cronExpression   
	     * @return void  
	     * @throws
	     * @author zhibin.zhu
	     * @date 2015年11月4日 下午3:41:48
	     */
	    public static void modifyTime(String name, String group, String cronExpression){
	        try {
	            TriggerKey tk = TriggerKey.triggerKey(name, group);
	            //构造任务触发器
	            Trigger trg = newTrigger()
	                    .withIdentity(name, group)
	                    .withSchedule(cronSchedule(cronExpression))
	                    .build();
	            Scheduler schedulers = sf.getScheduler();
	            schedulers.rescheduleJob(tk, trg);
	            schedulers.start();
//	            System.out.println("修改作业触发时间=> [作业名称：" + name + " 作业组：" + group + "]");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
//	            System.out.println("修改作业触发时间=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
	        }
	    }

	    /**
	     * 
	     * @Description: 停止任务   
	     * @return void  
	     * @throws
	     * @author zhibin.zhu
	     * @date 2015年11月4日 下午3:42:35
	     */
	    public static void shutdown() {
	        try {
	        	Scheduler schedulers = sf.getScheduler();
	        	schedulers.shutdown();
	        	System.out.println("停止调度器 ");
	        } catch (SchedulerException e) {
	            e.printStackTrace();
	            System.out.println("停止调度器=> [失败]");
	        }
	    }
	    
		public Scheduler getScheduler() {
			return scheduler;
		}
		public void setScheduler(Scheduler scheduler) {
			this.scheduler = scheduler;
		}

}