package com.fjp.view;

import com.fjp.entity.Sort;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * 修改删除水果监听器
 */
public class UpdateAndDeleteSortListener extends AbstractCellEditor implements TableCellEditor {
    private static final long serialVersionUID = 1L;

    private int mode;
    private SortFrame sortFrame;

    UpdateAndDeleteSortListener(int mode, SortFrame sortFrame) {
        this.mode = mode;
        this.sortFrame = sortFrame;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (mode == 1) {
            if (JOptionPane.showConfirmDialog(sortFrame, "是否删除此水果种类？") == JOptionPane.OK_OPTION) {
                Integer id = (Integer) table.getValueAt(row, 0);
                String message = sortFrame.deleteSort(id);
                JOptionPane.showMessageDialog(sortFrame, message);
                sortFrame.updateSortFrame();
            }
        } else {
            Integer id = (Integer) table.getValueAt(row, 0);
            String name = (String) table.getValueAt(row, 1);
            String description = (String) table.getValueAt(row, 2);
            Sort sort = new Sort(id, name, description);
            new UpdateSortDialog(sort, sortFrame).setVisible(true);
            sortFrame.updateSortFrame();
        }
        return null;
    }
}