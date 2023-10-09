package kr.ac.jbnu.se.tetris.controllers.pages;

import kr.ac.jbnu.se.tetris.controllers.NextBlockPanelController;
import kr.ac.jbnu.se.tetris.controllers.PlayerPage;
import kr.ac.jbnu.se.tetris.controllers.SprintBoardController;
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
    JButton goBackButton;
    public SprintPage(Member member, KeyInput keyInput){
        super();
        this.removedLine = new JLabel("0");
        this.gameTimer = new JLabel("00 : 00");
        this.goBackButton = new JButton("돌아가기");

        removedLine.setFont(new Font("Serif", Font.BOLD, 35));
        removedLine.setForeground(Color.white);
        gameTimer.setFont(new Font("Serif", Font.BOLD, 35));
        gameTimer.setForeground(Color.white);

        setLayout(null);
        this.board = new SprintBoardController(this, keyInput);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        board.setBounds(515, 110, BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
        removedLine.setBounds(800,300,100, 100);
        gameTimer.setBounds(800, 150, 150, 100);
        nextBlockPanelController.setBounds(415, 110, 100, 500);
        add(removedLine);
        add(gameTimer);
        add(board);
        add(nextBlockPanelController);
        setSize(new Dimension(1280, 720));

        backgroundPanel = new JPanel(){ //배경 패널 설정
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getGameBackgroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setBounds(0,0,1280,720);
        add(backgroundPanel);


        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
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
        clearScore.setText("Clear Time  " + score);
        buttonAction();

        gameClearFrame.setVisible(true);
        gameClearFrame.setLocationRelativeTo(null);
    }
    public void buttonAction(){
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameClearFrame.setVisible(false);
                setVisible(false);
            }
        });
    }

}
