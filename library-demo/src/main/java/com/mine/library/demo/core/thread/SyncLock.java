package com.mine.library.demo.core.thread;

public class SyncLock {
	int idx=0; //堆栈指针的初始值为0
	char[ ] data = new char[6]; //堆栈有6个字符的空间

	public void push(char c){ //压栈操作
		synchronized (this) {//this表示Stack的当前对象
			data[idx] = c; //数据入栈
			idx++; //指针向上移动一位
		}
	}
	public char pop(){ //出栈操作
		synchronized (this) {//this表示Stack的当前对象
			idx--; //指针向下移动一位
			return data[idx]; //数据出栈
		}
	}
}

