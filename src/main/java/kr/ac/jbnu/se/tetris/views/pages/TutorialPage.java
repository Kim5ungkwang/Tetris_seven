package kr.ac.jbnu.se.tetris.views.pages;

import kr.ac.jbnu.se.tetris.controllers.*;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;
import kr.ac.jbnu.se.tetris.views.PlayerPage;
import lombok.Getter;
import org.w3c.dom.css.Rect;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialPage extends PlayerPage {
    private final JPanel backgroundPanel;
    @Getter
    JLabel tutorialStep;
    private final JFrame tutorialPageFrame;
    JFrame tutorialEndFrame;
    private final JButton skipButton; // 튜토리얼 건너뛰는 버튼
    JButton finishButton;
    private final JButton resetButton;
    private final JLabel imageLabel;
    JLabel finishLabel;
    private final ImageIcon imageIcon;

    JPanel RectPanel;

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
        this.RectPanel = new JPanel();

        RectPanel.setOpaque(false);
        RectPanel.setBounds(900, 150, 350, 300);

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
        tutorialPageFrame.add(RectPanel);

        skipbuttonAction();
        resetbuttonAction();
        resetButton.setFocusable(false); // 버튼 선택 시 생기는 테두리 활성화


        backgroundPanel = new JPanel(){
            @Override
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

        AdapterController adapterController = new AdapterController();
        tutorialPageFrame.setFocusable(true);
        tutorialPageFrame.addKeyListener(adapterController);
        adapterController.addList(new KeyInputController(p1Key,board));

    }

    public void skipbuttonAction(){
        skipButton.addActionListener(e -> tutorialPageFrame.dispose());
    }
    public void finishbuttonAction(){
        finishButton.addActionListener(e -> {
            tutorialEndFrame.dispose();
            tutorialPageFrame.dispose();
        });
    }
    public void resetbuttonAction(){
        resetButton.addActionListener(e -> {
            TutorialBoardController tutorialBoardController = (TutorialBoardController) board;
            tutorialBoardController.resetTutorial();
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
}
