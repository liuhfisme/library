package com.mine.library.demo.core.util.MailUtils;

import net.sf.json.JSONObject;

import java.util.List;

public class Test2{
	public void t1(){
		String[] strs=new String[]{"1","2","3"};
		System.out.println(strs);
	}
	/**
	 * JSON字符串转javaBean对象
	 */
	public void t2(){
		JSONObject jsonObject=JSONObject.fromObject("{\"address\":\"liuff2513@163.com\",\"personal\":\"\"},{\"address\":\"liuff2513@163.com\",\"personal\":\"\"}");
		List<AddressJSON> addressJSONList=(List<AddressJSON>) JSONObject.toBean(jsonObject, AddressJSON.class);
		for(AddressJSON json:addressJSONList){
			System.out.println(json.getAddress()+"--");
			System.out.println(json.getPersonal()+"--");
		}
	}
	public void t3(){
		String str="<BODY><HEAD></HEAD></BODY>";
		System.out.println(str.indexOf("</HEAD>"));
		System.out.println(new String("</HEAD>").length());
	}
	public void t4(){
		String i=null;
		String j=i;
		if(j!=null){
			System.out.println("1");
		}else{
			System.out.println("2");
		}
	}
}
