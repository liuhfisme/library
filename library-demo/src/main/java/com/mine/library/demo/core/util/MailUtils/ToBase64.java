package com.mine.library.demo.core.util.MailUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Base64编码形式转换
 */
public class ToBase64 {
	public static void main(String[] args) throws IOException {
		BASE64Encoder encoder=new BASE64Encoder();
		BASE64Decoder decoder=new BASE64Decoder();
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String txt=reader.readLine();
			txt=encoder.encode(txt.getBytes());
			System.out.println(txt);
			byte[] txtDec=decoder.decodeBuffer(txt);
			System.out.println(new String(txtDec));
		}
	}
}
