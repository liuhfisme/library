package com.library.utils.design;

/**
 * @ClassName: SingletonModeHunger
 * @Description: 单例模式- 饿汉式
 * @author feifei.liu
 * @date 2018/11/27 10:50
 */
public class SingletonModeHunger {
    //在静态初始化时创建实例，这段代码保证了线程安全
    private static SingletonModeHunger singletonModeHunger = new SingletonModeHunger();
    //只定义一个私有构造方法，所有用户无法通过new方法创建对象实例
    private SingletonModeHunger(){}

    public static SingletonModeHunger getInstance() {
        return singletonModeHunger;
    }
}
