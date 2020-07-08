package com.fjp.controller;

import com.fjp.service.AdminService;
import com.fjp.view.LoginFrame;
import com.fjp.view.RegisterDialog;

import javax.swing.*;

/**
 * 登录控制类
 */
public class LoginController extends LoginFrame {
    private static String username;
    private static AdminService adminService = new AdminService();

    @Override
    protected void showAdminFrame(String username, String password) {
        if (adminService.login(username, password) != null) {
            dispose();
            new FruitController().setVisible(true);
            LoginController.username = username;
        } else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误！");
        }
    }

    @Override
    protected void showRegisterFrame() {
        new RegisterDialog().setVisible(true);
    }


    public static String resetPassword(String password, String newPassword) {
        return adminService.resetPassword(username, newPassword, password);
    }

    public static String register(String username, String password) {
        return adminService.register(username, password);
    }
}
