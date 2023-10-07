package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.controllers.MainPageController;
import lombok.Getter;

import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.imageio.ImageIO.read;

public class MainPageModel{
    static public final int BUTTON_WIDTH = 245;
    static public final int BUTTON_HEIGHT = 70;
    static public final int BUTTON_X = 518;
    static public final int BUTTON_Y = 290;
    @Getter
    private JPanel backgroundPanel;
    @Getter
    static ImageIcon backGroundImg, multiPlayImg, settingImg, localPlayImg, onlineImg, sprintImg, tutorialImg, singlePlayImg;
    @Getter
    JButton singlePlayBt, multiPlayBt, settingBt, localPlayBt, onlineBt, sprintBt, tutorialBt;


    public MainPageModel() throws IOException {

        backGroundImg = new ImageIcon("source/image/background/mainpagebackground.png");
        multiPlayImg = new ImageIcon("source/image/button/multiplay.png");
        settingImg = new ImageIcon("source/image/button/setting.png");
        localPlayImg = new ImageIcon("source/image/button/localplay.png");
        onlineImg = new ImageIcon("source/image/button/onlineplay.png");
        sprintImg = new ImageIcon("source/image/button/sprint.png");
        tutorialImg = new ImageIcon("source/image/button/tutorial.png");
        singlePlayImg = new ImageIcon("source/image/button/singleplay.png");

        singlePlayBt = new JButton(singlePlayImg);
        multiPlayBt = new JButton(multiPlayImg);
        settingBt = new JButton(settingImg);
        localPlayBt = new JButton(localPlayImg);
        onlineBt = new JButton(onlineImg);
        sprintBt = new JButton(sprintImg);
        tutorialBt = new JButton(tutorialImg);

        backgroundPanel = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getBackGroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setSize(1820, 720);
    }




}
