package com.sorm.core;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sorm.bean.ColumnInfo;
import com.sorm.bean.TableInfo;
import com.sorm.utils.JDBCUtils;
import com.sorm.utils.ReflectUtils;
import com.sorm.utils.StringUtils;
import com.test.po.T_user;

/**
 * 负责针对Mysql数据库的查询
 *
 * @author HAND_WEILI 2020/06/13 14:48
 */
public class MysqlQuery implements Query {

    //1、测试 是否够删除
    public static void main(String[] args) throws UnsupportedEncodingException {
       T_user t_user = new T_user();
        t_user.setId(1);
        String userName=new String("测试插入".getBytes(),"UTF-8");
        t_user.setUsername(userName);
        t_user.setPassword("234123423412341234");
        Query query = new MysqlQuery();
        // 1、测试删除方法
        // query.delete(t_user);
        // 2、测试新增方法
        // query.insert(t_user);
        // 3、测试更新方法

        //query.update(t_user,new String[]{"username","password"});

        List list= query.queryRows("select username,password from t_user where id=?",T_user.class,new Object[]{1});
        System.out.println(list);
    }

    @Override
    public int executDML(String sql, Object[] params) {
        Connection conn = DBManager.getConnection();
        // 计算返回得对象
        int count =0 ;
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            //直接调用jdbc中封装好的工具类
            JDBCUtils.handleParams(ps,params);
            count = ps.executeUpdate();
            System.out.println("已执行"+sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                // 执行完记得关闭连接
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public void insert(Object obj) {
        // obj -》对应的表中 insert inot 表名（）， values（？,？）；
        /**  思路：1、通过 obj对象获取到对应的类对象.(反射)
         *         2、通过类对象，使用最开始的TableContext.poClassTableMap.获取到对应的TableInfo信息(集合)
         *         3、通过TableInfo获取到表名。
         *         4、通过Class对象获取到对应的列名，以及对应的列值。
         *         5、通过一个  List<Objcect>将参数列表丢进去。
         *         6、通过这些信息拼凑成一个 insert 语句然后执行 executDML方法。
         */
        StringBuilder sql=new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        Class clazz = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);

        sql.append("insert into "+tableInfo.getTname()+"(");
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            if(ReflectUtils.invokeGet(field.getName(),obj)!=null){
                sql.append(field.getName()+",");
                paramList.add(ReflectUtils.invokeGet(field.getName(),obj));
            }

        }
        // sql.replace(sql.length()-1,sql.length(),")");
        sql.setCharAt(sql.length()-1,')');
        sql.append(" values(");
        for(int i=0 ; i<paramList.size();i++){
            sql.append("?,");
        }
        sql.setCharAt(sql.length()-1,')');
        System.out.println(sql);
        executDML(sql.toString(),paramList.toArray());
    }

    @Override
    public void delete(Class clazz, Object id) {
       // t_user，2--》delete from t_user id=2
        // 通过 class对象找到表对象 tableinfo
        // 反过来找数据库的信息
        TableInfo tableInfo=TableContext.poClassTableMap.get(clazz);
        // 主键
        ColumnInfo columnInfo= tableInfo.getOnlyKey();
        // 拼接sql语句
        String sql ="delete from " + tableInfo.getTname()+ " where "+columnInfo.getName()+" = ? ";
        executDML(sql,new Object[]{id});
    }

    @Override
    public void delete(Object obj) {
        // 直接传对象
        Class c =obj.getClass();
        System.out.println(TableContext.poClassTableMap);
        System.out.println(c);
        TableInfo tableInfo = TableContext.poClassTableMap.get(c);

        // 主键
        ColumnInfo columnInfo= tableInfo.getOnlyKey();
        // 通过反射机制，调用属性相对应的get方法或set方法
        // 将这种通过反射机制获取getset方法用工具类将他们封装起来，需要用的时候调用一下就好。
/*        try {
            Method m = c.getMethod("get"+ StringUtils.fristChar2UpperCase(columnInfo.getName()),null);
            Object priKeyValue = m.invoke(obj,null);
            // 我们再次调用上个方法。

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
        Object priKeyValue =ReflectUtils.invokeGet(columnInfo.getName(),obj);

        delete(c,priKeyValue);
    }

    @Override
    public int update(Object obj, String[] fieldName) {
        //  obj("user")-->update 表名 set uname =?,pwd=？ where id =？
        // 思路：都懒得写了，和新增没啥区别，小伙子自己看吧。
        Class clazz = obj.getClass();
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo onlyKey=tableInfo.getOnlyKey();

        sql.append(" update "+tableInfo.getTname()+" set ");
        for(String str:fieldName){
            sql.append(str+"=?,");
            paramList.add(ReflectUtils.invokeGet(str,obj));
        }
        sql.setCharAt(sql.length()-1,' ');
        sql.append("where "+onlyKey.getName()+"=?");
        paramList.add(ReflectUtils.invokeGet(onlyKey.getName(),obj));
        System.out.println(sql);
        return executDML(sql.toString(),paramList.toArray());
    }

    @Override
    public List queryRows(String sql, Class clazz, Object[] params) {
        /**
         * 思路：1、链接数据库
         *       2、调用查询
         *       3、设置对象值
         *       4、添加到list中
         *       5、返回list
         */
        // select username,password,id from 表名 where 条件
        Connection connection = DBManager.getConnection();
        PreparedStatement ps=null;
        ResultSet rs = null;
        List list =null;
        try {
            ps=connection.prepareStatement(sql);
            JDBCUtils.handleParams(ps,params);
            rs = ps.executeQuery();
            // 获取查询到了那些列
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()){
                if(list==null){
                    list = new ArrayList();
                }
                //查询多行，需要拼凑成好多个对象
                Object obj= clazz.newInstance();
                //拼凑列
                for(int i =0;i<rsmd.getColumnCount();i++){
                    Object columnInfo = rs.getObject(i+1);
                    ReflectUtils.invokeSet(rsmd.getColumnName(i+1),obj,columnInfo);

            }
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Object queryValue(String sql, Class clazz, Object[] params) {
        return null;
    }

    @Override
    public Object queryValue(String sql, Object[] params) {
        return null;
    }

    @Override
    public Number queryNumber(String sql, Object[] params) {
        return null;
    }
}
