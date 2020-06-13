package com.sorm.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sorm.bean.ColumnInfo;
import com.sorm.bean.TableInfo;
import com.sorm.utils.JDBCUtils;
import com.sorm.utils.ReflectUtils;
import com.sorm.utils.StringUtils;
import com.test.po.Emp;
import com.test.po.T_user;

/**
 * 负责针对Mysql数据库的查询
 *
 * @author HAND_WEILI 2020/06/13 14:48
 */
public class MysqlQuery implements Query {

    //测试 是否够删除
    public static void main(String[] args) {
        T_user t_user = new T_user();
        t_user.setId(2);
        //调用通用方法
        Query query = new MysqlQuery();
        query.delete(t_user);
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
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public void insert(Object obj) {

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
        return 0;
    }

    @Override
    public List queryRows(String sql, Class clazz, Object[] params) {
        return null;
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
