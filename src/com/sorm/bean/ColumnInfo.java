package com.sorm.bean;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.bean
 * @ClassName: ColumnInfo
 * @Author: HAND_WEILI
 * @Description: 封装表中一个字段的信息
 * @Date: 2020/6/10 12:58
 * @Version: 1.0
 */

public class ColumnInfo {
    /**
     * 字段名称
     */

    private String name;

    /**
     * 字段的数据类型
     */
    private String dataType;

    /**
     * 0,主键，1、普通键 3、外键
     */
    private int keyType;

    /**
     * 空构造函数
     */
    public ColumnInfo() {
    }

    public ColumnInfo(String name, String dataType, int keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }
}
