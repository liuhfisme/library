package com.mine.library.demo.core.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
/**
 * 根据国标GB2312-80规定：
 * 	1、所有国标汉字及字符分配在一个94行、94列的方阵中
 * 	2、方阵每一行称作一个区，每一列称作一个位，编号都为01到94
 * 	3、方阵中每一个汉字和符号所在的区号和位号组合在一起的4个阿拉伯数字就是它们的“区位码”
 * 	4、任何一个汉字或符号都对应着唯一的一个区位码
 * 01区到15区（图形符号区）：
 * 	1、01区到09区为标准符号区
 * 	2、10区到15区为自定义符号区
 * 16区到55区（一级常用汉字区）：
 * 	1、包含3755个一级汉字
 *  2、这40个区的汉字是按汉语拼音排序的，同音字按笔画顺序排序
 *  3、其中55区的90~94位未定义汉字
 * 56区到87区（二级汉字区）：
 * 	1、包含3008个汉字
 *  2、按部首排序
 * 88区到94区（自定义汉字区）：
 * 	1、第10~15区的自定义符号和第88~94区的自定义汉字可以是用户自行定义国标码中未定义的符号和汉字
 *
 * @author Administrator
 *
 */
public class CharUtil {
	private static Random random=new Random();
	public static String getChar(){
		String cTemp="";
		String[] rTemp={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
		//生成第1位的区码
		int r1=random.nextInt(3)+11;//生成11~14之间的随机数
		String str_r1=rTemp[r1];
		//生成第2位的区码
		int r2;
		if(r1==11){
			r2=random.nextInt(7);//生成0~7之间的随机数
		}else{
			r2=random.nextInt(16);//生成0~16之间的随机数
		}
		String str_r2=rTemp[r2];
		//生成第1位的位码
		int r3=random.nextInt(6)+10;//生成10~16之间的随机数
		String str_r3=rTemp[r3];
		//生成第2位的位码
		int r4;
		if(r3==10){
			r4=random.nextInt(15)+1;
		}else if(r3==15){
			r4=random.nextInt(15);
		}else{
			r4=random.nextInt(16);
		}
		String str_r4=rTemp[r4];
		//根据随机生成的区码和位码生成汉字
		byte[] bytes=new byte[2];
		//将生成的区码保存在字节数组的第1个元素中
		String str_12=str_r1+str_r2;
		int tempLow=Integer.parseInt(str_12, 16);
		bytes[0]=(byte) tempLow;
		//将生成的位码保存在字节数组的第2个元素中
		String str_34=str_r3+str_r4;
		int tempHigh=Integer.parseInt(str_34, 16);
		bytes[1]=(byte)tempHigh;
		//根据字节数组生成汉子
		try {
			cTemp=new String(bytes,"GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(tempLow+"---"+tempHigh);
		System.out.println(bytes[0]+"---"+bytes[1]);
//		System.out.println(r1+"-"+r2+"-"+r3+"-"+r4);
//		System.out.println(str_r1+"-"+str_r2+"-"+str_r3+"-"+str_r4);
		System.out.println(cTemp);
		return cTemp;
	}
	public static void main(String[] args) {
//		int i=0;
//		String str="";
//		while(true){
////			System.out.println(getChar());
//			System.out.println("---------- "+i);
//			str+=getChar();
//			i++;
//			if(i==100){
//				break;
//			}
//		}
//
//		System.out.println(str);
		int i, j;
		System.out.println("start……");
		for (i = 1; i <= 94; i++) {
			for (j = 1; j <= 94; j++) {
				try {
					System.out.print(new String(new byte[] { (byte) -(byte)i, (byte) -(byte)j },
							"GB2312"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------------------------------");
		}
		System.out.println("end……");
	}

}
