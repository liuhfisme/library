package com.library.utils.design;

/**
 * @ClassName: SingletonModeLazier
 * @Description: 单例模式- 懒汉式
 * @author feifei.liu
 * @date 2018/11/27 11:01
 */
public class SingletonModeLazier {
    private static SingletonModeLazier singletonModeLazier1;
    private static SingletonModeLazier singletonModeLazier2;
    /**
     * volatile 保证，当singletonModeLazier变量被初始化成实例时，多个线程可以正确处理singletonModeLazier变量
     */
    private volatile static SingletonModeLazier singletonModeLazier3;
    private SingletonModeLazier() {}

    /**
     * 没有加入synchronized 关键字的版本是线程不安全的
     *
     * @return SingletonModeLazier
     */
    public static SingletonModeLazier getInstance() {
        if (singletonModeLazier1 == null) {
            singletonModeLazier1 = new SingletonModeLazier();
        }
        return singletonModeLazier1;
    }

    /**
     * 加入synchronized 关键字保证线程安全
     *
     * @return SingletonModeLazier
     */
    public static synchronized SingletonModeLazier getInstance2() {
        if (singletonModeLazier2 == null) {
            singletonModeLazier2 = new SingletonModeLazier();
        }
        return singletonModeLazier2;
    }

    /**
     * 双重检查加锁
     *
     * @return SingletonModeLazier
     */
    public static SingletonModeLazier getInstance3() {
        if (singletonModeLazier3 == null) {
            synchronized (SingletonModeLazier.class) {
                if (singletonModeLazier3 == null) {
                    singletonModeLazier3 = new SingletonModeLazier();
                }
            }
        }
        return singletonModeLazier3;
    }

}
