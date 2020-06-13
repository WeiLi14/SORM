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
    /**
     * 数据库用户名
     */
    private String  user;
    /**
     * 数据库密码
     */
    private String  password;
    /**
     * 使用的数据库 Mysql
     */
    private String  usingDB;
    /**
     * 生成包的地址
     */
    private String  srcPath;
    /**
     *
     */
    private String  poPackage;
    
    /**
     * <p>
     * 配置的空构造器
     * </p>
     * 
     * @author HAND_WEILI 2020/06/10 20:05
     */

    public Configuration() {
    }


    public Configuration(String driver, String url, String user, String password, String usingDB, String srcPath, String poPackage) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.usingDB = usingDB;
        this.srcPath = srcPath;
        this.poPackage = poPackage;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsingDB() {
        return usingDB;
    }

    public void setUsingDB(String usingDB) {
        this.usingDB = usingDB;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getPoPackage() {
        return poPackage;
    }

    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }
}
