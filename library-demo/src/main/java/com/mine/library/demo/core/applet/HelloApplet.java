package com.mine.library.demo.core.applet;
import java.applet.Applet;
import java.awt.Graphics;

public class HelloApplet extends Applet{
	String msg;
	
	@Override
	public void init() {
		msg="Hello Applet";
	}

	public void paint(Graphics g){
		g.drawString(msg, 25, 25); 
	} 
}
