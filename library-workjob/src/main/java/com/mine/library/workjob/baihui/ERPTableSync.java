package com.mine.library.workjob.baihui;

import java.sql.*;
import java.util.*;

/**
 * Created by feifei.liu on 2017/4/8.
 * 本类用于百会产品ERP部署表结构同步处理(测试环境)
 */
public class ERPTableSync {
    private static String url210 = "jdbc:mysql://192.168.1.210:3307/baihui?useUnicode=true&characterEncoding=utf-8" ;
    private static String username210 = "baihui" ;
    private static String password210 = "V23jdlfafxc_write" ;
    private static String url246 = "jdbc:mysql://192.168.19.246:3306/baihui_lowercase?useUnicode=true&characterEncoding=utf-8" ;
    private static String username246 = "root" ;

    private static String password246 = "baihuiroot" ;

    private static Connection connection210 = null;
    private static Connection connection246 = null;

    private static final String FUNCTION_DESC_SQL = "DESC s_function;";

    static{
        if (connection210==null || connection246==null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection210 = DriverManager.getConnection(url210, username210, password210);
                connection246 = DriverManager.getConnection(url246, username246, password246);
                connection210.setAutoCommit(false);
                connection246.setAutoCommit(false);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库连接失败！");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("数据库连接失败！");
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取功能表结构
     * @return
     * @throws SQLException
     */
    public static List<String> getTableDesc246() throws SQLException {
        List<String> tfieldNames = new ArrayList<>();
        ResultSet rs = connection246.createStatement().executeQuery(FUNCTION_DESC_SQL);
        while(rs.next()) {
            String fieldName = rs.getString("Field");
            tfieldNames.add(fieldName);
        }
        return tfieldNames;
    }

    /**
     * 获取246库ERP添加的菜单数据
     * @return
     */
    public static Map<String, String> getFunctionInsert246() throws SQLException {
        Map<String, String> insertSqlMap = new LinkedHashMap<>();
        String functionstrsql246 = "SELECT * from s_function WHERE project_id='402893b85a65a5bc015a65bb1a440e75' AND alias"
            +" IN('verificationPay','pay','basicUnitGroup','settlement','currency','verificationReceipt','payDetail','proceeds','receiveDetail','address','baseConfig','payDetailActual','payActual','receiveDetailActual','receiveActual');";
        ResultSet rs = connection246.createStatement().executeQuery(functionstrsql246);
        String initInsertSql = "insert into s_function (";
        List<String> tfieldNames = getTableDesc246();
        for(String fieldName:tfieldNames) {
            initInsertSql +=fieldName+",";
        }
        initInsertSql = initInsertSql.substring(0, initInsertSql.length()-1)+") values (";
        while(rs.next()) {
            String alias = rs.getString("alias");
//            String newUUID = UUID.randomUUID().toString().replace("-","");
            String insertSql = initInsertSql;
            for(String fieldName:tfieldNames) {
                if ("id".equals(fieldName)&&"baseConfig".equals(alias)) {
                    insertSql += "'<?1>',";
                    continue;
                }
                if ("id".equals(fieldName)) {
                    insertSql += "'<?0>',";
                    continue;
                }
                if ("pid".equals(fieldName)&&("settlement".equals(alias)||"basicUnitGroup".equals(alias)||"currency".equals(alias)||"address".equals(alias))) {
                    insertSql += "'<?1>',";
                    continue;
                }
                if ("project_id".equals(fieldName)) {
                    insertSql += "'<?2>',";
                    continue;
                }
                if (rs.getString(fieldName)==null) {
                    insertSql += "NULL,";
                    continue;
                }
                if ("state".equals(fieldName)||"sys".equals(fieldName)||"level".equals(fieldName)||"seq".equals(fieldName)||"flag".equals(fieldName)) {
                    insertSql += rs.getString(fieldName)+",";
                    continue;
                }
                insertSql += "'"+rs.getString(fieldName)+"',";
            }
            insertSql = insertSql.substring(0, insertSql.length()-1)+");";
            insertSqlMap.put(rs.getString("alias"), insertSql);
        }
        return insertSqlMap;
    }

    /**
     * 功能表插入
     * @throws SQLException
     */
    public static void executeFuncInsert210() throws SQLException {
        Map<String, String> insertSqlMap = getFunctionInsert246();
        String projectidsql210 = "SELECT p.id FROM s_project p LEFT JOIN s_function f ON p.id=f.project_id GROUP BY p.id";
        String projectfuncsql210 = "SELECT p.id,f.alias FROM s_project p,s_function f WHERE p.id=f.project_id and f.alias"
                +" IN('verificationPay','pay','basicUnitGroup','settlement','currency','verificationReceipt','payDetail','proceeds','receiveDetail','address','baseConfig','payDetailActual','payActual','receiveDetailActual','receiveActual');";
        ResultSet projectidsRs = connection210.createStatement().executeQuery(projectidsql210);
        ResultSet projectfuncRs = connection210.createStatement().executeQuery(projectfuncsql210);
        List<String> existfuncalias = new ArrayList<>();
        System.out.println(projectfuncRs.getFetchSize());
        while (projectfuncRs.next()) {
            existfuncalias.add(projectfuncRs.getString("id")+"_"+projectfuncRs.getString("alias"));
        }
        Statement stmt = connection210.createStatement();
        //do it
        while (projectidsRs.next()) {
            String projectId = projectidsRs.getString("id");
            for (Map.Entry<String, String> entry:insertSqlMap.entrySet()) {
                if (existfuncalias.contains(projectId+"_"+entry.getKey())) {//已有过滤
                    continue;
                }
                String readyInsertSql = entry.getValue();
                String newUUID0 = UUID.randomUUID().toString().replace("-","");
                String newUUID1 = UUID.randomUUID().toString().replace("-","");
                System.out.println(readyInsertSql);
                readyInsertSql = readyInsertSql.replace("<?0>",newUUID0).replace("<?1>", newUUID1).replace("<?2>", projectId);
                stmt.addBatch(readyInsertSql);
            }
        }
        stmt.executeBatch();
        connection210.commit();
    }

    /**
     * 权限表插入
     * @throws SQLException
     */
    public static void executeRoleInsert210() throws SQLException {
        String operationSql = "select id from s_operation where ABBR in('create','update','delete','view');";
        String roleSql = "select id from s_role where type=0";
        String projectfuncsql = "SELECT f.ID as fid,p.ID as pid FROM s_function f,s_project p WHERE f.PROJECT_ID=p.ID AND f.ALIAS IN('verificationPay','pay','basicUnitGroup','settlement','currency','verificationReceipt','payDetail','proceeds','receiveDetail','address','baseConfig','payDetailActual','payActual','receiveDetailActual','receiveActual');";
        ResultSet operationRs = connection210.createStatement().executeQuery(operationSql);
        ResultSet roleRs = connection210.createStatement().executeQuery(roleSql);
        ResultSet projectfuncRs = connection210.createStatement().executeQuery(projectfuncsql);
        //由于ResultSet只能循环一次，则预处理成List集合
        Set<String> operationSet = new HashSet<>();
        while (operationRs.next()) {
            String operationId = operationRs.getString("id");
            operationSet.add(operationId);
        }
        Set<String> roleIdSet = new HashSet<>();
        while (roleRs.next()) {
            String roleId = roleRs.getString("id");
            roleIdSet.add(roleId);
        }
        Statement stmt = connection210.createStatement();
        int count = 0;
        while (projectfuncRs.next()) {
            String functionId = projectfuncRs.getString("fid");
            String projectId = projectfuncRs.getString("pid");
            for (String roleId:roleIdSet) {
                for (String operationId:operationSet) {
                    String newUUID = UUID.randomUUID().toString().replace("-","");
                    String readyInsertSql = "insert into s_role_permission (id,role_id,function_id,operation_id,project_id)"+
                            "values('"+newUUID+"','"+roleId+"','"+functionId+"','"+operationId+"','"+projectId+"')";
                    //stmt.addBatch(readyInsertSql);
                    //System.out.println(readyInsertSql);
                    //stmt.execute(readyInsertSql);

                }
            }
            System.out.println(++count);
        }
        System.out.println(roleIdSet.size());
        System.out.println(operationSet.size());
        //stmt.executeBatch();
        long s = System.currentTimeMillis();
        //connection210.commit();
        long e = System.currentTimeMillis();
        System.out.println("耗时："+(e-s));
    }

    /**
     * 功能表PID纠正处理
     * @throws SQLException
     */
    public static void executeFuncUpdate210() throws SQLException {
        String baseConfigFuncSql = "select id, project_id from s_function where alias='baseConfig';";
        ResultSet baseConfigFuncRs = connection210.createStatement().executeQuery(baseConfigFuncSql);
        Statement batchStmt = connection210.createStatement();
        while (baseConfigFuncRs.next()) {
            String baseConfigFuncId = baseConfigFuncRs.getString("id");
            String projectId = baseConfigFuncRs.getString("project_id");
            //("settlement".equals(alias)||"basicUnitGroup".equals(alias)||"currency".equals(alias)||"address".equals(alias))
            String baseConfigChildUpdateSql = "update s_function set pid='"+baseConfigFuncId+"' where project_id='"+projectId+"'"
                    +" and alias in('settlement','basicUnitGroup','currency','address')";
            batchStmt.addBatch(baseConfigChildUpdateSql);
        }
        batchStmt.executeBatch();
        connection210.commit();
    }

    public static void main(String[] args) throws SQLException {
        //executeFuncInsert210(); //功能表插入
        //executeFuncUpdate210(); //功能表纠正
        executeRoleInsert210(); //权限表插入

        connection246.close();
        connection210.close();
    }
}
