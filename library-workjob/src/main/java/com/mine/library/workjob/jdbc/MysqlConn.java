package com.mine.library.workjob.jdbc;

import java.sql.*;

/**
 * ClassName: MysqlConn
 * @Description: 康美产品推荐测试
 * @author feifei.liu
 * @date 2017年2月10日 上午10:37:10
 */
public class MysqlConn {
	/*private static String url = "jdbc:mysql://192.168.1.177:3306/baihui?useUnicode=true&characterEncoding=utf-8" ;
	private static String username = "root" ;
	private static String password = "PGix8VM7" ;*/
	private static String url = "jdbc:mysql://192.168.1.210:3307/baihui?useUnicode=true&characterEncoding=utf-8" ;
	private static String username = "root" ;
	private static String password = "V23jdlfafxc_write" ;
	private static String url2 = "jdbc:mysql://192.168.19.246:3306/baihui_lowercase?useUnicode=true&characterEncoding=utf-8" ;
	private static String username2 = "root" ;
	private static String password2 = "baihuiroot" ;
	
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
	

	
	public static void main(String[] args) throws SQLException {
	}
}
