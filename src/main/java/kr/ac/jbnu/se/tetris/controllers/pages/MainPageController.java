package kr.ac.jbnu.se.tetris.controllers.pages;

import kr.ac.jbnu.se.tetris.controllers.MusicController;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainPageController extends JFrame {
    JPanel background;
    static private final int MAINPAGE_SIZE_WIDTH = 1280;
    static private final int MAINPAGE_SIZE_HEIGHT = 720;
    MainPageModel mainPageModel;
    MusicController sound;
    @Getter
    JPanel backgroundPanel, mainPagePanel, singlePagePanel, multiPlayPagePanel; //뒷 배경을 그린 패널, 메인 패널, 싱글 플레이 패널, 멀티플레이 패널
    JButton multiPlayBt, settingBt, singlePlayBt;
    public MainPageController() throws IOException {
        setSize(new Dimension(MAINPAGE_SIZE_WIDTH, MAINPAGE_SIZE_HEIGHT));
        setLayout(null);
        mainPageModel = new MainPageModel();
        singlePagePanel = new SinglePlayPage(this);
        multiPlayPagePanel = new MutliPlayPage(this);
        add(singlePagePanel);
        add(multiPlayPagePanel);

        mainPagePanelInit();
        buttonInit();
        backgroundPanel = mainPageModel.getBackgroundPanel();

        add(backgroundPanel);
        backgroundPanel.setSize(MAINPAGE_SIZE_WIDTH,MAINPAGE_SIZE_HEIGHT);

        setTitle("Tetris_seven");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        sound = new MusicController();
        sound.mainManuPlay();
    }

    /**
     * 버튼이 들어갈 mainPagePanel을 초기화하고 프레임에 추가하는 메서드
     */
    public void mainPagePanelInit(){
        mainPagePanel = new JPanel();
        mainPagePanel.setLayout(null);
        mainPagePanel.setBackground(new Color(0,0,0,0));
        mainPagePanel.setSize(1280, 720);
        mainPagePanel.setBounds(0,0,1280,720);
        add(mainPagePanel);
    }

    /**
     * 버튼의 모양과 위치를 설정하고 mainPAgePanel에 추가하는 메서드
     */
    public void buttonInit(){
        int buttonX = MainPageModel.BUTTON_X;
        int buttonY = MainPageModel.BUTTON_Y;
        int buttonWidth = MainPageModel.BUTTON_WIDTH;
        int buttonHeight = MainPageModel.BUTTON_HEIGHT;

        singlePlayBt = mainPageModel.getSinglePlayBt();
        multiPlayBt = mainPageModel.getMultiPlayBt();
        settingBt = mainPageModel.getSettingBt();

        singlePlayBt.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        multiPlayBt.setBounds(buttonX, buttonY + buttonHeight + 30, buttonWidth, buttonHeight);
        settingBt.setBounds(buttonX, buttonY +  buttonHeight*2 + 60, buttonWidth, buttonHeight);

        mainPagePanel.add(singlePlayBt);
        mainPagePanel.add(multiPlayBt);
        mainPagePanel.add(settingBt);
        buttonAction();
    }
    /**
     * 버튼을 눌렀을 때 실행되는 action을 설정힌 메서드
     */
    public void buttonAction(){
        singlePlayBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPagePanel.setVisible(false);
                singlePagePanel.setVisible(true);
            }
        });
        multiPlayBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPagePanel.setVisible(false);
                multiPlayPagePanel.setVisible(true);
            }
        });
        settingBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //설정 창 삽입
            }
        });
    }

}
