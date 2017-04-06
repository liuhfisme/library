package com.mine.library.demo.core.thread;

public class SimpleThread extends Thread{

	public SimpleThread(String name) {
		super(name);
	}
	/**
	 * 重写Thread的run方法
	 */
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(i+" "+getName());
			try {
				sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("done "+getName());
	}
}
