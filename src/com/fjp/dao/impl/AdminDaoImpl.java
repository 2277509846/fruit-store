package com.fjp.dao.impl;

import com.fjp.dao.AdminDao;
import com.fjp.entity.Admin;
import com.fjp.tools.JDBCUtils;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

    @SuppressWarnings("unchecked")
    @Override
    public Admin login(String username, String password) {
        String sql = "select * from admin where username = ? and password = ?";
        Connection conn = JDBCUtils.getConnection();
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, username);
        params.put(2, password);
        List<Admin> admins = query(sql, conn, params, Admin.class);
        if (admins.size() > 0) return admins.get(0);
        return null;
    }

    @Override
    public boolean resetPassword(String username, String newPassword, String password) {
        String sql = "update admin set password = ? where username = ? and password = ?";
        Connection conn = JDBCUtils.getConnection();
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, newPassword);
        params.put(2, username);
        params.put(3, password);
        return doCUD(sql, conn, params) != 0;
    }

    @Override
    public boolean register(String username, String password) {
        String sql = "insert into admin values(?, ?)";
        Connection conn = JDBCUtils.getConnection();
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, username);
        params.put(2, password);
        return doCUD(sql, conn, params) != 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Admin findAdminByUsername(String username) {
        String sql = "select * from admin where username = ?";
        Connection conn = JDBCUtils.getConnection();
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, username);
        List<Admin> admins = query(sql, conn, params, Admin.class);
        if (admins.size() > 0) return admins.get(0);
        return null;
    }


}
