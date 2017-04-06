package com.mine.library.demo.core.pictureCheckCode.util;

import java.awt.Color;
import java.util.Random;

/**
 * 生成随机颜色
 * @author Administrator
 *
 */
public class RandColor {
	public static  Color getRandColor(int s,int e){
		Random ran=new Random();
		if(s>255)
			s=255;
		if(e>255)
			e=255;
		int r=s+ran.nextInt(e-s);//随机生成RGB颜色中的r值
		int g=s+ran.nextInt(e-s);//随机生成RGB颜色中的g值
		int b=s+ran.nextInt(e-s);//随机生成RGB颜色中的b值
		return new Color(r,g,b);
	}
}

