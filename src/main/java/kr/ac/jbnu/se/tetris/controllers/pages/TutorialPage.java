package kr.ac.jbnu.se.tetris.controllers.pages;

import com.sun.tools.javac.Main;
import kr.ac.jbnu.se.tetris.controllers.BoardController;
import kr.ac.jbnu.se.tetris.controllers.NextBlockPanelController;
import kr.ac.jbnu.se.tetris.controllers.PlayerPage;
import kr.ac.jbnu.se.tetris.controllers.TutorialBoardController;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;
import kr.ac.jbnu.se.tetris.models.TutorialModel;
import lombok.Getter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static kr.ac.jbnu.se.tetris.models.TutorialModel.currentStepIndex;

public class TutorialPage extends PlayerPage {
    JPanel backgroundPanel;
    JFrame tutorialPageFrame;
    @Getter
    JLabel tutorialStep;
    JFrame tutorialSRSFrame; //SRS 블럭 쌓는 모양 알려주는 프레임
    JButton skipButton; // 튜토리얼 건너뛰는 버튼
    JLabel imageLabel;
    ImageIcon imageIcon;
    TutorialBoardController tutorialBoardController;


    public TutorialPage(Member member, KeyInput p1Key){
        super();
        this.tutorialPageFrame = new JFrame();
        tutorialPageFrame.setSize(1280, 720);
        tutorialPageFrame.setLayout(null);

        this.statusBar = new JLabel();
        this.gameTimer = new JLabel();
        this.tutorialStep = new JLabel();

        tutorialStep.setFont(new Font("Serif", Font.BOLD, 25));
        tutorialStep.setForeground(Color.white);


        this.board = new TutorialBoardController(this, p1Key);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setBounds(515, 110, BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
        nextBlockPanelController.setBounds(415, 110, 100, 500);
        tutorialStep.setBounds(900, 150, 350, 100);

        tutorialPageFrame.add(board);
        tutorialPageFrame.add(nextBlockPanelController);
        tutorialPageFrame.add(tutorialStep);

        backgroundPanel = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getGameBackgroundImg().getImage(), 0 , 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        backgroundPanel.setBounds(0, 0, 1280, 720);
        tutorialPageFrame.add(backgroundPanel);

        tutorialPageFrame.setVisible(true);
        tutorialPageFrame.setResizable(false);
        tutorialPageFrame.setLocationRelativeTo(null);

        tutorialPageFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    // 5번째 스텝에서 SRS하려면 블럭 어떻게 쌓아야 하는지 이미지 보여주기
    public void showImageLabel(){
        if(TutorialModel.getCurrentStepIndex() == 4){
            tutorialPageFrame.setVisible(false);
            tutorialSRSFrame.setVisible(true);
            tutorialSRSFrame.add(imageLabel);
        }
    }

    public void buttonAction(){
        /*skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tutorialPageFrame.setVisible(false);
                mainPageController.mainPagePanelInit();
            }
        });

         */
    }
}
