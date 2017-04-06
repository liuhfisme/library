package com.mine.library.demo.core.pictureCheckCode.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CreatePictureCheckCode {
	public static void getPictureCheckCode(HttpServletRequest request,
										   HttpServletResponse response)throws ServletException,IOException{
		int width=86;//指定验证码的宽度
		int height=22;//指定验证码的高度
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

		Graphics g=image.getGraphics();
		Graphics2D g2d=(Graphics2D)g;//创建Graphics2D对象

		Random ran=new Random();
		Font mFont=new Font("黑体",Font.BOLD,16);//定义一字体样式

		g.setColor(RandColor.getRandColor(200, 255));
		g.fillRect(0, 0, width, height);//绘制背景
		g.setFont(mFont);
		g.setColor(RandColor.getRandColor(180, 200));//设置字体

		//绘制100根颜色和位置全部为随机产生的线条，该线条为2f
		for(int i=0;i<100;i++){
			int x=ran.nextInt(width-1);
			int y=ran.nextInt(height-1);
			int x1=ran.nextInt(6)+1;
			int y1=ran.nextInt(12)+1;
			BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
			Line2D line=new Line2D.Double(x,y,x+x1,y+y1);
			g2d.setStroke(bs);
			g2d.draw(line);//绘制直线
		}
		String sRand="";
		//输出随机的验证文字
		String ctmp="";
		int itmp=0;
		for(int i=0;i<4;i++){
//			ran=new Random(new Date().getTime()+i);
			switch(ran.nextInt(4)){
				case 1://生成A~Z的字母
					itmp=ran.nextInt(26)+65;
					ctmp=String.valueOf((char)itmp);
					break;
				case 2://生成汉字
					String[] rBase={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
					//生成第一位的区码
					int r1=ran.nextInt(3)+11;//生成11~14之间的随机数
					String str_r1=rBase[r1];
					//生成第2位的区码
					int r2;
					if(r1==13){
						r2=ran.nextInt(7);//生成0~7之间的随机数
					}else{
						r2=ran.nextInt(16);//生成0~16之间的随机数
					}
					String str_r2=rBase[r2];
					//生成第1位的位码
					int r3=ran.nextInt(6)+10;//生成10~16之间的随机数
					String str_r3=rBase[r3];
					//生成第2位的位码
					int r4;
					if(r3==10){
						r4=ran.nextInt(15)+1;//生成1~16之间的随机数
					}else if(r3==15){
						r4=ran.nextInt(15);//生成1~15之间的随机数
					}else{
						r4=ran.nextInt(16);//生成0~16之间的随机数
					}
					String str_r4=rBase[r4];
					//将生成的机内码转换为汉字
					byte[] bytes=new byte[2];
					//将生成的区码保存到字节数组的第1个元素中
					String str_r12=str_r1+str_r2;
					int tempLow=Integer.parseInt(str_r12,16);
					bytes[0]=(byte)tempLow;
					//将生成的位码保存到字节数组的第2个元素中
					String str_r34=str_r3+str_r4;
					int tempHigh=Integer.parseInt(str_r34, 16);
					bytes[1]=(byte)tempHigh;
					ctmp=new String(bytes);//根据字节数组生成汉字
					break;
				default:
					itmp=ran.nextInt(10)+48;
					ctmp=String.valueOf((char)itmp);
					break;
			}
			sRand+=ctmp;
			Color color=new Color(20+ran.nextInt(110),20+ran.nextInt(110),20+ran.nextInt(110));
			g.setColor(color);
			//将文字旋转指定角度
			Graphics2D g2d_word=(Graphics2D) g;
			AffineTransform trans=new AffineTransform();
			trans.rotate(ran.nextInt(45)*3.14/180,15*i+8,7);
			//缩放字体
			float scaleSize=ran.nextFloat()+0.8f;
			if(scaleSize>1f)
				scaleSize=1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(ctmp, 15*i+18, 14);
		}
		//将生成的验证码保存到Session中。
		HttpSession session=request.getSession(true);
		session.setAttribute("randCheckCode", sRand);
		//输出生成的验证码图片
		g.dispose();//disponse方法： 释放此图形的上下文以及它使用的所有系统资源
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}
