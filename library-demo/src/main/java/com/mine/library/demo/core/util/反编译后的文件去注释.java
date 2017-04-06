package com.mine.library.demo.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class 反编译后的文件去注释 {
	public static void main(String[] args) {
		try {
			File distFile=new File("F:\\useragentutils");
			File[] files=distFile.listFiles();
			String lineStr="";
			BufferedReader reader=null;
			BufferedWriter writer=null;
			for(File file:files){
				File newFile=new File("F:\\useragentutils-deal\\"+file.getName());
				reader=new BufferedReader(new FileReader(file));
				writer=new BufferedWriter(new FileWriter(newFile));
				while((lineStr=reader.readLine())!=null){
					//System.out.println(lineStr);
					writer.write(lineStr.replaceFirst(".*/\\*(.*)\\*/", ""));
					writer.newLine();
					writer.flush();
				}

			}
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
		}
	}
}
