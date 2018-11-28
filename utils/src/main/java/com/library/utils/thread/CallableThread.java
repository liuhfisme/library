package com.library.utils.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: InterruptThread
 * @Description: 示例- 线程的三种创建方式之一（Callable FutureTask实现）
 * @author feifei.liu
 * @date 2018/11/18 13:39
 */
public class CallableThread {
    public static void main(String[] args) {
        //1.创建Callable的对象返回值
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return  new Random().nextInt(1000);
            }
        };

        //2.FutureTask实现了两个接口，Runnable和Future，所以它既可以作为Runnable被线程执行，也可以作为Future得到Callable的返回值。
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        //3.作为Runnable被线程执行
        new Thread(future, "A线程").start();
        try {
            Thread.sleep(5000);//可能做一些事情
            //4.作为Future得到Callable的返回值
            System.out.println(future.get());//拿到结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
