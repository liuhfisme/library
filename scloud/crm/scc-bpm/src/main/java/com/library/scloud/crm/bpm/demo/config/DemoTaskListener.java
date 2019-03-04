package com.library.scloud.crm.bpm.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.api.task.runtime.events.TaskAssignedEvent;
import org.activiti.api.task.runtime.events.TaskCompletedEvent;
import org.activiti.api.task.runtime.events.listener.TaskRuntimeEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监听器- 任务.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-04
 */
@Slf4j
@Configuration
public class DemoTaskListener {
    @Bean
    public TaskRuntimeEventListener<TaskAssignedEvent> taskAssignedListener() {
        return taskAssigned -> log.info(">>> Task Assigned：'" +
                taskAssigned.getEntity().getName() +
                "' We can send a notificationo to the assginee：" +
                taskAssigned.getEntity().getAssignee());
    }

    @Bean
    public TaskRuntimeEventListener<TaskCompletedEvent> taskCompletedListener() {
        return taskCompleted -> log.info(">>> Task Completed：'" +
                taskCompleted.getEntity().getName() +
                "' We can send a notification to the owner：" +
                taskCompleted.getEntity().getOwner());
    }
}