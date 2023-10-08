package kr.ac.jbnu.se.tetris.controllers.pages;

import kr.ac.jbnu.se.tetris.controllers.BoardController;
import kr.ac.jbnu.se.tetris.controllers.NextBlockPanelController;
import kr.ac.jbnu.se.tetris.controllers.PlayerPage;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;

import javax.swing.*;
import java.awt.*;

public class SprintPage extends PlayerPage {
    JPanel backgroundPanel;
    public SprintPage(Member member, KeyInput keyInput){
        super();
        this.board = new BoardController(this, keyInput);
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        add(board, BorderLayout.CENTER);
        add(nextBlockPanelController, BorderLayout.WEST);
        setSize(new Dimension(1280, 720));

        backgroundPanel = new JPanel(){ //배경 패널 설정
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getGameBackgroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        add(backgroundPanel);
    }
}
