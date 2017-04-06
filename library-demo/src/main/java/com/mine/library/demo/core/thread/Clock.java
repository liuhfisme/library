package com.mine.library.demo.core.thread;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Date;

public class Clock extends Applet implements Runnable{
	Thread clockThread;
	/**
	 * 该方法是Applet的方法，不是线程的方法
	 */
	@Override
	public void start() {
		if(clockThread==null){
			/*线程体是Clock对象本身，线程名字为"Clock"*/
			clockThread=new Thread(this, "Clock");
			/*启动线程*/
			clockThread.start();
		}
	}
	public void paint(Graphics g){
		Date now=new Date();
		g.drawString(now.getHours() + ":" + now.getMinutes()+ ":" +now.getSeconds(), 5, 10);
	}
	@Override
	public void run() {
		while(clockThread!=null){
			repaint();//刷新显示画面
		}
		try {
			/*睡眠1秒，即每隔1秒执行一次*/
			clockThread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 该方法是Applet的方法，不是线程的方法
	 */
	@Override
	public void stop() {
		clockThread.stop();
		clockThread=null;
	}
}
