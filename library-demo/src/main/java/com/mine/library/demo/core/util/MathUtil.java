package com.mine.library.demo.core.util;

public class MathUtil {
	public static int staticVar=0;
	public int instanceVar=0;
	public MathUtil(){
		staticVar++;
		instanceVar++;
		System.out.println("staticVar:"+staticVar+"\ninstanceVar:"+instanceVar);
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		System.out.println(2<<3);
//		System.out.println(Byte.MIN_VALUE+"---"+Byte.MAX_VALUE);
//		System.out.println(Short.MIN_VALUE+"---"+Short.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE+"---"+Integer.MAX_VALUE);
//		System.out.println(Long.MIN_VALUE+"---"+Long.MAX_VALUE);
		MathUtil util=new MathUtil();
		MathUtil util2=new MathUtil();
		MathUtil util3=new MathUtil();
		MathUtil util4=new MathUtil();
		MathUtil util5=new MathUtil();
	}
}
