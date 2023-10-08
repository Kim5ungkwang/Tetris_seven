package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;

import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

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

    public PlayerPage(Member member, KeyInput keyInput){
        this.statusBar = new JLabel(" 0");

        this.board = new TutorialBoardController(this, keyInput);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        setLayout(new BorderLayout());
        add(statusBar, BorderLayout.SOUTH);
        add(board, BorderLayout.CENTER);
        add(nextBlockPanelController, BorderLayout.WEST);
        setSize(new Dimension(PLAYERPAGE_WIDTH, PLAYERPAGE_HEIGTH));
    }

    public PlayerPage() {
        //상속용
    }
}
