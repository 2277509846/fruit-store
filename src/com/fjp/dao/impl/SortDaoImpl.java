package com.fjp.dao.impl;

import com.fjp.dao.SortDao;
import com.fjp.entity.Sort;
import com.fjp.tools.JDBCUtils;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortDaoImpl extends BaseDaoImpl implements SortDao {
    @SuppressWarnings("unchecked")
    @Override
    public List<Sort> findAllSort() {
        String sql = "select * from sort";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        Connection conn = JDBCUtils.getConnection();
        return query(sql, conn, params, Sort.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Sort findSortByName(String name) {
        String sql = "select * from sort where name = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, name);
        Connection conn = JDBCUtils.getConnection();
        List<Sort> sorts = query(sql, conn, params, Sort.class);
        if (sorts.size() > 0) return sorts.get(0);
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Sort findSortById(Integer id) {
        String sql = "select * from sort where id = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, id);
        Connection conn = JDBCUtils.getConnection();
        List<Sort> sorts = query(sql, conn, params, Sort.class);
        if (sorts.size() > 0) return sorts.get(0);
        return null;
    }

    @Override
    public boolean addSort(String name, String description) {
        String sql = "insert into sort(name, description) values(?, ?)";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, name);
        params.put(2, description);
        Connection conn = JDBCUtils.getConnection();
        return doCUD(sql, conn, params) != 0;
    }

    @Override
    public boolean updateSort(Integer id, String name, String description) {
        String sql = "update sort set name = ?, description = ? where id = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, name);
        params.put(2, description);
        params.put(3, id);
        Connection conn = JDBCUtils.getConnection();
        return doCUD(sql, conn, params) != 0;
    }

    @Override
    public boolean deleteSort(Integer id) {
        String sql = "delete from sort where id = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, id);
        Connection conn = JDBCUtils.getConnection();
        return doCUD(sql, conn, params) != 0;
    }
}
