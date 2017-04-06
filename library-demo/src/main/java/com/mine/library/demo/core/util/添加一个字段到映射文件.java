package com.mine.library.demo.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;

public class 添加一个字段到映射文件 {
	public final static String[] paths={
			"E:\\Workspaces\\MyEclipse Professional 2014\\aycrmNew\\src\\com\\aycrm\\model",
			"E:\\Workspaces\\MyEclipse Professional 2014\\aycrmNew\\src\\com\\platform\\user\\domain",
			"E:\\Workspaces\\MyEclipse Professional 2014\\aycrmNew\\src\\com\\platform\\system\\domain",
			"E:\\Workspaces\\MyEclipse Professional 2014\\aycrmNew\\src\\com\\aycrminter\\model"};
	/**
	 * 添加一个字段到实体类和映射文件
	 */
	public void 添加一个字段到实体类和映射文件(){
		try {
			BufferedReader reader=null;
			BufferedWriter writer=null;
			String lineStr="";
			InputStream is=null;
			List<File> fileList=new ArrayList<File>();
			for(String path:paths){
				List<File> tempFileList=new ArrayList<File>();
				File tempFile=new File(path);
				String absolutePath=tempFile.getAbsolutePath().substring(51);
				tempFileList=listAllFile(tempFileList, tempFile.listFiles());
				//fileList.addAll(tempFileList);
				File directoryFile=new File("F:\\添加UUID字段\\"+absolutePath);
				directoryFile.mkdirs();
				for(File file:tempFileList){
					File newFile=new File("F:\\添加UUID字段\\"+absolutePath+"\\"+file.getName());
					is=new FileInputStream(file);
					reader=new BufferedReader(new InputStreamReader(is, "UTF-8"));
					writer=new BufferedWriter(new FileWriter(newFile));
//					<id name="id" type="java.lang.Integer" column="log_id">
//					<generator class="increment" />
//				</id>
					while((lineStr=reader.readLine())!=null){
						//<id name="id" type="java.lang.String" column="ID" />
						if(lineStr.indexOf("<id")!=-1){
							writer.write(lineStr.replace(" />", ">"));
							writer.newLine();
							writer.write("			<generator class=\"uuid\" />");
							writer.newLine();
							writer.write("		</id>");
							writer.newLine();
							writer.flush();
						}else{
							writer.write(lineStr);
							writer.newLine();
							writer.flush();
						}
					}
				}
				//System.out.println("--"+tempFileList.size());
			}
			//System.out.println(fileList.size());


			/*for(File file:fileList){
				File newFile=new File("F:\\添加UUID字段\\"+file.getName());
				is=new FileInputStream(file);
				reader=new BufferedReader(new InputStreamReader(is, "UTF-8"));
				writer=new BufferedWriter(new FileWriter(newFile));
				while((lineStr=reader.readLine())!=null){
					writer.write(lineStr);
					writer.newLine();
					if(lineStr.indexOf("</id>")!=-1){
						writer.write("        <property name=\"uuid\" type=\"java.lang.String\" column=\"UUID\"/>");
						writer.newLine();
					}
					writer.flush();
				}
			}*/


			/*File distFile=new File("F:\\useragentutils");
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
					writer.newLine();
					writer.flush();
				}

			}*/
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 遍历出所有文件
	 * @param list
	 * @param file
	 * @return
	 */
	public List<File> listAllFile(List<File> list,File[] files){
		for(File file:files){
			if(file.getName().indexOf(".hbm.xml")!=-1){
				if(file.isDirectory()){
					this.listAllFile(list, file.listFiles());
				}else{
					list.add(file);
				}
			}
		}
		return list;
	}
	public void test(){
		String str="E:\\Workspaces\\MyEclipse Professional 2014\\aycrmNew\\";
		System.out.println(str.length());
	}
}
