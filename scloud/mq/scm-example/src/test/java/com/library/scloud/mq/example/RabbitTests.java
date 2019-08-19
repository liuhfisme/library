package com.library.scloud.mq.example;

import com.library.scloud.mq.example.day01.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * MQ测试类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-07-30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExampleApplication.class)
public class RabbitTests {
    @Autowired(required = false)
    private Sender sender;

    @Test
    public void sendTest() throws Exception {
//        while (true) {
//            String msg = new Date().toString();
//            sender.send(msg);
//            Thread.sleep(1000);
//        }
    }
}
