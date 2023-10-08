package kr.ac.jbnu.se.tetris.models;

import lombok.Getter;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

import static javax.imageio.ImageIO.read;

public class MainPageModel{
    static public final int BUTTON_WIDTH = 245;
    static public final int BUTTON_HEIGHT = 70;
    static public final int BUTTON_X = 518;
    static public final int BUTTON_Y = 290;
    @Getter
    private JPanel backgroundPanel, multiPlayPagePanel, singlePlayPagePanel;    //백그라운드, 멀티플레이 누르면나오는 패널, 싱글플레이 누르면 나오는 패널
    @Getter
    static ImageIcon backGroundImg, multiPlayImg, settingImg, singlePlayImg, gameBackgroundImg;
    @Getter
    JButton singlePlayBt, multiPlayBt, settingBt;


    public MainPageModel() throws IOException {
        gameBackgroundImg = new ImageIcon("source/image/background/gamebackground.png");
        backGroundImg = new ImageIcon("source/image/background/mainpagebackground.png");    //버튼 이미지 아이콘 위치
        multiPlayImg = new ImageIcon("source/image/button/multiplay.png");
        settingImg = new ImageIcon("source/image/button/setting.png");
        singlePlayImg = new ImageIcon("source/image/button/singleplay.png");

        singlePlayBt = new JButton(singlePlayImg);  //버튼 제작
        multiPlayBt = new JButton(multiPlayImg);
        settingBt = new JButton(settingImg);

        backgroundPanel = new JPanel(){ //배경 패널 설정
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getBackGroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setSize(1280, 720);
        singlePlayPagePanel = new JPanel();
        multiPlayPagePanel = new JPanel();
    }


    public void multiPlayPagePanelInit(){
        multiPlayPagePanel.setSize(1280, 720);
        multiPlayPagePanel.setBackground(new Color(65, 65, 65, 179));
        multiPlayBt.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH,BUTTON_HEIGHT);
    }





}
