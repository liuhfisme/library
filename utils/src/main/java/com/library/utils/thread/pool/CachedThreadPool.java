package com.library.utils.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName: InterruptThread
 * @Description: 示例- 线程池的四种实现方式之一（缓存线程池）
 * 若果线程池长度超过处理需求，可灵活回收空闲线程，若无可回收，则新建线程
 * @author feifei.liu
 * @date 2018/11/18 13:39
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for(int i=0; i< 10;i++) {
            final int index = i;
            try {
                Thread.sleep(index*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

}
