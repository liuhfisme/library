package com.mine.library.demo.test;

import junit.framework.TestCase;

public class Test extends TestCase{
	public void test2(){
		for(int i=0;i<=10;i++){
			for(int j=0;j<=10;j++){
				if(j==5){
					System.out.print(" *");
				}else{
					System.out.print("  ");
				}
			}
			System.out.println(); 
		}
	}
	public String test(){
		return "Hello Assert";
	}
	public void test3(){
		assertEquals("Hello Assert", this.test());
	}
}
