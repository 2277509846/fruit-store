package com.fjp.view;

import com.fjp.tools.JDBCUtils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 登录界面
 */
public abstract class LoginFrame extends JFrame {
    private JLabel usernameLabel = new JLabel("账号：");
    private JLabel passwordLabel = new JLabel("密码：");
    private JTextField username = new JTextField(10);
    private JPasswordField password = new JPasswordField(10);
    private JButton login = new JButton("登录");
    private JButton register = new JButton("注册");
    private JPanel loginPanel = new JPanel();

    public LoginFrame() {
        init();
        login.addActionListener(e -> {
            showAdminFrame(username.getText(), password.getText());
        });
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) showAdminFrame(username.getText(), password.getText());
            }
        });
        register.addActionListener(e -> {
            showRegisterFrame();
        });
    }

    private void init() {
        setTitle("登录界面");
        setSize(600, 400);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDBCUtils.close(null, null, JDBCUtils.getConnection());
                System.exit(0);
            }
        });
        loginPanel.setBounds(215, 40, 170, 280);
        loginPanel.add(usernameLabel);
        loginPanel.add(username);
        loginPanel.add(passwordLabel);
        loginPanel.add(password);
        loginPanel.add(login);
        loginPanel.add(register);
        add(loginPanel);
    }

    /**
     * 验证登录信息
     * @param username 登录的账号
     * @param password 登录的密码
     */
    protected abstract void showAdminFrame(String username, String password);

    //跳转到注册界面
    protected abstract void showRegisterFrame();
}
