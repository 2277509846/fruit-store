package com.fjp.dao.impl;

import com.fjp.dao.FruitDao;
import com.fjp.entity.Fruit;
import com.fjp.tools.JDBCUtils;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitDaoImpl extends BaseDaoImpl implements FruitDao {
    @SuppressWarnings("unchecked")
    @Override
    public List<Fruit> findFruits(String condition) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from fruit where name like ? or id like ? or sortId in (select id from sort where name like ?)";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, condition);
        params.put(2, condition);
        params.put(3, condition);
        return (List<Fruit>) query(sql, conn, params, Fruit.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Fruit findFruitById(Integer id) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from fruit where id = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, id);
        List<Fruit> fruits = query(sql, conn, params, Fruit.class);
        if (fruits.size() == 0) return null;
        return fruits.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Fruit findFruitByName(String name) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from fruit where name = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, name);
        List<Fruit> fruits = query(sql, conn, params, Fruit.class);
        if (fruits.size() == 0) return null;
        return fruits.get(0);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into fruit(name, price, unit, sortId, count) values(?, ?, ?, ?, ?)";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, fruit.getName());
        params.put(2, fruit.getPrice());
        params.put(3, fruit.getUnit());
        params.put(4, fruit.getSortId());
        params.put(5, fruit.getCount());
        return doCUD(sql, conn, params) != 0;
    }

    @Override
    public boolean deleteFruit(Integer id) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from fruit where id = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, id);
        return doCUD(sql, conn, params) != 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update fruit set name = ?, price = ?, unit = ?, count = ?, sortId = ? where id = ?";
        Map<Integer, Object> params = new HashMap<Integer, Object>();
        params.put(1, fruit.getName());
        params.put(2, fruit.getPrice());
        params.put(3, fruit.getUnit());
        params.put(4, fruit.getCount());
        params.put(5, fruit.getSortId());
        params.put(6, fruit.getId());
        return doCUD(sql, conn, params) != 0;
    }
}
