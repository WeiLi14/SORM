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
public class MysqlQuery extends Query {

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















}
