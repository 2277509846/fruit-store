package com.fjp.view;

import com.fjp.entity.Sort;

import javax.swing.*;

/**
 * 修改水果界面
 */
public class UpdateSortDialog extends JDialog {
    private Sort sort;
    private JPanel updateSortPanel = new JPanel();
    private JLabel nameLabel = new JLabel("名称：");
    private JLabel descriptionLabel = new JLabel("描述：");
    private JTextField name = new JTextField(10);
    private JTextField description = new JTextField(10);
    private JButton submit = new JButton("修改");

    UpdateSortDialog(Sort sort, SortFrame sortFrame) {
        this.sort = sort;
        init();
        submit.addActionListener(e -> {
            Integer id = sort.getId();
            String name = this.name.getText();
            String description = this.description.getText();
            String message = sortFrame.updateSort(id, name, description);
            JOptionPane.showMessageDialog(this, message);
            sortFrame.updateSortFrame();
        });
    }

    private void init() {
        setTitle("修改水果信息");
        setModal(true);
        setLocationRelativeTo(null);
        setSize(500, 300);
        setLayout(null);
        name.setText(sort.getName());
        description.setText(sort.getDescription());
        updateSortPanel.setBounds(160, 30, 180, 200);
        updateSortPanel.add(nameLabel);
        updateSortPanel.add(name);
        updateSortPanel.add(descriptionLabel);
        updateSortPanel.add(description);
        updateSortPanel.add(submit);
        add(updateSortPanel);
    }
}
