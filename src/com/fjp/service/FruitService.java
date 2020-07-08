package com.fjp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fjp.dao.FruitDao;
import com.fjp.dao.SortDao;
import com.fjp.dao.impl.FruitDaoImpl;
import com.fjp.dao.impl.SortDaoImpl;
import com.fjp.entity.Fruit;
import com.fjp.entity.Sort;

public class FruitService {
    private FruitDao fruitDao = new FruitDaoImpl();
    private SortDao sortDao = new SortDaoImpl();

    public Map<String, Object> findFruits(String condition, int page, int countPerPage) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Fruit> fruits = fruitDao.findFruits("%" + condition + "%");
        int begin = (page - 1) * countPerPage;
        int end = begin + countPerPage;
        int total = fruits.size();
        int pageCount = (total - 1) / countPerPage + 1;
        if (end > fruits.size()) end = fruits.size();
        result.put("message", fruits.subList(begin, end));
        result.put("total", total);
        result.put("pageCount", pageCount);
        for (Fruit fruit : fruits) {
            Sort sort = sortDao.findSortById(fruit.getSortId());
            if (sort != null) fruit.setSort(sort.getName());
        }
        return result;
    }

    public String addFruit(String name, String price, String unit, String count, String sort) {
        String message = checkFruit(name, price, unit, count, sort);
        if (message != null) return message;
        if (fruitDao.findFruitByName(name) != null) return "水果名称已存在！";
        Integer count2 = Integer.parseInt(count);
        Double price2 = Double.parseDouble(price);
        Sort sort2 = sortDao.findSortByName(sort);
        Integer sortId = sort2.getId();
        Fruit fruit = new Fruit(null, name, price2, unit, count2, sortId);
        fruitDao.addFruit(fruit);
        return "添加成功！";
    }

    public String updateFruit(String id, String name, String price, String unit, String count, String sort) {
        String message = checkFruit(name, price, unit, count, sort);
        if (message != null) return message;
        Integer id2 = Integer.parseInt(id);
        Integer count2 = Integer.parseInt(count);
        Double price2 = Double.parseDouble(price);
        Sort sort2 = sortDao.findSortByName(sort);
        if (sort2 == null) return "水果种类不存在！";
        List<Fruit> fruits = fruitDao.findFruits("%%");
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals(name) && !fruit.getId().equals(id2)) return "水果名称已存在！";
        }
        Integer sortId = sort2.getId();
        Fruit fruit = new Fruit(id2, name, price2, unit, count2, sortId);
        fruitDao.updateFruit(fruit);
        return "修改成功！";
    }

    public String deleteFruit(Integer id) {
        if (fruitDao.deleteFruit(id)) return "删除成功！";
        return "水果编号不存在！";
    }

    private String checkFruit(String name, String price, String unit, String count, String type) {
        if (name.length() > 10) return "水果名称长度不能超过10！";
        if (unit.length() > 10) return "水果计价单位长度不能超过10！";
        if (name.equals("")) return "水果名称不能为空！";
        if (unit.equals("")) return "水果计价单位不能为空！";
        try {
            int count2 = Integer.parseInt(count);
            if (count2 <= 0) return "水果数量必须为正整数！";
        } catch (NumberFormatException e) {
            return "水果数量必须为正整数！";
        }
        try {
            double price2 = Double.parseDouble(price);
            if (price2 <= 0) return "水果价格必须为正实数！";
        } catch (NumberFormatException e) {
            return "水果价格必须为正实数";
        }
        Sort sort = sortDao.findSortByName(type);
        if (sort == null) return "没有找到此类别！";
        return null;
    }
}
