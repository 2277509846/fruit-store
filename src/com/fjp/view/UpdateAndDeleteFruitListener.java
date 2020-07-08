package com.fjp.view;

import com.fjp.entity.Fruit;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * 修改删除水果监听器
 */
public class UpdateAndDeleteFruitListener extends AbstractCellEditor implements TableCellEditor {
    private static final long serialVersionUID = 1L;

    private int mode;
    private FruitFrame fruitFrame;

    UpdateAndDeleteFruitListener(int mode, FruitFrame fruitFrame) {
        this.mode = mode;
        this.fruitFrame = fruitFrame;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (mode == 1) {
            if (JOptionPane.showConfirmDialog(fruitFrame, "是否删除此水果？") == JOptionPane.OK_OPTION) {
                Integer id = (Integer) table.getValueAt(row, 0);
                String message = fruitFrame.deleteFruit(id);
                fruitFrame.afterDelete();
                JOptionPane.showMessageDialog(fruitFrame, message);
            }
        } else {
            Integer id = (Integer) table.getValueAt(row, 0);
            String name = (String) table.getValueAt(row, 1);
            Double price = (Double) table.getValueAt(row, 2);
            String unit = (String) table.getValueAt(row, 3);
            Integer count = (Integer) table.getValueAt(row, 4);
            String sort = (String) table.getValueAt(row, 5);
            Integer sortId = null;
            if (fruitFrame.findSortByName(sort) != null) sortId = fruitFrame.findSortByName(sort).getId();
            Fruit fruit = new Fruit(id, name, price, unit, count, sortId);
            fruit.setSort(sort);
            new UpdateFruitDialog(fruit, fruitFrame).setVisible(true);
        }
        return null;
    }
}