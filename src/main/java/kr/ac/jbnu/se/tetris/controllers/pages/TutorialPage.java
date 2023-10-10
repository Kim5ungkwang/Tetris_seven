package kr.ac.jbnu.se.tetris.controllers.pages;

import com.sun.tools.javac.Main;
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
    @Getter
    JLabel tutorialStep;
    JFrame tutorialSRSFrame; //SRS 블럭 쌓는 모양 알려주는 프레임
    JFrame tutorialPageFrame;
    JButton skipButton; // 튜토리얼 건너뛰는 버튼
    JLabel imageLabel;
    ImageIcon imageIcon;
    TutorialBoardController tutorialBoardController;


    public TutorialPage(Member member, KeyInput p1Key){
        super();

        tutorialPageFrame = new JFrame();
        tutorialPageFrame.setSize(1280, 720);
        tutorialPageFrame.setLayout(null);

        this.statusBar = new JLabel();
        this.tutorialStep = new JLabel();
        this.skipButton = new JButton("튜토리얼 스킵");
        this.imageIcon = new ImageIcon("source/image/튜토리얼 SRS 안내.png");
        this.imageLabel = new JLabel(imageIcon);

        tutorialStep.setFont(new Font("Serif", Font.BOLD, 20));
        tutorialStep.setForeground(Color.white);

        tutorialBoardController = new TutorialBoardController(this, p1Key);
        this.board = tutorialBoardController;
        this.nextBlockPanelController = new NextBlockPanelController(this);
        board.setVisible(true);
        tutorialBoardController.start();
        tutorialBoardController.startTutorial();

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        board.setBounds(515, 110, BOARD_SIZE_WIDTH,BOARD_SIZE_HEIGHT);
        tutorialStep.setBounds(800, 150, 500, 300);
        nextBlockPanelController.setBounds(415,110, 110, 500);
        imageLabel.setBounds(93, 10, 256, 256);

        tutorialPageFrame.add(tutorialStep);
        tutorialPageFrame.add(skipButton);
        tutorialPageFrame.add(board);
        tutorialPageFrame.add(nextBlockPanelController);
        setSize(new Dimension(1280, 720));


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
