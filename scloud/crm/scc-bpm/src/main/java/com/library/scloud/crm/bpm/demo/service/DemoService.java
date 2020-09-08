//package com.library.scloud.crm.bpm.demo.service;
//
//import com.library.scloud.crm.bpm.demo.uitls.SecurityUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.activiti.api.runtime.shared.query.Page;
//import org.activiti.api.runtime.shared.query.Pageable;
//import org.activiti.api.task.model.Task;
//import org.activiti.api.task.model.builders.TaskPayloadBuilder;
//import org.activiti.api.task.runtime.TaskRuntime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * 示例 Service.
// *
// * @author liufefei02@beyondsoft.com
// * @version 1.0
// * @date 2019-03-04
// */
//@Service
//@Slf4j
//public class DemoService {
//    @Autowired
//    private TaskRuntime taskRuntime;
//
//    @Autowired
//    private SecurityUtil securityUtil;
//
//    public void demo() {
//        securityUtil.logInAs("salaboy");
//        log.info("> Creating a Group Task for 'activitiTeam'");
//        taskRuntime.create(
//            TaskPayloadBuilder.create()
//                .withName("First Team Task")
//                .withDescription("This is something really important")
//                .withCandidateGroup("activitiTeam")
//                .withPriority(10)
//                .build()
//        );
//        securityUtil.logInAs("other");
//        log.info("> Getting all the tasks");
//        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
//        log.info(">  Other cannot see the task: " + tasks.getTotalItems());
//        securityUtil.logInAs("erdemedeiros");
//        log.info("> Getting all the tasks");
//        tasks = taskRuntime.tasks(Pageable.of(0, 10));
//        log.info(">  erdemedeiros can see the task: " + tasks.getTotalItems());
//
//        String availableTaskId = tasks.getContent().get(0).getId();
//        log.info("> Claiming the task");
//        taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(availableTaskId).build());
//
//        log.info("> Completing the task");
//        taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(availableTaskId).build());
//    }
//}