package com.sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.utils
 * @ClassName: JDBCUtils
 * @Author: HAND_WEILI
 * @Description: 用来链接数据库
 * @Date: 2020/6/10 12:54
 * @Version: 1.0
 */
public class JDBCUtils {
    /**
     *给sql语句设置参数
     * @param ps 准备sql语句
     * @param params 参数列表
     */
    public static void handleParams(PreparedStatement ps, Object[] params) {
        // 给sql传参
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                // 给 ?设置参数。
                try {
                    ps.setObject(i + 1, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
