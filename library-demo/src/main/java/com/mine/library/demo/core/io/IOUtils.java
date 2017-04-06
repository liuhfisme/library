package com.mine.library.demo.core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class IOUtils {
	public static void main(String[] args) {
//		String files = "F://util";
//		File file = new File(files);
//		delNote(file);
		writeNode("F://test/note3.txt", readNode(new File("F://test/note2.txt"), "txt"));
	}
	/**
	 *  读取文件
	 */
	public static String readNode(File file,String fileType){
		String str="";
		String resultStr="";
		/*如果f为文件并且文件后缀为fileType*/
		if(file.isFile()&&file.getName().endsWith("."+fileType)){
			try {
				InputStream is=new FileInputStream(file);
				Reader in=new InputStreamReader(is);
				BufferedReader reader=new BufferedReader(in);

//				BufferedReader reader2=new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
				while((str=reader.readLine())!=null){
//					System.out.println(str);
					resultStr+=str;
				}
				reader.close();
				in.close();
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("resultStr:::\n"+resultStr);
		return resultStr;
	}
	/**
	 * 写文件
	 */
	public static void writeNode(String dosPath,String data){
		try {
			OutputStream os=new FileOutputStream(dosPath);
			Writer osw=new OutputStreamWriter(os, "gbk");
			PrintWriter out=new PrintWriter(osw);
			out.print(data);
			out.close();
			osw.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void delNote(File f) {
		String str = "";
		String rootDirectory = "d";
		if ((f.isFile()) && (f.getName().endsWith(".java"))) {
			System.out.println(f.getName());
			try {
				InputStream is = new FileInputStream(f);
				Reader in = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(in);

				String file = rootDirectory
						+ f.getPath().substring(1, f.getPath().length());
				System.out.println("file:::" + file);
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");
				PrintWriter out = new PrintWriter(osw);

				while ((str = reader.readLine()) != null) {
					Pattern pattern2 = Pattern.compile("/\\*(.*?)\\*/", 32);
					Matcher matcher2 = pattern2.matcher(str);
					str = matcher2.replaceAll("");
					out.println(str);
				}
				is.close();
				in.close();
				reader.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (f.isDirectory()) {
			String resultPath = rootDirectory
					+ f.getPath().substring(1, f.getPath().length());
			File fileDemo = new File(resultPath);
			fileDemo.mkdir();
			File[] fs = f.listFiles();
			for (int i = 0; i < fs.length; i++) {
				File file = fs[i];
				delNote(file);
			}
		}
		System.out.println("修改完毕\n存放目录在 " + rootDirectory.toUpperCase() + "盘！");
	}
}