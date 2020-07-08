package com.fjp.view;

import com.fjp.controller.LoginController;

import javax.swing.*;

/**
 * 修改密码界面
 */
public class RegisterDialog extends JDialog {
    private JPanel updateFruitPanel = new JPanel();
    private JLabel usernameLabel = new JLabel("输入账号：");
    private JLabel passwordLabel = new JLabel("输入密码：");
    private JLabel passwordLabel2 = new JLabel("确认密码：");
    private JTextField username = new JTextField(10);
    private JPasswordField password = new JPasswordField(10);
    private JPasswordField password2 = new JPasswordField(10);
    private JButton submit = new JButton("注册");

    public RegisterDialog() {
        init();
        submit.addActionListener(e -> {
            String username = this.username.getText();
            String password = this.password.getText();
            String password2 = this.password2.getText();
            if (!password.equals(password2))
                JOptionPane.showMessageDialog(this, "两次输入密码不一致！");
            else
                JOptionPane.showMessageDialog(this, LoginController.register(username, password));
        });
    }

    private void init() {
        setTitle("添加水果种类信息");
        setModal(true);
        setLocationRelativeTo(null);
        setSize(500, 300);
        setLayout(null);
        updateFruitPanel.setBounds(140, 0, 220, 280);
        updateFruitPanel.add(usernameLabel);
        updateFruitPanel.add(username);
        updateFruitPanel.add(passwordLabel);
        updateFruitPanel.add(password);
        updateFruitPanel.add(passwordLabel2);
        updateFruitPanel.add(password2);
        updateFruitPanel.add(submit);
        add(updateFruitPanel);
    }
}
