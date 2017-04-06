package com.mine.library.demo.core.jdbc;

import com.mine.library.demo.core.jdbc.data.DBDATA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionUtil {
	private final static String DRIVER_CLASS= DBDATA.DRIVER_CLASS;
	private final static String URL=DBDATA.URL;
	private final static String USER=DBDATA.USER;
	private final static String PASSWORD=DBDATA.PASSWORD;
	private static Connection conn=null;
	static{ 
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(conn==null){
			System.out.println("Connection is failed");
		}else{
			System.out.println("Connection is success");
		}
		return conn;
	}
	public static void closeAll(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		getConnection();
		System.out.println(DRIVER_CLASS+":::"+URL+":::"+USER+":::"+PASSWORD);
	}
}
