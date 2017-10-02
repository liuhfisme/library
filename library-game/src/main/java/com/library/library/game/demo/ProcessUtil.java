package com.library.library.game.demo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * ClassName: ProcessUtil
 * @Description: 进程列表
 * @author feifei.liu
 * @date 2017年1月16日 下午3:32:13
 */
public class ProcessUtil {
	public static void main(String[] args) throws Exception {
        
        // 创建系统进程
        ProcessBuilder pb = new ProcessBuilder("tasklist");
        Process p = pb.start();
        BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
        BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));
        System.out.println("Window 系统进程列表");
        String ostr;
         
        while ((ostr = out.readLine()) != null)
        System.out.println(ostr);
        String estr = err.readLine();
        if (estr != null) {
        System.out.println("\nError Info");
        System.out.println(estr);
        }
    }
}
