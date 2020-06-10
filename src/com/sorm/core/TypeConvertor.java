package com.sorm.core;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.core
 * @ClassName: TypeConvertor
 * @Author: HAND_WEILI
 * @Description: 负责java数据类型和数据库数据类型的互相转换
 * @Date: 2020/6/10 12:48
 * @Version: 1.0
 */
public interface TypeConvertor {
    /**
     * 将数据库数据类型转换成Java数据类型
     * @param columnType 数据库字段的数据类型
     * @return java的数据类型
     */
    public String databaseType2JavaType(String columnType);

    /**
     * 将JAVA数据类型转换成数据库类型
     * @param javaDateType  JAVA数据类型
     * @return 数据库数据类型
     */
    public String javaType2DatabaseType(String javaDateType);
}
