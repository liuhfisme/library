package com.mine.library.demo.test;

public class ThreadTestCase extends Thread{
	long minPrime;
	public ThreadTestCase(long minPrime) {
		super();
		this.minPrime = minPrime;
	}
	@Override
	public void run() {
		System.out.println("JVM 执行run()……");
	}
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		System.out.println("Thread start()……");
		super.start();
	}
	@SuppressWarnings("deprecation")
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Thread destroy()……");
		super.destroy();
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		ThreadTestCase testCase=new ThreadTestCase(43);
		testCase.start();
		try {
			System.out.println(System.currentTimeMillis());
			testCase.sleep(5000);
			System.out.println(System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
