package com.fjp.view;

import com.fjp.controller.LoginController;

import javax.swing.*;

/**
 * 修改密码界面
 */
public class ResetPasswordDialog extends JDialog {
    private JPanel updateFruitPanel = new JPanel();
    private JLabel passwordLabel = new JLabel("原来密码：");
    private JLabel newPasswordLabel = new JLabel("新的密码：");
    private JLabel newPasswordLabel2 = new JLabel("确认密码：");
    private JPasswordField password = new JPasswordField(10);
    private JPasswordField newPassword = new JPasswordField(10);
    private JPasswordField newPassword2 = new JPasswordField(10);
    private JButton submit = new JButton("修改");

    ResetPasswordDialog() {
        init();
        submit.addActionListener(e -> {
            String password = this.password.getText();
            String newPassword = this.newPassword.getText();
            String newPassword2 = this.newPassword2.getText();
            if (!newPassword.equals(newPassword2))
                JOptionPane.showMessageDialog(this, "两次输入密码不一致！");
            else
                JOptionPane.showMessageDialog(this, LoginController.resetPassword(password, newPassword));
        });
    }

    private void init() {
        setTitle("修改密码");
        setModal(true);
        setLocationRelativeTo(null);
        setSize(500, 300);
        setLayout(null);
        updateFruitPanel.setBounds(140, 0, 220, 280);
        updateFruitPanel.add(passwordLabel);
        updateFruitPanel.add(password);
        updateFruitPanel.add(newPasswordLabel);
        updateFruitPanel.add(newPassword);
        updateFruitPanel.add(newPasswordLabel2);
        updateFruitPanel.add(newPassword2);
        updateFruitPanel.add(submit);
        add(updateFruitPanel);
    }
}
