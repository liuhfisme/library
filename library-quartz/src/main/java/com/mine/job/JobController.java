package com.mine.job;

import com.mine.core.quartz.DemoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * ClassName: JobController
 * Description: //TODO
 * Created by feifei.liu on 2017/9/14 20:24
 **/
@RestController
public class JobController {
    @Autowired
    private DemoTaskService taskService;
    @RequestMapping("/hello")
    public String hello(){
//        taskService.fixTimeExecution();
        taskService.removeJob();
        return "Hello Quartz!";
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
