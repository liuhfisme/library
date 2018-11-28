package com.library.utils.thread.pool.fac;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Main
 * @Description: 测试主函数- 线程池
 * @author feifei.liu
 * @date 2018/11/28 10:42
 */
public class Main {
    public static void main(String[] args) {
        //设置配置
        ThreadPoolConfig config = ThreadPoolConfig.getInstance();
        config.setCorePoolSize(2);
        config.setMaximumPoolSize(3);
        config.setKeepAliveTime(5);
        config.setUnit(TimeUnit.SECONDS);
        //将队列设小，会抛异常
        config.setWorkQueue(new ArrayBlockingQueue<>(10));
        config.setHandler(new MyRejectedExecutionHandler());

        //线程池工厂
        ThreadPoolFactory factory = ThreadPoolFactory.getInstance(config);
        for (int i = 0; i < 100; i++) {
            factory.addTask(new ThreadTask(i+"-i"));
        }
        System.out.println("i add is over!…………");
    }
}
