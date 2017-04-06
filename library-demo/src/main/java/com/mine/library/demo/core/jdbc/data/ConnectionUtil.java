package com.mine.library.demo.core.jdbc.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private Properties p;
	static String driver_class;
	static String url;
	static String username;
	static String password;
	
	private ConnectionUtil() {
		p=new Properties();
		InputStream is=this.getClass().getResourceAsStream("dataSource.properties");
		try {
			p.load(is);
			driver_class=p.getProperty("driver_class");
			url=p.getProperty("url");
			username=p.getProperty("username");
			password=p.getProperty("password");
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(driver_class);
			conn=DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Connection conn=new ConnectionUtil().getConnection();
		try {
			System.out.println(conn==null);
			System.out.println(conn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
