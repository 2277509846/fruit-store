package com.fjp.view;

import com.fjp.entity.Sort;
import com.fjp.tools.JDBCUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * 水果列表界面
 */
public abstract class FruitFrame extends JFrame {
    protected String condition2 = "";
    protected int page = 1;
    protected int countPerPage = 15;
    protected int total;
    protected int pageCount;
    private JTable fruitTable = new JTable();
    private JScrollPane fruitPanel = new JScrollPane();
    private JPanel splitPanel = new JPanel();
    private JLabel pageLabel = new JLabel("第n页/共n页 共X条记录 | ");
    private JButton firstButton = new JButton("首页");
    private JButton previousButton = new JButton("上一页");
    private JButton nextButton = new JButton("下一页");
    private JButton lastButton = new JButton("末页");
    private JTextField condition = new JTextField(10);
    private JButton queryButton = new JButton("查询");
    private JPanel queryPanel = new JPanel();
    private JButton addButton = new JButton("添加水果");
    private JButton resetPassword = new JButton("修改密码");
    private JButton fruitSort = new JButton("水果种类");

    public FruitFrame() {
        init();
        initService();
        updateFruitFrame();
        addListener();
    }

    private void addListener() {
        queryButton.addActionListener(e -> {
            page = 1;
            condition2 = condition.getText();
            updateFruitFrame();
        });
        condition.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    page = 1;
                    condition2 = condition.getText();
                    updateFruitFrame();
                }
            }
        });
        firstButton.addActionListener(e -> {
            page = 1;
            condition.setText(condition2);
            updateFruitFrame();
        });
        previousButton.addActionListener(e -> {
            page--;
            condition.setText(condition2);
            updateFruitFrame();
        });
        nextButton.addActionListener(e -> {
            page++;
            condition.setText(condition2);
            updateFruitFrame();
        });
        lastButton.addActionListener(e -> {
            page = pageCount;
            condition.setText(condition2);
            updateFruitFrame();
        });
        addButton.addActionListener(e -> {
            new AddFruitDialog(FruitFrame.this).setVisible(true);
        });
        resetPassword.addActionListener(e -> {
            new ResetPasswordDialog().setVisible(true);
        });
        fruitSort.addActionListener(e -> {
            toSortFrame();
        });
    }

    private void init() {
        setTitle("水果列表");
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
        fruitTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        setLocationRelativeTo(null);
        queryPanel.add(condition);
        queryPanel.add(queryButton);
        queryPanel.add(resetPassword);
        queryPanel.add(fruitSort);
        splitPanel.add(pageLabel);
        splitPanel.add(firstButton);
        splitPanel.add(previousButton);
        splitPanel.add(nextButton);
        splitPanel.add(lastButton);
        splitPanel.add(addButton);
        add(queryPanel, BorderLayout.SOUTH);
        add(splitPanel, BorderLayout.CENTER);
    }

    //更新水果列表界面
    public void updateFruitFrame() {
        TableModel tableModel = showFruits();
        remove(fruitPanel);
        fruitTable.setModel(tableModel);
        fruitTable.getColumnModel().getColumn(6).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> new JButton("修改"));
        fruitTable.getColumnModel().getColumn(7).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> new JButton("删除"));
        fruitTable.getColumnModel().getColumn(6).setCellEditor(new UpdateAndDeleteFruitListener(2, FruitFrame.this));
        fruitTable.getColumnModel().getColumn(7).setCellEditor(new UpdateAndDeleteFruitListener(1, FruitFrame.this));
        fruitPanel = new JScrollPane(fruitTable);
        fruitPanel.setPreferredSize(new Dimension(400, 280));
        add(fruitPanel, BorderLayout.NORTH);
        validate();
        pageLabel.setText(String.format("第%d页/共%d页 共%d条记录 | ", page, pageCount, total));
        firstButton.setEnabled(page != 1);
        previousButton.setEnabled(page != 1);
        lastButton.setEnabled(page != pageCount);
        nextButton.setEnabled(page != pageCount);
    }

    //删除后当前页的变化
    void afterDelete() {
        total--;
        if (total % countPerPage == 0 && total != 0) page--;
        updateFruitFrame();
    }

    //添加后当前页的变化
    void afterAdd() {
        page = 1;
        condition.setText("");
        condition2 = "";
        updateFruitFrame();
        page = pageCount;
        updateFruitFrame();
    }

    //查询满足条件的水果
    protected abstract TableModel showFruits();

    /**
     * 修改指定的水果信息
     * @param id 要修改的水果id
     * @param name 修改后的水果名称
     * @param price 修改后的水果价格
     * @param unit 修改后的水果计价单位
     * @param count 修改后的水果数量
     * @param sort 修改后的水果种类名称
     * @return 系统提示信息
     */
    protected abstract String updateFruit(String id, String name, String price, String unit, String count, String sort);

    /**
     * 删除指定的水果信息
     * @param id 要删除的水果id
     * @return 系统提示信息
     */
    protected abstract String deleteFruit(Integer id);

    /**
     * 添加水果信息
     * @param name 添加的水果名称
     * @param price 添加的水果价格
     * @param unit 添加的水果计价单位
     * @param count 添加的水果数量
     * @param sort 添加的水果种类名称
     * @return 系统提示信息
     */
    protected abstract String addFruit(String name, String price, String unit, String count, String sort);

    /**
     * 查找水果种类信息
     * @return 所有水果种类信息
     */
    protected abstract List<Sort> findAllSort();

    /**
     * 根据水果种类名称查找水果种类
     * @param name 查找的水果种类名称
     * @return 查找的水果种类信息
     */
    protected abstract Sort findSortByName(String name);

    //初始化controller层的service
    protected abstract void initService();

    //跳转到水果种类列表界面
    protected abstract void toSortFrame();

}
