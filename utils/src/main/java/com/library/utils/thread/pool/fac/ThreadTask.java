package com.library.utils.thread.pool.fac;

/**
 * @ClassName: ThreadTask
 * @Description: 任务线程
 * @author feifei.liu
 * @date 2018/11/28 10:46
 */
public class ThreadTask extends Thread {
    public ThreadTask(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(String.format("%s, will sleep 0 s", this.getName().toString()));
        try {
            this.sleep(1*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s, I am wakeup now ", this.getName().toString()));
    }
}
