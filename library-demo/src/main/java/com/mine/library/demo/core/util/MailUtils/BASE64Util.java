package com.mine.library.demo.core.util.MailUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * BASE64 编码和解码工具
 * @author Administrator
 *
 */
public class BASE64Util {
	private final static BASE64Encoder ENCODER=new BASE64Encoder();
	private final static BASE64Decoder DECODER=new BASE64Decoder();
	/**
	 * BASE64 编码
	 * @param str 编码前的字符串
	 * @return BASE64 编码格式的字符串
	 */
	public static String encode(String str){
		if(str==null)
			throw new IllegalArgumentException("Null str!");
		String encodedStr=ENCODER.encode(str.getBytes());
		return encodedStr;
	}
	/**
	 * BASE64 解码
	 * @param str BASE64 编码格式的字符串
	 * @return 解码后的字符串
	 */
	public static String decode(String str){
		if(str==null)
			throw new IllegalArgumentException("Null str!");
		String decodedStr="";
		try {
			byte[] decodedByte=DECODER.decodeBuffer(str);
			decodedStr=new String(decodedByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decodedStr;
	}
	public static void main(String[] args) {
		System.out.println(encode("liuff2513@163.com"));
		System.out.println(decode(""));
	}
}
