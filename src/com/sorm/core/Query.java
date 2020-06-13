package com.sorm.core;

import java.util.List;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.core
 * @ClassName: Query
 * @Author: HAND_WEILI
 * @Description: 负责查询（对外提供服务的核心类）
 * @Date: 2020/6/7 17:10
 * @Version: 1.0
 */
public interface Query {

    /**
     * 直接执行一个DML语句
     *
     * @param sql
     * @param params 参数
     * @return 执行后影响的行数
     */
    public int executDML(String sql, Object[] params);

    /**
     * 将一个对象直接存储到数据库中
     *
     * @param obj
     */
    public void insert(Object obj);

    /**
     * @param clazz
     * @param id
     */
    public void delete(Class clazz, Object id);

    /**
     * 直接通过对象删除
     *
     * @param obj
     */
    public void delete(Object obj);

    /**
     * 更新对象记录
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public int update(Object obj, String[] fieldName);

    /**
     * 查询多行返回记录并将数据封装到LIST中去
     *
     * @param sql    查询语句
     * @param clazz  封装数据的javabena
     * @param params
     * @return 返回查询到的结果
     */
    public List queryRows(String sql, Class clazz, Object[] params);

    /**
     * 返回一个记录
     *
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    public Object queryValue(String sql, Class clazz, Object[] params);

    /**
     * 返回一个对象一个值
     *
     * @param sql
     * @param params
     * @return
     */
    public Object queryValue(String sql, Object[] params);

    /**
     * 返回一个对象一个值
     *
     * @param sql
     * @param params
     * @return
     */
    public Number queryNumber(String sql, Object[] params);

}
