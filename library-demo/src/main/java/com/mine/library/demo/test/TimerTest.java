package com.mine.library.demo.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimerTest {
	public static void main(String[] args) {
		ActionListener listener=new TimePrinter();
		Timer timer=new Timer(1000, listener);
		timer.start();
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}
class TimePrinter implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Date now=new Date();
		System.out.println("At the tone,the time is "+now);
		Toolkit.getDefaultToolkit().beep();//使机子发出哔哔的提示音
	}

}