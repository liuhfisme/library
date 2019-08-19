package com.library.scloud.mq.example.day01;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-07-30
 */
@Component
public class Receiver {
    @RabbitListener(queues = "hello.queue1")
    public String processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
        return msg.toUpperCase();
    }

    @RabbitListener(queues = "hello.queue2")
    public void processMessage2(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + msg);
    }
}
