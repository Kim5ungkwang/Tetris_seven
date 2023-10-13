package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.controllers.pages.TutorialPage;
import kr.ac.jbnu.se.tetris.models.KeyInput;

import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PlayerPage extends JPanel{
    static final protected int PLAYERPAGE_WIDTH = 400;
    static final protected int PLAYERPAGE_HEIGTH = 700;
    @Getter
    protected JLabel statusBar;
    @Getter
    protected BoardController board;
    protected static final int BOARD_SIZE_WIDTH = 250;
    protected static final int BOARD_SIZE_HEIGHT = 500;
    @Getter
    protected NextBlockPanelController nextBlockPanelController;
    @Getter
    protected JLabel gameTimer;
    Random rand = new Random(1);

    /**
     * 게임이 이루어지는 화면을 구성하는 패널을 관리하는 클래스
     * @param member 어떤 플레이어가 게임하는지
     * @param keyInput 키 입력을 어떻게 하는지
     */
    public PlayerPage(Member member, KeyInput keyInput){
        this.statusBar = new JLabel("0");   //사용안할예정
        this.gameTimer = new JLabel("00 : 00");

        Random rand = new Random(1);
        this.board = new BoardController( this, rand);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        setLayout(new BorderLayout());
        add(statusBar, BorderLayout.SOUTH);
        add(board, BorderLayout.CENTER);
        add(nextBlockPanelController, BorderLayout.WEST);
        setSize(new Dimension(PLAYERPAGE_WIDTH, PLAYERPAGE_HEIGTH));

        setVisible(true);

        //AdapterController adapterController = new AdapterController();
        //addKeyListener(adapterController);
        //adapterController.addList(new KeyInputController(keyInput,board));
        //setFocusable(true);
    }

    public PlayerPage(Member member, KeyInput keyInput, Random rand) {
        this.statusBar = new JLabel("0");   //사용안할예정
        this.gameTimer = new JLabel("00 : 00");

        this.board = new BoardController( this, rand);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        setLayout(new BorderLayout());
        add(board, BorderLayout.CENTER);
        add(nextBlockPanelController, BorderLayout.WEST);
        setSize(new Dimension(PLAYERPAGE_WIDTH, PLAYERPAGE_HEIGTH));

        setVisible(true);

        //AdapterController adapterController = new AdapterController();
        //addKeyListener(adapterController);
        //adapterController.addList(new KeyInputController(keyInput,board));
        //setFocusable(true);
    }

    public PlayerPage(){
        //상속용
    }

    public void raiseGameOverFrame(){
        // 구현해서 사용하시오
    }
}
