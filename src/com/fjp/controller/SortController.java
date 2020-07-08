package com.fjp.controller;

import com.fjp.dao.SortDao;
import com.fjp.entity.Sort;
import com.fjp.service.SortService;
import com.fjp.view.SortFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * 水果种类控制器
 */
public class SortController extends SortFrame {
    private SortService sortService;

    @Override
    protected String addSort(String name, String description) {
        return sortService.addSort(name, description);
    }

    @Override
    protected String deleteSort(Integer id) {
        return sortService.deleteSort(id);
    }

    @Override
    protected String updateSort(Integer id, String name, String description) {
        return sortService.updateSort(id, name, description);
    }

    @Override
    protected TableModel showSorts() {
        List<Sort> sorts = sortService.findAllSort();
        String[] thead = {"水果种类编号", "水果种类名称", "描述", "修改", "删除"};
        Object[][] tbody = new Object[sorts.size()][5];
        for (int i = 0; i < sorts.size(); i++) {
            Sort sort = sorts.get(i);
            tbody[i][0] = sort.getId();
            tbody[i][1] = sort.getName();
            tbody[i][2] = sort.getDescription();
            tbody[i][3] = new JButton("修改");
            tbody[i][4] = new JButton("删除");
        }
        return new DefaultTableModel(tbody, thead) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 2;
            }
        };
    }

    @Override
    protected void initService() {
        sortService = new SortService();
    }

    @Override
    protected void backToFruitFrame() {
        dispose();
        new FruitController().setVisible(true);
    }

}
