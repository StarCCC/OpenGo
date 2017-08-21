package com.opengo.opengoapp.frame;

import com.opengo.opengoapp.frame.gui.FrameMenu;

import javax.swing.*;

/**
 * 框架程序主窗体
 * Created by statCCC on 2017/8/21.
 */
public class MainFrame extends JFrame{
    //主窗体高度
    private final int FRAME_HIGHT = 200;

    //主窗体宽度
    private final int FRAME_WIDTH = 300;

    /**
     *  构造主窗体分为四步
     *  1。初始化窗体
     *  2。初始化菜单
     *  3。初始化工具栏
     *  4。初始化组件
     */
    public MainFrame() {
        initFrame();
        initMeun();
        initToolBar();
        initComponent();
    }

    /**
     *  初始化窗体
     */
    private void initFrame() {
        setVisible(true);
        setSize(FRAME_WIDTH, FRAME_HIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     *  初始化菜单
     */
    private void initMeun() {
        FrameMenu fm = new FrameMenu();
        setJMenuBar(fm);
    }

    /**
     *  初始化工具栏
     */
    private void initToolBar() {

    }

    /**
     *  初始化组件
     */
    private void initComponent() {

    }
}
