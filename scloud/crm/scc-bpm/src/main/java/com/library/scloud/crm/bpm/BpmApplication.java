package com.library.scloud.crm.bpm;

import com.library.scloud.crm.bpm.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BPM启动类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-04
 */
@SpringBootApplication
public class BpmApplication implements CommandLineRunner {

    @Autowired
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(BpmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoService.demo();
    }
}