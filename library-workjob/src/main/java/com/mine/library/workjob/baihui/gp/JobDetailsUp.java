package com.mine.library.workjob.baihui.gp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 本类用于更新GP数据库中定时任务用户对象
 * Created by feifei.liu on 2017/6/1.
 */
public class JobDetailsUp {
    private static String url = "jdbc:postgresql://192.168.19.120:5432/baihui?useUnicode=true&characterEncoding=utf-8" ;
    private static String username = "baihui" ;
    private static String password = "S1cloONi1ETX" ;
    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println(conn+"::");
        conn.close();
    }

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
            Class.forName("org.postgresql.Driver");
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

}
