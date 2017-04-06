package com.mine.library.demo.test;

import junit.framework.TestCase;

public class UUID extends TestCase{
	public void t1(){
		java.util.UUID uuid=java.util.UUID.randomUUID();
		System.out.println(uuid);
	}
}
