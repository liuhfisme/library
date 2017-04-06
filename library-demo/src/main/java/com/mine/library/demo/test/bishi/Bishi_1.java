package com.mine.library.demo.test.bishi;

/**
 * 题目：
 * 	有一对兔子，从出生后第3个月起每个月都生一对兔子，
 * 	小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，
 * 	问一年内每个月的兔子总数为多少？
 * @author Administrator
 *
 */
public class Bishi_1 {
	public void method1() {
		System.out.println("第1个月的兔子对数：1");
		System.out.println("第2个月的兔子对数：1");
		int c1=1,c2=1,c,m=12;
		for(int i=3;i<=m;i++){
			c = c2;
			/*上个月的兔子对数*/
			/*每月增加后的兔子总对数*/
			c2 = c1 + c2;
			/*每月要增加的兔子对数*/
			c1 = c;
			System.out.println(c+"--"+c1+"--"+c2);
//			System.out.println("第" + i + "个月的兔子对数: " + c2);
		}
	}
	public void method2(){
		/**
		 * 从第3个月后推算结果得出如下规律：
		 * 	    成熟的兔子对数     半成熟的兔子对数    新生兔子对数
		 * 3-      1			    0			  1
		 * 4-	   1				1			  1
		 * 5-	   2				1			  2
		 * 6-	   3				2			  3
		 * .........
		 * 如上可得知,每个月的成熟的兔子和新生的兔子对数是一样的，规律为上两个月的总和
		 * 而半成熟的兔子对数规律和上面的一样，但是要比成熟的晚一个月开始算
		 */
		int u1=0,u2=1,u=1,count;
		for(int i=3;i<=12;i++){
			count=2*u+u1;
			u=u1+u2;
			u1=u2;
			u2=u;
			System.out.println("第"+i+"个月的兔子对数："+count);
		}
	}
}
