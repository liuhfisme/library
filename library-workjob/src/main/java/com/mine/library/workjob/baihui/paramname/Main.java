package com.mine.library.workjob.baihui.paramname;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * ClassName: Main
 * Description: //TODO
 * Created by feifei.liu on 2017/11/21 20:39
 **/
public class Main {
    private static String url = "jdbc:postgresql://192.168.1.126:5432/baihui" ;
    private static String username = "baihui" ;
    private static String password = "baihui" ;
    private static Connection connection = null;
    static {
        if (connection==null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

    }

    /**
     * @Description: 生成字段属性对外参数名称
     *
     * @param tableNameOrEntityName
     * @param projectId
     * @author feifei.liu
     * @date 2017/11/21 16:12
     */
    public static void generateParamName(String tableNameOrEntityName, String projectId) {
        FieldEnum fieldEnum = FieldEnum.valueOf(tableNameOrEntityName);
        String queryHql = "from "+fieldEnum.getEntityName()+" t where t.projectId='"+projectId+"'";
        List fields = this.findAll(queryHql);
        List nosetFields = new ArrayList();
        String indexStart = "arg";
        Set<Integer> indexs = new HashSet<>();
        //1、遍历获取已设置参数名称序号 2、拿到未设置参数名称的字段
        for (Object field:fields) {
            String fieldParamName = (String) Reflections.invokeGetter(field, "paramName");
            if (StringUtils.isNotBlank(fieldParamName)){ //遍历获取已设置参数名称序号
                if (fieldParamName.startsWith(indexStart)) {
                    indexs.add(Integer.valueOf(fieldParamName.substring(indexStart.length())));
                }
            } else { //拿到未设置参数名称的字段
                nosetFields.add(field);
            }
        }
        int indexMax = indexs.size()==0?0: Collections.max(indexs);
        for (int i=0; i<nosetFields.size(); i++) {
            Object field = nosetFields.get(i);
            String fieldName = (String) Reflections.invokeGetter(field, "name");
            String fieldParamName = (String) Reflections.invokeGetter(field, "paramName");
            ModuleFieldInitParamEnum initParamEnum = null;
            try {
                initParamEnum = ModuleFieldInitParamEnum.valueOf(fieldName.toLowerCase());
            }catch (IllegalArgumentException e) {
                logger.debug("初始化字段枚举找不到（为自定义字段）："+fieldName.toLowerCase());
            }
            //模块初始化字段参数命名
            if (initParamEnum!=null) {
                fieldParamName = initParamEnum.toString();
            } else{ //其它字段默认赋值
                int newIndex = ++indexMax;
                String newIndexStr = newIndex<10?"00"+newIndex:newIndex<100?"0"+newIndex:""+newIndex;
                fieldParamName = indexStart+newIndexStr;
            }
            Reflections.invokeSetter(field, "paramName", fieldParamName);
            nosetFields.set(i, field);
        }
        if (nosetFields.size()>0) this.save(nosetFields);
    }
}
