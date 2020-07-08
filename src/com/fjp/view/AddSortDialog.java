package com.fjp.view;

import javax.swing.*;

/**
 * 添加水果种类界面
 */
public class AddSortDialog extends JDialog {
    private JPanel updateFruitPanel = new JPanel();
    private JLabel nameLabel = new JLabel("名称：");
    private JLabel descriptionLabel = new JLabel("描述：");
    private JTextField name = new JTextField(10);
    private JTextField description = new JTextField(10);
    private JButton submit = new JButton("添加");

    AddSortDialog(SortFrame sortFrame) {
        init();
        submit.addActionListener(e -> {
            String name = this.name.getText();
            String description = this.description.getText();
            JOptionPane.showMessageDialog(this, sortFrame.addSort(name, description));
            sortFrame.updateSortFrame();
        });
    }

    private void init() {
        setTitle("添加水果种类信息");
        setModal(true);
        setLocationRelativeTo(null);
        setSize(500, 300);
        setLayout(null);
        updateFruitPanel.setBounds(160, 0, 170, 280);
        updateFruitPanel.add(nameLabel);
        updateFruitPanel.add(name);
        updateFruitPanel.add(descriptionLabel);
        updateFruitPanel.add(description);
        updateFruitPanel.add(submit);
        add(updateFruitPanel);
    }
}
