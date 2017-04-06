package com.mine.library.demo.test;

public class Base {
	public static int count=0;
	public Base(){
		count++;
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		/**
		 * count变量被static修饰时下面base1和base2公用count
		 * 结果为1 2
		 * 去掉static修饰
		 * 结果为1 1
		 */
		Base  base1=new Base();
		System.out.println(base1.count);
		Base  base2=new Base();
		System.out.println(base2.count);
	}
}
