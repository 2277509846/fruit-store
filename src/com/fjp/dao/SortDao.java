package com.fjp.dao;

import com.fjp.entity.Sort;

import java.util.List;

public interface SortDao {
    @SuppressWarnings("unchecked")
    List<Sort> findAllSort();

    Sort findSortByName(String name);

    Sort findSortById(Integer id);

    boolean addSort(String name, String description);

    boolean updateSort(Integer id, String name, String description);

    boolean deleteSort(Integer id);
}
