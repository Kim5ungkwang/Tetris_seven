package kr.ac.jbnu.se.tetris.controllers.pages;

import com.sun.tools.javac.Main;
import kr.ac.jbnu.se.tetris.controllers.*;
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
    JFrame tutorialEndFrame;
    JButton skipButton; // 튜토리얼 건너뛰는 버튼
    JLabel imageLabel;
    JLabel finishLabel;
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
        this.skipButton = new JButton("튜토리얼 스킵");

        tutorialStep.setFont(new Font("Serif", Font.BOLD, 25));
        tutorialStep.setForeground(Color.white);

        this.imageIcon = new ImageIcon("source/image/튜토리얼 SRS 안내.png");
        this.imageLabel = new JLabel(imageIcon); // 5번째 스텝에서 SRS하려면 블럭 어떻게 쌓아야 하는지 이미지 보여주기
        imageLabel.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconWidth()));

        this.board = new TutorialBoardController(this, p1Key);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setBounds(515, 110, BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
        nextBlockPanelController.setBounds(415, 110, 100, 500);
        tutorialStep.setBounds(900, 150, 350, 300);
        skipButton.setBounds(900, 500,150, 50);
        imageLabel.setBounds(100, 300, 300, 300);

        tutorialPageFrame.add(board);
        tutorialPageFrame.add(nextBlockPanelController);
        tutorialPageFrame.add(tutorialStep);
        tutorialPageFrame.add(skipButton);
        tutorialPageFrame.add(imageLabel);
        skipbuttonAction();


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

        if(TutorialModel.getCurrentStepIndex() == 6){
            tutorialFinished();
        }

        tutorialPageFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        AdapterController adapterController = new AdapterController();
        tutorialPageFrame.setFocusable(true);
        tutorialPageFrame.addKeyListener(adapterController);
        adapterController.addList(new KeyInputController(p1Key,board));
    }

    public void skipbuttonAction(){
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tutorialPageFrame.dispose();
            }
        });
    }
    public void tutorialFinished(){
        this.tutorialEndFrame = new JFrame();
        tutorialEndFrame.setSize(500, 250);
        tutorialEndFrame.setLayout(null);

        this.finishLabel = new JLabel("축하합니다! 튜토리얼 과정을 수료하셨습니다!");
        finishLabel.setFont(new Font("serif", Font.BOLD, 25));
        finishLabel.setBounds(100, 120, 300, 50);

        tutorialEndFrame.setVisible(true);
        tutorialEndFrame.add(skipButton);
        skipbuttonAction();
        tutorialEndFrame.add(finishLabel);
    }
}
