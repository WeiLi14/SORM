package com.sorm.bean;

import java.util.Map;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.bean
 * @ClassName: TableInfo
 * @Author: HAND_WEILI
 * @Description: 存储表信息，表结构
 * @Date: 2020/6/10 13:07
 * @Version: 1.0
 */
public class TableInfo {
    /**
     * 表名
     */
    private String tname;

    /**
     * 所有字段的信息
     */
    private Map<String,ColumnInfo> columns;

    /**
     * 唯一主键(目前只支持一个主键的情况)
     */
    private ColumnInfo onlyKey;

    public TableInfo() {
    }

    public TableInfo(String tname, Map<String, ColumnInfo> columns, ColumnInfo onlyKey) {
        this.tname = tname;
        this.columns = columns;
        this.onlyKey = onlyKey;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyKey() {
        return onlyKey;
    }

    public void setOnlyKey(ColumnInfo onlyKey) {
        this.onlyKey = onlyKey;
    }
}
