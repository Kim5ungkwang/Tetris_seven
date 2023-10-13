package kr.ac.jbnu.se.tetris.views.pages;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * mainPage에서 멀티플레이버튼을 눌렀을 때 나오는 페이지를 관리하는 메서드
 */
public class MutliPlayPage extends JPanel{
    static public final int BUTTON_WIDTH = 245;
    static public final int BUTTON_HEIGHT = 70;
    static public final int BUTTON_X = 518;
    static public final int BUTTON_Y = 290;
    @Getter
    private JPanel buttonPanel;
    private final MainPageController mainPage;
    @Getter
    static ImageIcon localPlayImg, onlineImg, undoImg;
    @Getter
    JButton localPlayBt, onlineBt, undoBt;
    KeyInput p1Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player1key.json");
    KeyInput p2Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player2key.json");
    Random p1Rand;
    Random p2Rand;

    public MutliPlayPage(MainPageController parent){
        this.mainPage = parent;
        setLayout(null);
        setSize(1280, 720);
        buttonPanel = new JPanel();
        buttonInit();
        buttonPanelInit();
        setBackground(new Color(0,0,0, 153));
        setVisible(false);

        double x= Math.random()*10000;
        p1Rand= new Random((long) x);
        p1Rand.nextInt();

        double y= Math.random()*10000;
        p2Rand= new Random((long) y);
        p2Rand.nextInt();
    }

    /**
     * 버튼이 들어갈 buttonPanel을 초기화하고 패널에 추가하는 메서드
     */
    public void buttonPanelInit(){
        buttonPanel.setLayout(null);
        buttonPanel.setSize(1280, 600);
        buttonPanel.setBounds(0, 120,1280, 600);
        buttonPanel.setBackground(new Color(0,0,0, 168));
        add(buttonPanel);
        buttonPanel.setVisible(true);
    }

    /**
     * 버튼 모양과 위치를 설정하고 buttonPanel에 추가하는 메서드
     */
    public void buttonInit(){
        localPlayImg = new ImageIcon("source/image/button/localplay.png");
        onlineImg = new ImageIcon("source/image/button/onlineplay.png");
        undoImg = new ImageIcon("source/image/button/undo.png");

        localPlayBt = new JButton(localPlayImg);
        onlineBt = new JButton(onlineImg);
        undoBt = new JButton(undoImg);

        settingButton(localPlayBt);
        settingButton(onlineBt);

        localPlayBt.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH,BUTTON_HEIGHT);
        onlineBt.setBounds(BUTTON_X, BUTTON_Y + BUTTON_HEIGHT + 30, BUTTON_WIDTH, BUTTON_HEIGHT);
        undoBt.setBounds(0, 120, BUTTON_WIDTH,BUTTON_HEIGHT);

        add(localPlayBt);
        add(onlineBt);
        add(undoBt);

        buttonAction();
    }
    /**
     * 버튼을 눌렀을 때 실행되는 action을 설정힌 메서드
     */
    public void settingButton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    public void buttonAction(){
        undoBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPage.getMainPagePanel().setVisible(true);
            }
        });
        onlineBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 온라인 모드
            }
        });
        localPlayBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTwoPlayPage localTwoPlayPage = new LocalTwoPlayPage(new Member(), p1Key, p1Rand, new Member(), p2Key, p2Rand);
                localTwoPlayPage.setVisible(true);
            }
        });
    }
}
