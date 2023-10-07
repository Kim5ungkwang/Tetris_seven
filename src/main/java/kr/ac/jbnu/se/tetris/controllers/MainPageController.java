package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.MainPageModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPageController extends JFrame {
    JPanel background;
    static private final int MAINPAGE_SIZE_WIDTH = 1280;
    static private final int MAINPAGE_SIZE_HEIGHT = 720;
    MainPageModel mainPageModel;
    JPanel backgroundPanel;
    JButton localPlayBt, multiPlayBt, onlinePlayBt, settingBt, singlePlayBt, sprintBt, tutorialBt;
    public MainPageController() throws IOException {
        setSize(new Dimension(MAINPAGE_SIZE_WIDTH, MAINPAGE_SIZE_HEIGHT));
        setLayout(null);
        mainPageModel = new MainPageModel();
        buttonInit();

        backgroundPanel = mainPageModel.getBackgroundPanel();
        add(backgroundPanel);
        backgroundPanel.setSize(MAINPAGE_SIZE_WIDTH,MAINPAGE_SIZE_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
    }

    public void buttonInit(){
        int buttonX = MainPageModel.BUTTON_X;
        int buttonY = MainPageModel.BUTTON_Y;
        int buttonWidth = MainPageModel.BUTTON_WIDTH;
        int buttonHeight = MainPageModel.BUTTON_HEIGHT;

        localPlayBt = mainPageModel.getLocalPlayBt();
        multiPlayBt = mainPageModel.getMultiPlayBt();
        onlinePlayBt = mainPageModel.getOnlineBt();
        settingBt = mainPageModel.getSettingBt();
        singlePlayBt = mainPageModel.getSinglePlayBt();
        sprintBt = mainPageModel.getSprintBt();
        tutorialBt = mainPageModel.getTutorialBt();

        localPlayBt.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        multiPlayBt.setBounds(buttonX, buttonY + buttonHeight + 30, buttonWidth, buttonHeight);
        settingBt.setBounds(buttonX, buttonY +  buttonHeight*2 + 60, buttonWidth, buttonHeight);

        add(localPlayBt);
        add(multiPlayBt);
        add(settingBt);
    }

}
