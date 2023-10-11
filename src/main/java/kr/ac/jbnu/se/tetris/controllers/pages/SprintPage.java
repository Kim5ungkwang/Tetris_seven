package kr.ac.jbnu.se.tetris.controllers.pages;

import kr.ac.jbnu.se.tetris.controllers.*;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SprintPage extends PlayerPage {
    JPanel backgroundPanel;
    @Getter
    JLabel removedLine;
    JFrame gameClearFrame;
    JFrame gameOverFrame;
    JFrame sprintPageFrame;
    JButton goBackButton1;
    JButton goBackButton2;
    public SprintPage(Member member, KeyInput keyInput){
        super();
        sprintPageFrame = new JFrame();
        sprintPageFrame.setSize(1280, 720);
        sprintPageFrame.setLayout(null);

        this.removedLine = new JLabel("0");
        this.gameTimer = new JLabel("00 : 00");
        this.goBackButton1 = new JButton("돌아가기");
        this.goBackButton2 = new JButton("돌아가기");

        removedLine.setFont(new Font("Serif", Font.BOLD, 35));
        removedLine.setForeground(Color.white);
        gameTimer.setFont(new Font("Serif", Font.BOLD, 35));
        gameTimer.setForeground(Color.white);

        this.board = new SprintBoardController(this, keyInput);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        board.setBounds(515, 110, BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
        removedLine.setBounds(900,300,100, 100);
        gameTimer.setBounds(900, 150, 150, 100);
        nextBlockPanelController.setBounds(415, 110, 100, 500);

        sprintPageFrame.add(removedLine);
        sprintPageFrame.add(gameTimer);
        sprintPageFrame.add(board);
        sprintPageFrame.add(nextBlockPanelController);
        //setSize(new Dimension(1280, 720));

        backgroundPanel = new JPanel(){ //배경 패널 설정
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getGameBackgroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setBounds(0,0,1280,720);
        sprintPageFrame.add(backgroundPanel);

        sprintPageFrame.setVisible(true);
        sprintPageFrame.setResizable(false);
        sprintPageFrame.setLocationRelativeTo(null);

        sprintPageFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void gameClear(String score){
        gameClearFrame = new JFrame();
        gameClearFrame.setSize(500,250);

        JLabel clearScore = new JLabel();
        clearScore.setFont(new Font("Serif", Font.BOLD, 35));

        clearScore.setBounds(100, 50, 300, 50);
        goBackButton1.setBounds(100, 120, 300, 50);

        gameClearFrame.setLayout(null);
        gameClearFrame.add(clearScore);
        gameClearFrame.add(goBackButton1);

        gameClearFrame.setVisible(true);
        clearScore.setText("Clear Time  " + score);
        buttonAction();

        gameClearFrame.setVisible(true);
        gameClearFrame.setLocationRelativeTo(null);
    }

    @Override
    public void raiseGameOverFrame(){
       gameOverFrame = new JFrame();
        gameOverFrame.setSize(500,250);

        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 35));

        gameOverLabel.setBounds(150, 50, 300, 50);
        goBackButton2.setBounds(100, 120, 300, 50);

        gameOverFrame.setLayout(null);
        gameOverFrame.add(gameOverLabel);
        gameOverFrame.add(goBackButton2);

        gameOverFrame.setVisible(true);
        gameOverLabel.setText("Game Over");
        buttonAction();

        gameOverFrame.setVisible(true);
        gameOverFrame.setLocationRelativeTo(null);
    }

    public void buttonAction(){
        goBackButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sprintPageFrame.dispose();
                gameClearFrame.dispose();
            }
        });
        goBackButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sprintPageFrame.dispose();
                gameOverFrame.dispose();
            }
        });
    }


}
