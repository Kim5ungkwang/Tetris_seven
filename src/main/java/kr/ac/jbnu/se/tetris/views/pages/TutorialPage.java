package kr.ac.jbnu.se.tetris.views.pages;

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

public class TutorialPage extends PlayerPage {
    JPanel backgroundPanel;
    @Getter
    JLabel tutorialStep;
    JFrame tutorialPageFrame;
    JFrame tutorialEndFrame;
    JButton skipButton; // 튜토리얼 건너뛰는 버튼
    JButton finishButton;
    JButton resetButton;
    JLabel imageLabel;
    JLabel finishLabel;
    ImageIcon imageIcon;
    JProgressBar progressBar;
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
        skipButton.setForeground(Color.black);
        this.resetButton = new JButton("다시하기");

        tutorialStep.setFont(new Font("SensSerif", Font.BOLD, 20));
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
        skipButton.setBounds(900, 500,120, 50);
        imageLabel.setBounds(100, 300, 300, 300);
        resetButton.setBounds(900, 560, 120, 50);

        tutorialPageFrame.add(board);
        tutorialPageFrame.add(nextBlockPanelController);
        tutorialPageFrame.add(tutorialStep);
        tutorialPageFrame.add(skipButton);
        tutorialPageFrame.add(imageLabel);
        tutorialPageFrame.add(resetButton);

        skipbuttonAction();
        resetbuttonAction();
        resetButton.setFocusable(false); // 버튼 선택 시 생기는 테두리 활성화


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

        /*if(TutorialModel.getCurrentStepIndex() == 6 ){
            tutorialFinished();
        }*/

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
    public void finishbuttonAction(){
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tutorialEndFrame.dispose();
            }
        });
    }
    public void resetbuttonAction(){
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TutorialBoardController tutorialBoardController = (TutorialBoardController) board;
                tutorialBoardController.resetTutorial();
            }
        });
    }
    public void tutorialFinished(){
        this.tutorialEndFrame = new JFrame();
        tutorialEndFrame.setSize(650, 300);
        tutorialEndFrame.setLayout(null);
        tutorialPageFrame.setLocationRelativeTo(null);

        this.finishButton = new JButton("확인");
        finishButton.setBounds(250, 200,100, 50);

        this.finishLabel = new JLabel("축하합니다!\n 튜토리얼 과정을 수료하셨습니다!");
        finishLabel.setFont(new Font("SensSerif", Font.BOLD, 20));
        finishLabel.setBounds(80, 10, 450, 200);
        tutorialPageFrame.dispose();

        tutorialEndFrame.setVisible(true);
        tutorialEndFrame.add(finishButton);
        tutorialEndFrame.add(finishLabel);
        finishbuttonAction();

    }
    public boolean isTutorialEndFrame(){
        return tutorialEndFrame.isVisible();
    }
    public boolean isTutorialPageFrame(){
        return tutorialPageFrame.isVisible();
    }
    public void removeTutorialPageFrame(){
        tutorialPageFrame.dispose();
    }

}
