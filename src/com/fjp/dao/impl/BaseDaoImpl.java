package com.fjp.dao.impl;

import com.fjp.dao.BaseDao;
import com.fjp.tools.JDBCUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl implements BaseDao {

    @Override
    public int doCUD(String sql, Connection conn, Map<Integer, Object> params) {
        int row = 0;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            for (Integer key : params.keySet()) {
                pstm.setObject(key, params.get(key));
            }
            row = pstm.executeUpdate();
            JDBCUtils.close(null, pstm, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List query(String sql, Connection conn, Map<Integer, Object> params, Class type) {
        List list = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            list = new ArrayList();
            pstm = conn.prepareStatement(sql);
            for (Integer key : params.keySet()) {
                pstm.setObject(key, params.get(key));
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Constructor declaredConstructor = type.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                Object instance = declaredConstructor.newInstance();
                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        field.set(instance, rs.getObject(field.getName()));
                    } catch (Exception ignored) {

                    }
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstm, null);
        }
        return list;
    }
}
