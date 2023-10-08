package kr.ac.jbnu.se.tetris.controllers.pages;

import kr.ac.jbnu.se.tetris.models.MainPageModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * mainPage에서 싱글플레이 버튼을 눌렀을 때 나오는 패널을 관리하는 클래스
 */
public class SinglePlayPage extends JPanel {
    static public final int BUTTON_WIDTH = 245;
    static public final int BUTTON_HEIGHT = 70;
    static public final int BUTTON_X = 518;
    static public final int BUTTON_Y = 290;
    @Getter
    private JPanel buttonPanel;
    private final MainPageController mainPage;  //메인페이지(parent)
    @Getter
    static ImageIcon undoImg, sprintImg, tutorialImg, timeAttackImg;
    @Getter
    JButton sprintBt, tutorialBt, timeAttackBt, undoBt;

    public SinglePlayPage(MainPageController parent){
        this.mainPage = parent;
        setLayout(null);
        setSize(1280, 720);
        buttonPanel = new JPanel();
        buttonInit();
        buttonPanelInit();
        setBackground(new Color(0,0,0, 153));
        setVisible(false);
    }

    /**
     * 버튼이 들어갈 패널을 초기화하고 패널에 추가한다.
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
        sprintImg = new ImageIcon("source/image/button/sprint.png");    //이미지 아이콘의 위치
        tutorialImg = new ImageIcon("source/image/button/tutorial.png");
        timeAttackImg = new ImageIcon("source/image/button/timeattack.png");
        undoImg = new ImageIcon("source/image/button/undo.png");

        sprintBt = new JButton(sprintImg);  //스프린트
        tutorialBt = new JButton(tutorialImg);  //튜토리얼
        timeAttackBt = new JButton(timeAttackImg);  //타임어택
        undoBt = new JButton(undoImg);  //뒤로가기

        sprintBt.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH,BUTTON_HEIGHT); //버튼들의 위치를 설정한다
        timeAttackBt.setBounds(BUTTON_X, BUTTON_Y + BUTTON_HEIGHT + 30, BUTTON_WIDTH, BUTTON_HEIGHT);
        tutorialBt.setBounds(BUTTON_X, BUTTON_Y +  BUTTON_HEIGHT*2 + 60, BUTTON_WIDTH, BUTTON_HEIGHT);
        undoBt.setBounds(0, 120, BUTTON_WIDTH,BUTTON_HEIGHT);

        add(sprintBt);  //버튼 프레임에 추가
        add(timeAttackBt);
        add(tutorialBt);
        add(undoBt);
        buttonAction();
    }

    /**
     * 버튼을 눌렀을 때 실행되는 action을 설정힌 메서드
     */
    public void buttonAction(){
        undoBt.addActionListener(new ActionListener() { //뒤로가기 버튼을 눌렀을 때 발생하는 이벤트
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPage.getMainPagePanel().setVisible(true);
            }
        });
        tutorialBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 여기에 튜토리얼 보드를 삽입
            }
        });
        sprintBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //여기에 스프린트 보드를 삽입
            }
        });
        timeAttackBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //여기에 타임어택 보드를 삽입
            }
        });
    }
}
