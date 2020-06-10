package com.sorm.bean;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.bean
 * @ClassName: Configuration
 * @Author: HAND_WEILI
 * @Description: 封装管理配置信息
 * @Date: 2020/6/10 13:06
 * @Version: 1.0
 */
public class Configuration {

    /**
     * 驱动类
     */
    private String driver;
    /**
     * jdbc的地址
     */
    private String url;

    private String   user;
    private String   password;
    private String   usingDB;
    private String  srcPath;
    private String  poPackage;
}
