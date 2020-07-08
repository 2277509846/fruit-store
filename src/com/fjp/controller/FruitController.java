package com.fjp.controller;

import com.fjp.entity.Fruit;
import com.fjp.entity.Sort;
import com.fjp.service.FruitService;
import com.fjp.service.SortService;
import com.fjp.view.FruitFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Map;

/**
 * 水果界面控制类
 */
public class FruitController extends FruitFrame {
    private FruitService fruitService;
    private SortService sortService;
    protected void initService() {
        fruitService = new FruitService();
        sortService = new SortService();
    }

    @Override
    protected void toSortFrame() {
        dispose();
        new SortController().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected TableModel showFruits() {
        String[] thead = {"水果编号", "水果名称", "水果单价(/元)", "计价单位", "数量", "类别", "修改", "删除"};
        Map<String, Object> map = fruitService.findFruits(condition2, page, countPerPage);
        List<Fruit> fruits = (List<Fruit>) map.get("message");
        total = (int) map.get("total");
        pageCount = (int) map.get("pageCount");
        Object[][] tbody = new Object[fruits.size()][8];
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            tbody[i][0] = fruit.getId();
            tbody[i][1] = fruit.getName();
            tbody[i][2] = fruit.getPrice();
            tbody[i][3] = fruit.getUnit();
            tbody[i][4] = fruit.getCount();
            tbody[i][5] = fruit.getSort();
            tbody[i][6] = new JButton("修改");
            tbody[i][7] = new JButton("删除");
        }
        return new DefaultTableModel(tbody, thead) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 5;
            }
        };
    }

    @Override
    protected String updateFruit(String id, String name, String price, String unit, String count, String sort) {
        return fruitService.updateFruit(id, name, price, unit, count, sort);
    }

    @Override
    protected String deleteFruit(Integer id) {
        return fruitService.deleteFruit(id);
    }

    @Override
    protected String addFruit(String name, String price, String unit, String count, String sort) {
        return fruitService.addFruit(name, price, unit, count, sort);
    }

    @Override
    protected List<Sort> findAllSort() {
        return sortService.findAllSort();
    }

    @Override
    protected Sort findSortByName(String name) {
        return sortService.findSortByName(name);
    }


}
