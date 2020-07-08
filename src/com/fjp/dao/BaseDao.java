package com.fjp.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseDao {

    int doCUD(String sql, Connection conn, Map<Integer, Object> params);

    List query(String sql, Connection conn, Map<Integer, Object> params, Class type);
}
