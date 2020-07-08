package com.fjp.view;

import com.fjp.entity.Sort;

import javax.swing.*;
import java.util.List;

/**
 * 添加水果界面
 */
public class AddFruitDialog extends JDialog {
    private JPanel updateFruitPanel = new JPanel();
    private JLabel nameLabel = new JLabel("名称：");
    private JLabel priceLabel = new JLabel("价格：");
    private JLabel unitLabel = new JLabel("单位：");
    private JLabel sortIdLabel = new JLabel("类别：");
    private JLabel countLabel = new JLabel("数量：");
    private JTextField name = new JTextField(10);
    private JTextField price = new JTextField(10);
    private JTextField unit = new JTextField(10);
    private JSpinner sort = new JSpinner();
    private JTextField count = new JTextField(10);
    private JButton submit = new JButton("添加");

    AddFruitDialog(FruitFrame fruitFrame) {
        init();
        List<Sort> sorts = fruitFrame.findAllSort();
        String[] sortNames = new String[sorts.size()];
        for (int i = 0; i < sorts.size(); i++) {
            sortNames[i] = sorts.get(i).getName();
        }
        sort.setModel(new SpinnerListModel(sortNames));
        submit.addActionListener(e -> {
            String name = this.name.getText();
            String price = this.price.getText();
            String unit = this.unit.getText();
            String sort = (String) this.sort.getValue();
            String count = this.count.getText();
            JOptionPane.showMessageDialog(this, fruitFrame.addFruit(name, price, unit, count, sort));
            fruitFrame.afterAdd();
        });
    }

    private void init() {
        setTitle("添加水果信息");
        setModal(true);
        setLocationRelativeTo(null);
        setSize(500, 300);
        setLayout(null);
        updateFruitPanel.setBounds(160, 0, 170, 280);
        updateFruitPanel.add(nameLabel);
        updateFruitPanel.add(name);
        updateFruitPanel.add(priceLabel);
        updateFruitPanel.add(price);
        updateFruitPanel.add(unitLabel);
        updateFruitPanel.add(unit);
        updateFruitPanel.add(countLabel);
        updateFruitPanel.add(count);
        updateFruitPanel.add(sortIdLabel);
        updateFruitPanel.add(sort);
        updateFruitPanel.add(submit);
        add(updateFruitPanel);
    }
}
