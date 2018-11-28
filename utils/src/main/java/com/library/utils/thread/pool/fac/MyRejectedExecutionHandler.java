package com.library.utils.thread.pool.fac;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: MyRejectedExecutionHandler
 * @Description: 线程池异常处理类
 * @author feifei.liu
 * @date 2018/11/28 10:36
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        System.out.println("Begin exception handler…………");
        //执行失败任务
        new Thread(task, "exception by pool").start();
        //打印线程池的对象
        System.out.println(String.format("The pool RejectedExecutionHandler = ", executor.toString()));
    }
}
