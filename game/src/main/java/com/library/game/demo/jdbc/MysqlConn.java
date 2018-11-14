package com.library.game.demo.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ClassName: MysqlConn
 * @Description: 康美产品推荐测试
 * @author feifei.liu
 * @date 2017年2月10日 上午10:37:10
 */
public class MysqlConn {
	private static String url = "jdbc:mysql://192.168.1.177:3306/baihui?useUnicode=true&characterEncoding=utf-8" ;    
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
	
	/**
	 * @Description: 获取AB产品同时出现的次数
	 * @return
	 * @throws SQLException   
	 * @return Map<String,Integer>  
	 * @throws
	 * @author feifei.liu
	 * @date 2017年2月10日 上午10:40:35
	 */
	public static Map<String, Integer> getABNumberMap() throws SQLException{
		long s = System.currentTimeMillis();
		ResultSet rs = executeSql("SELECT ARG_F1001,ARG_A2001 FROM bh_custom_module_2 WHERE ARG_F1001<>'' AND ARG_A2001<>'' ORDER BY ARG_F1001;");
		List<Object[]> arrs = new ArrayList<>();
		List<Object> arr = new ArrayList<>();
		String argF1001End="";
		while(rs.next()) {
			String argF1001 = rs.getString("ARG_F1001");
			String argA2001 = rs.getString("ARG_A2001");
			if(!argF1001.equals(argF1001End)) {
				if(arr.size()>1) arrs.add(arr.toArray());
				arr = new ArrayList<>();
			}
			if(!arr.contains(argA2001)) arr.add(argA2001);
			argF1001End = argF1001;
			if(rs.isLast()&&arr.size()>1) arrs.add(arr.toArray());
		}
		System.out.println(arrs.size()+":::"+Arrays.toString(arrs.get(0)));
		long t1 = System.currentTimeMillis();
		System.out.println("数据查询耗时："+(t1-s));
		Map<String, Integer> map = new LinkedHashMap<>();
		int k = 0;
		for(Object[] arr2:arrs) {
//			System.out.println(arrs.indexOf(arr2)+":"+arr2.length);
//			if(arrs.indexOf(arr2)==1) {
//				System.out.println(arrs.indexOf(arr2)+":"+Arrays.toString(arr2));
				for(int i=0;i<arr2.length;i++) {
					for(int j=i+1;j<arr2.length;j++) {
						if(!arr2[i].equals(arr2[j])){
							String tempKey = arr2[i]+"_"+arr2[j];
							String tempKeyAdverse = arr2[j]+"_"+arr2[i];
							if("龙眼肉_医用电子体温计奶嘴型".equals(tempKey)||"龙眼肉_医用电子体温计奶嘴型".equals(tempKeyAdverse)) {
								k++;
							}
							if(map.keySet().contains(tempKeyAdverse)){
								tempKey = tempKeyAdverse;
							}
							if(map.get(tempKey)==null){
								map.put(tempKey, 1);
							} else{
								map.put(tempKey, map.get(tempKey)+1);
							}
						}
					}
				}
//			}
		}
		System.out.println("K:::"+k);
		System.out.println(map.keySet().size());
//		for(Entry<String, Integer> entry:map.entrySet()) {
//			System.out.println(entry.getKey()+" :: "+entry.getValue());
//		}
		long e = System.currentTimeMillis();
		System.out.println("匹配耗时："+(e-t1));
		rs.close();
		return map;
	}
	
	/**
	 * @Description: 总消费次数
	 * @return
	 * @throws SQLException   
	 * @return long  
	 * @throws
	 * @author feifei.liu
	 * @date 2017年2月10日 上午10:45:08
	 */
	public static Integer getOrderNumber() throws SQLException {
		ResultSet rs = executeSql("SELECT count(1) as cnt FROM bh_custom_module_1 WHERE NAME<>'';");
		Integer orderNumber = 0;
		while(rs.next()) {
			orderNumber = rs.getInt("cnt");
		}
		rs.close();
		System.out.println("总消费单次数："+orderNumber); 
		return orderNumber;
	}
	
	/**
	 * @Description: 获取产品出现的次数
	 * @return   
	 * @return Map<String,Integer>  
	 * @throws SQLException 
	 * @throws
	 * @author feifei.liu
	 * @date 2017年2月10日 上午10:53:16
	 */
	public static Map<String, Integer> getProductNumberMap() throws SQLException {
		ResultSet rs = executeSql("SELECT ARG_A2001, count(1) as cnt FROM bh_custom_module_2 WHERE ARG_A2001<>'' GROUP BY ARG_A2001");
		Map<String, Integer> productNumberMap = new LinkedHashMap<>();
		while(rs.next()) {
			productNumberMap.put(rs.getString("ARG_A2001"), rs.getInt("cnt"));
		}
		rs.close();
		return productNumberMap;
	}
	
	/**
	 * @Description: 关联规则算法   
	 * @return void  
	 * @throws SQLException 
	 * @throws
	 * @author feifei.liu
	 * @date 2017年2月10日 上午10:39:10
	 */
	public static void aprioriMath() throws SQLException {
		Integer orderNumber = getOrderNumber();//AB同时出现的次数
		Map<String, Integer> productNumberMap = getProductNumberMap();//AB同时出现的次数
		Map<String, Integer> abNumberMap = getABNumberMap();//AB同时出现的次数
		DecimalFormat df = new DecimalFormat("######0.0000");  
		for(Entry<String, Integer> entry:abNumberMap.entrySet()) {
			String tempKey = entry.getKey();
			Integer tempValue = entry.getValue();
			// 支持度（A、B同时出现的次数/总消费单次数）
			// 置信度（A、B同时出现的次数/A出现的次数）
			// 提升度（置信度*总消费订单次数/B产品出现的次数====》》（A、B同时出现的次数*总消费单次数）/（A出现的次数*B出现的次数））
			// A->B
			String[] abstrs = tempKey.split("_");
			String astr = abstrs[0];
			String bstr = abstrs[1];
//			String tempKeyAdverse = abstrs[1]+"_"+abstrs[0];
			double s = Double.valueOf(df.format(Double.valueOf(tempValue)/Double.valueOf(orderNumber)));
			double i = Double.valueOf(df.format(Double.valueOf(tempValue)/Double.valueOf(productNumberMap.get(astr))));
			double t = i*orderNumber/Double.valueOf(productNumberMap.get(bstr));
			if(i > 0.1) {
//				System.out.println(tempKey+":::"+tempValue+" -> "+productNumberMap.get(astr));
				System.out.println(astr+"->"+bstr+": "+s+"、"+BigDecimal.valueOf(i).toPlainString()+"、"+t);
			}
			// B->A
			double sAdverse = s;
			double iAdverse = tempValue/productNumberMap.get(bstr);
			double tAdverse = i*orderNumber/productNumberMap.get(astr);
			if(iAdverse > 0.1) {
//				System.out.println(tempKey+":::"+tempValue+" <- "+productNumberMap.get(bstr));
				System.out.println(bstr+"->"+astr+": "+sAdverse+"、"+BigDecimal.valueOf(iAdverse).toPlainString()+"、"+tAdverse);
			}
			
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
//		getProductNumber();
//		getOrderNumber();
//		getABNumberMap();
		aprioriMath();
	}
}
