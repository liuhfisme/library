package com.library.game.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TemporaryTableTest {
	private static String url = "jdbc:mysql://192.168.1.177:3306/scrm?useUnicode=true&characterEncoding=utf-8" ;    
	private static String username = "root" ;   
	private static String password = "PGix8VM7" ;   
	
	/**
	 * @Description: 获取数据库连接
	 * @return   
	 * @return Connection  
	 * @throws
	 * @author feifei.liu
	 * @date 2017年2月10日 上午10:37:39
	 */
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败！");   
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description: 执行查询SQL返回ResultSet对象
	 * @param sql
	 * @return   
	 * @return ResultSet  
	 * @throws
	 * @author feifei.liu
	 * @date 2017年2月10日 上午10:37:49
	 */
	public static ResultSet executeSql(String sql) {
		Connection conn = null;
		Statement smt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			smt = conn.createStatement();
			rs =  smt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/* finally{
			try {
				if(rs!=null) rs.close();
				if(smt!=null) smt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return null;
	}
	public static void main(String[] args) {
		Connection conn = null;
		Statement smt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			smt = conn.createStatement();
			smt.execute("create temporary table tmp_table(ID varchar(32) not null)");
			smt.execute("insert into tmp_table (ID) values(select ID from bh_customer limit 0,1000)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(smt!=null) smt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
