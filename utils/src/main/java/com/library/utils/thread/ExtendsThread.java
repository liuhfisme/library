package com.library.utils.thread;

/**
 * @ClassName: InterruptThread
 * @Description: 示例- 线程的三种创建方式之一（extends Thread类）
 * @author feifei.liu
 * @date 2018/11/18 13:39
 */
public class ExtendsThread extends Thread {
    private int i =0;

    public ExtendsThread(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        synchronized (this) {
            while (i<1000) {
                i++;
                System.out.println(Thread.currentThread().getName()+ "i="+i);
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new ExtendsThread("A线程");
        Thread thread1 = new ExtendsThread("B线程");
        thread.start();
        thread1.start();
    }
}
