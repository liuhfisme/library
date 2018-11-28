package com.library.utils.thread.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: InterruptThread
 * @Description: 示例- 四大线程池实现方式底层构造器
 * @author feifei.liu
 * @date 2018/11/18 13:39
 */
public class CoreThreadPoolExecutor {
    public static void main(String[] args) {
        int corePoolSize = 5;//核心池大小，意思是当超过这个范围的时候，就需要将新的线程放到等待队列中了即workQueue;
        int maximumPoolSize = 10;//线程池最大线程数量，表明线程池能创建的最大线程数
        int keepAlivertime = 3;//当活跃线程数大于核心线程数，空闲的多余线程最大存活时间。
        TimeUnit unit = TimeUnit.SECONDS;//存活时间单位
        LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();//存放任务的队列--阻塞队列
        //handler ：超出线程范围（maximumPoolSize）和队列容量的任务的处理程序
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
          corePoolSize, maximumPoolSize, keepAlivertime, unit, workQueue
        );
    }
}
