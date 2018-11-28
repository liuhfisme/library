package com.library.utils.thread;

/**
 * @ClassName: InterruptThread
 * @Description: 示例- 线程的三种创建方式之一（implements Runnable接口）
 * @author feifei.liu
 * @date 2018/11/18 13:39
 */
public class ImplementsThread implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        synchronized (this) {
            synchronized (this) {
                while (i<1000) {
                    i++;
                    System.out.println(Thread.currentThread().getName()+ "i="+i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ImplementsThread(),"A线程");
        Thread thread1 = new Thread(new ImplementsThread(),"B线程");
        thread.start();
        thread1.start();
    }
}
