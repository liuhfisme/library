package com.mine.library.demo.core.thread;

public class ThreadTest {
	public static void main(String[] args) {
//		Thread thread1=new SimpleThread("First");
//		Thread thread2=new SimpleThread("Second");
//		thread1.start(); 
//		thread2.start();
		Thread thread1=new ThreadPriority("First");
		thread1.setPriority(Thread.MIN_PRIORITY);
		thread1.start();
		Thread thread2=new ThreadPriority("Second");
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread2.start();
		Thread thread3=new ThreadPriority("Three");
		thread3.setPriority(Thread.MAX_PRIORITY);
		thread3.start();
	}
}
class ThreadPriority extends Thread{

	public ThreadPriority(String name) {
		super(name);
	}

	@Override
	public void run() {
		for(int i=0;i<3;i++){
			System.out.println(getName()+" "+getPriority());
		}
	}
	
	
}
