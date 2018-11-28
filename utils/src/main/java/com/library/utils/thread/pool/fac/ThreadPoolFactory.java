package com.library.utils.thread.pool.fac;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadPoolFactory
 * @Description: 线程池工厂方法
 * @author feifei.liu
 * @date 2018/11/28 10:13
 */
public class ThreadPoolFactory {
    //线程池
    private static ThreadPoolExecutor pool;
    //自身对象
    private static ThreadPoolFactory factory;

    /**
     * 私有构造器
     */
    private ThreadPoolFactory(){}

    public static ThreadPoolFactory getInstance(ThreadPoolConfig config) {
        if (factory == null) {
            factory = new ThreadPoolFactory();
        }
        if (pool == null) {
            if (config.getHandler() == null) {
                pool = new ThreadPoolExecutor(config.getCorePoolSize(),config.getMaximumPoolSize(),config.getKeepAliveTime(),
                        config.getUnit(),config.getWorkQueue());
            } else {
                pool = new ThreadPoolExecutor(config.getCorePoolSize(),config.getMaximumPoolSize(),config.getKeepAliveTime(),
                        config.getUnit(),config.getWorkQueue(),config.getHandler());
            }
        }
        System.out.println(String.format("pool create= %s", pool.toString()));
        return factory;
    }

    /**
     * @Title: addTask
     * @Description: 添加线程池任务
     * @Param [run]
     * @return void
     * @author feifei.liu
     * @date 2018/11/28 10:33
     */
    public synchronized void addTask(Runnable run) {
        pool.execute(run);
    }

    /**
     * @Title: addTask
     * @Description: 添加线程池任务- 批量
     * @Param [runs]
     * @return void
     * @author feifei.liu
     * @date 2018/11/28 10:34
     */
    public synchronized void addTask(List<Runnable> runs) {
        if (runs != null) {
            for (Runnable run:runs) {
                this.addTask(run);
            }
        }
    }

    /**
     * @Title: closePool
     * @Description: 关闭线程池
     * @Param []
     * @return void
     * @author feifei.liu
     * @date 2018/11/28 10:35
     */
    public void closePool() {
        pool.shutdown();
    }
}
