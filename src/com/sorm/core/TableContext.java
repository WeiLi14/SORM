package com.sorm.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sorm.bean.ColumnInfo;
import com.sorm.bean.TableInfo;
import com.sorm.utils.JAVAFileUtils;
import com.sorm.utils.StringUtils;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.core
 * @ClassName: TableContext
 * @Author: HAND_WEILI
 * @Description: 管理数据库表结构和类结构
 * @Date: 2020/6/10 12:53
 * @Version: 1.0
 */
public class TableContext {
    // 获取数据库的元信息
    /**
     * 表名为key，表信息对象为value ,这个相当于将所有的表信息都存到了当前的Map中
     */
    public static Map<String,TableInfo> tables = new HashMap<String,TableInfo>();

    /**
     * 将po的class对象和表信息对象关联起来，便于重用！
     */
    public static  Map<Class,TableInfo>  poClassTableMap = new HashMap<Class,TableInfo>();

    private TableContext(){}

     // 通过静态代码块调用的时候进行加载数据库的表信息
    static {
        try {
            //初始化获得表的信息
            Connection con = DBManager.getConnection();
            DatabaseMetaData dbmd = con.getMetaData();

            ResultSet tableRet = dbmd.getTables("testjdbc", "%","%",new String[]{"TABLE"});

            while(tableRet.next()){
                String tableName = (String) tableRet.getObject("TABLE_NAME");

                TableInfo ti = new TableInfo(tableName, new ArrayList<ColumnInfo>()
                        ,new HashMap<String, ColumnInfo>());
                tables.put(tableName, ti);

                ResultSet set = dbmd.getColumns(null, "%", tableName, "%");  //查询表中的所有字段
                while(set.next()){
                    ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"),
                            set.getString("TYPE_NAME"), 0);
                    ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
                }

                ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);  //查询t_user表中的主键
                while(set2.next()){
                    ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
                    ci2.setKeyType(1);  //设置为主键类型
                    ti.getPriKeys().add(ci2);
                }

                if(ti.getPriKeys().size()>0){  //取唯一主键。。方便使用。如果是联合主键。则为空！
                    ti.setOnlyKey(ti.getPriKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //更新类结构
        updateJavaPOFile();

        //加载po包下面所有的类，便于重用，提高效率！
         loadPOTables();
    }

    /**
     * 根据表结构，更新配置的po包下面的java类
     * 实现了从表结构转化到类结构
     */
    public static void updateJavaPOFile(){
        Map<String,TableInfo> map = TableContext.tables;
        for(TableInfo t:map.values()){
            JAVAFileUtils.createJavaPOFile(t,new MySqlTypeConvertor());
        }
    }

    /**
     * 加载po包下面的类
     */
   public static void loadPOTables(){

        for(TableInfo tableInfo:tables.values()){
            try {
                // 加载映射类的map
                Class c = Class.forName(DBManager.getConf().getPoPackage()
                        +"."+ StringUtils.fristChar2UpperCase(tableInfo.getTname()));
                poClassTableMap.put(c, tableInfo);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        // 测试是否已经读取到所有的表信息
        Map<String,TableInfo>  tables = TableContext.tables;
        System.out.println(tables);
    }
}
