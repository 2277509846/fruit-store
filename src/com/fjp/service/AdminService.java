package com.fjp.service;

import com.fjp.dao.AdminDao;
import com.fjp.dao.impl.AdminDaoImpl;
import com.fjp.entity.Admin;

public class AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    public Admin login(String username, String password) {
        return adminDao.login(username, password);
    }

    public String resetPassword(String username, String newPassword, String password) {
        if (newPassword.equals("") || password.equals("")) return "密码不能为空！";
        if (newPassword.length() > 10) return "密码长度不能超过10！";
        if (adminDao.resetPassword(username, newPassword, password)) return "修改成功！";
        return "密码错误！";
    }

    public String register(String username, String password) {
        if (username.equals("")) return "账号不能为空！";
        if (password.equals("")) return "密码不能为空！";
        if (username.length() > 10) return "账号长度不能超过10！";
        if (password.length() > 10) return "密码长度不能超过10！";
        if (adminDao.findAdminByUsername(username) != null) return "账号已存在！";
        adminDao.register(username, password);
        return "注册成功！";
    }
}
