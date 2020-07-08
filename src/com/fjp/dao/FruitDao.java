package com.fjp.dao;

import com.fjp.entity.Fruit;

import java.util.List;

public interface FruitDao {
    @SuppressWarnings("unchecked")
    List<Fruit> findFruits(String condition);

    Fruit findFruitById(Integer id);

    Fruit findFruitByName(String name);

    boolean addFruit(Fruit fruit);

    boolean deleteFruit(Integer id);

    boolean updateFruit(Fruit fruit);
}
