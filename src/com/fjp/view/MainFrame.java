package com.fjp.view;

import com.fjp.tools.JDBCUtils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 主窗口类
 */
public abstract class MainFrame extends JFrame {
    private JLabel titleLabel = new JLabel(new ImageIcon("FruitStore.jpg"));
    private JButton enter = new JButton("进入系统");
    private JPanel mainPanel = new JPanel();

    public MainFrame() {
        init();
        addListener();
    }

    private void init() {
        setTitle("水果超市欢迎您!");
        mainPanel.setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(null);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setIconImage(kit.createImage("title.png"));
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDBCUtils.close(null, null, JDBCUtils.getConnection());
                System.exit(0);
            }
        });
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel btnPanel = new JPanel();
        btnPanel.add(enter);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void addListener() {
        enter.addActionListener(e -> {
            showLoginFrame();
        });
    }

    //跳转到登录界面
    public abstract void showLoginFrame();
}
