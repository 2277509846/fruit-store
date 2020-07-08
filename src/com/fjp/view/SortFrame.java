package com.fjp.view;

import com.fjp.tools.JDBCUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 水果种类列表界面
 */
public abstract class SortFrame extends JFrame {
    private JTable sortTable = new JTable();
    private JScrollPane sortPanel = new JScrollPane();
    private JPanel addPanel = new JPanel();
    private JButton addButton = new JButton("添加水果种类");
    private JButton back = new JButton("返回");

    public SortFrame() {
        init();
        initService();
        updateSortFrame();
        addListener();
    }

    private void addListener() {
        addButton.addActionListener(e -> {
            new AddSortDialog(SortFrame.this).setVisible(true);
        });
        back.addActionListener(e -> {
            backToFruitFrame();
        });
    }

    private void init() {
        setTitle("水果种类列表");
        setResizable(false);
        setSize(600, 400);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDBCUtils.close(null, null, JDBCUtils.getConnection());
                System.exit(0);
            }
        });
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        sortTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        setLocationRelativeTo(null);
        addPanel.add(addButton);
        addPanel.add(back);
        add(addPanel, BorderLayout.SOUTH);
    }

    //更新水果种类列表界面
    public void updateSortFrame() {
        remove(sortPanel);
        TableModel tableModel = showSorts();
        sortTable.setModel(tableModel);
        sortTable.getColumnModel().getColumn(3).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> new JButton("修改"));
        sortTable.getColumnModel().getColumn(4).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> new JButton("删除"));
        sortTable.getColumnModel().getColumn(3).setCellEditor(new UpdateAndDeleteSortListener(2, SortFrame.this));
        sortTable.getColumnModel().getColumn(4).setCellEditor(new UpdateAndDeleteSortListener(1, SortFrame.this));
        sortPanel = new JScrollPane(sortTable);
        sortPanel.setPreferredSize(new Dimension(400, 280));
        add(sortPanel, BorderLayout.NORTH);
        validate();
    }

    /**
     * 添加水果种类
     * @param name 添加的水果种类名称
     * @param description 添加的水果种类描述
     * @return 系统提示信息
     */
    protected abstract String addSort(String name, String description);

    /**
     * 删除水果种类
     * @param id 删除的水果种类id
     * @return 系统提示信息
     */
    protected abstract String deleteSort(Integer id);

    /**
     * 修改水果种类
     * @param id 修改的水果种类id
     * @param name 修改的水果种类名称
     * @param description 修改的水果种类描述
     * @return 系统提示信息
     */
    protected abstract String updateSort(Integer id, String name, String description);

    /**
     * 查询所有水果种类信息
     * @return 所有水果种类信息
     */
    protected abstract TableModel showSorts();

    //初始化controller层的service
    protected abstract void initService();

    //返回水果列表界面
    protected abstract void backToFruitFrame();

}
