package com.mine.library.demo.test;

public class ThreadTestCase2 implements Runnable{
	long minPrime;

	public ThreadTestCase2(long minPrime) {
		super();
		this.minPrime = minPrime;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("JVM run……");
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		ThreadTestCase2 case2=new ThreadTestCase2(5000);
		System.out.println(System.currentTimeMillis());
		new Thread(case2).start();
	}
}
