package com.fjp.controller;

import com.fjp.view.MainFrame;

/**
 * 主界面操作类
 */
public class MainFrameController extends MainFrame {
    @Override
    public void showLoginFrame() {
        dispose();
        new LoginController().setVisible(true);
    }
}
