package com.sorm.core;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.sorm.bean.Configuration;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.core
 * @ClassName: DBManager
 * @Author: HAND_WEILI
 * @Description: 根据配置信息，维持链接对象的管理
 * @Date: 2020/6/10 12:54
 * @Version: 1.0
 */
public class DBManager {
    // 管理全局的配置信息
    private static Configuration conf;

    /**
     * 静态代码块用来加载链接池的信息。
     */
    static {
        // 加载制定的资源文件，或者用xml使用
        Properties pros = new Properties();
        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取配置信息
        conf = new Configuration();
        conf.setDriver(pros.getProperty("driver"));
        conf.setUrl(pros.getProperty("url"));
        conf.setUser(pros.getProperty("user"));
        conf.setPassword(pros.getProperty("password"));
        conf.setUsingDB(pros.getProperty("usingDB"));
        conf.setSrcPath(pros.getProperty("srcPath"));
        conf.setPoPackage(pros.getProperty("poPackage"));

        // 维持链接对象
    }

    /**
     * 通过配置文件类，获取数据库链接。
     * @return
     */
    public static Connection getConnection(){
        try {
            Class.forName(conf.getDriver());
            //目前直接建立链接，后面提供链接池
            return DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获取Configuration对象
    public static Configuration getConf(){
        return conf;
    }

}
