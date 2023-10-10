package kr.ac.jbnu.se.tetris.controllers.pages;

import kr.ac.jbnu.se.tetris.controllers.NextBlockPanelController;
import kr.ac.jbnu.se.tetris.controllers.PlayerPage;
import kr.ac.jbnu.se.tetris.controllers.TimeAttackBoardController;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TimeAttackPage extends PlayerPage {
    JPanel backgroundPanel;
    @Getter
    JLabel removedLine;
    @Getter
    JLabel timeLimit;
    JFrame gameClearFrame;
    JFrame timeAttackPageFrame;
    JButton goBackButton;
    TimeAttackBoardController timeAttackBoardController;
    public TimeAttackPage(Member member, KeyInput keyInput){
        super();
        timeAttackPageFrame = new JFrame();
        timeAttackPageFrame.setSize(1280, 720);
        timeAttackPageFrame.setLayout(null);

        this.removedLine = new JLabel("0");
        this.gameTimer = new JLabel("00 : 00"); //안씀
        this.goBackButton = new JButton("돌아가기");
        this.timeLimit = new JLabel("00 : 00");

        removedLine.setFont(new Font("Serif", Font.BOLD, 35));
        removedLine.setForeground(Color.white);
        //gameTimer.setFont(new Font("Serif", Font.BOLD, 25));    //
        //gameTimer.setForeground(Color.white);
        timeLimit.setFont(new Font("Serif", Font.BOLD, 35));    //
        timeLimit.setForeground(Color.white);   //

        this.timeAttackBoardController = new TimeAttackBoardController(this, keyInput);
        board = timeAttackBoardController;
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        board.setBounds(515, 110, BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
        removedLine.setBounds(900,300,100, 100);
        timeLimit.setBounds(900, 150, 150, 100);
        //gameTimer.setBounds(900, 450, 150, 150);
        nextBlockPanelController.setBounds(415, 110, 100, 500);

        timeAttackPageFrame.add(removedLine);
        timeAttackPageFrame.add(timeLimit); //
        //timeAttackPageFrame.add(gameTimer);
        timeAttackPageFrame.add(board);
        timeAttackPageFrame.add(nextBlockPanelController);
        setSize(new Dimension(1280, 720));

        backgroundPanel = new JPanel(){ //배경 패널 설정
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getGameBackgroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setBounds(0,0,1280,720);
        timeAttackPageFrame.add(backgroundPanel);

        timeAttackPageFrame.setVisible(true);
        timeAttackPageFrame.setResizable(false);
        timeAttackPageFrame.setLocationRelativeTo(null);

        timeAttackPageFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        timeAttackPageFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // 창이 닫힐 때 board 객체 정리
                board.getTimer().stop(); // 게임 루프 중지
                timeAttackBoardController.getReverseCountTimer().stop();
                timeAttackPageFrame.getContentPane().removeAll(); // 컴포넌트 제거
                timeAttackPageFrame.dispose(); // 창 닫기
            }
        });

    }
    public void gameClear(String score){
        gameClearFrame = new JFrame();
        gameClearFrame.setSize(500,250);

        JLabel clearScore = new JLabel();
        clearScore.setFont(new Font("Serif", Font.BOLD, 35));

        clearScore.setBounds(100, 50, 300, 50);
        goBackButton.setBounds(100, 120, 300, 50);

        gameClearFrame.setLayout(null);
        gameClearFrame.add(clearScore);
        gameClearFrame.add(goBackButton);

        gameClearFrame.setVisible(true);
        clearScore.setText("Clear Point  " + score);
        buttonAction();

        gameClearFrame.setVisible(true);
        gameClearFrame.setLocationRelativeTo(null);


    }
    public void buttonAction(){
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeAttackPageFrame.dispose(); // 현재 프레임 닫기
                gameClearFrame.dispose(); // 다른 프레임도 닫기
            }
        });
    }

}
