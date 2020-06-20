package com.sorm.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * description
 *
 * @author HAND_WEILI 2020/06/20 12:11
 */
public interface CallBack {

    public Object doExecute(Connection conn, PreparedStatement ps, ResultSet resultSet);
}
