package com.library.utils.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: InterruptThread
 * @Description: 示例- 线程池的四种实现方式之一（定长线程池支持定时和周期性任务）
 * @author feifei.liu
 * @date 2018/11/18 13:39
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds!");
            }
        }, 3, TimeUnit.SECONDS);
    }
}
