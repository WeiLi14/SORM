package com.sorm.core;

/**
 * Mysql数据库类型转换到JAVA数据类型
 *
 * @author HAND_WEILI 2020/06/10 21:05
 */
public class MySqlTypeConvertor implements TypeConvertor {


    @Override
    public String databaseType2JavaType(String columnType) {
        // 传入 varchar--->String
       if("varchar".equalsIgnoreCase(columnType)
               ||"text".equalsIgnoreCase(columnType)){
           return "String";
       }else if("int".equalsIgnoreCase(columnType)
               ||"tinyint".equalsIgnoreCase(columnType)
               ||"smallint".equalsIgnoreCase(columnType)
               ||"integer".equalsIgnoreCase(columnType)
               ){
            return "Integer";
       }else if("double".equalsIgnoreCase(columnType)||"float".equalsIgnoreCase(columnType)){
           return  "Double";
       }else if("bigint".equalsIgnoreCase(columnType)){
           return "long";
       }else if("clob".equalsIgnoreCase(columnType)){
           return "Clob";
       }else if("blob".equalsIgnoreCase(columnType)){
           return "Blob";
       }else if("date".equalsIgnoreCase(columnType)){
           return "java.sql.Date";
       } else if("timestamp".equalsIgnoreCase(columnType)){
           return "java.sql.Timestamp";
       } else if("datetime".equalsIgnoreCase(columnType)){
           return "java.sql.Date";
       }
        return null;
    }

    @Override
    public String javaType2DatabaseType(String javaDateType) {
        return null;
    }
}
