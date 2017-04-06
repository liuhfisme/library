package com.mine.library.demo.test;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


public class 类 {
	public int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * 内部类是从jdk1.1开始引入的，有如下优点：
	 * 1、内部类对象能访问所处类的私有属性和方法
	 * 2、内部类能够隐藏起来，不被包中其它类访问，如果一个类只对某个类提供使用，可以定义其为内部类
	 * 3、匿名内部类可以方便的用在回调方法中，典型应用是图形编辑中的事件处理
	 * 特点：
	 * 1、内部类可以声明为抽象类，所以可以被其它内部类继承，也可以声明为final的
	 * 2、和外部类不同的是内部类可以声明为private和protected，外部类只能声明为public和default
	 * 3、内部类可以声明为static的，但是同外部类方法一样不能使用外部类的非static属性
	 * 4、非static的内部类中的成员不能声明为static，只用在外部类和static内部类中才能声明static成员
	 * @author Administrator
	 */
	class 内部类{
		public void getAge(){
			System.out.println(age);
		}
	}
	/**
	 * 局部内部类作用域被限制在当前方法内不能被public或private等访问修饰符声明
	 */
	public void 局部内部类外的方法(){
		class 局部内部类{
			public void 局部内部类中的方法(){
				Date now=new Date();
				System.out.println(now.toGMTString());
			}
		}
		new 局部内部类().局部内部类中的方法();
	}
	Frame frame=new Frame();
	/**
	 * 匿名内部类是指在定义时没有名称的内部类，一般用于方法中
	 * 匿名内部类的声明是在编译时进行的
	 */
	public void 匿名内部类(){
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}

