package com.library.utils.thread;

/**
 * @ClassName: InterruptThread
 * @Description: 示例- 停止一个正在运行的线程(使用interrupt函数)
 * @author feifei.liu
 * @date 2018/11/18 13:39
 */
public class InterruptThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5000000; i++) {
            if (this.interrupted()) {
                System.out.println("已经是停止状态了!我要退出了!");
                break;
            }
            System.out.println("i="+(i+1));
        }
        System.out.println("看到这句话说明线程并未终止------");
    }

    public static void main(String[] args) {
        try {
            InterruptThread thread = new InterruptThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        }catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}
