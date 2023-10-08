package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.controllers.NextBlockPanelController;

import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class PlayerPageController extends JPanel{
    static final public int PLAYERPAGE_WIDTH = 400;
    static final public int PLAYERPAGE_HEIGTH = 700;
    @Getter
    private JLabel statusBar;
    @Getter
    private BoardController board;
    private static final int BOARD_SIZE_WIDTH = 250;
    private static final int BOARD_SIZE_HEIGHT = 500;
    @Getter
    private final NextBlockPanelController nextBlockPanelController;

    public PlayerPageController(Member member, KeyInput keyInput){
        this.statusBar = new JLabel(" 0");

        this.board = new TutorialController(this, keyInput);
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
}
