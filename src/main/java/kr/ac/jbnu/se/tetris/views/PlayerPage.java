package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class PlayerPage extends JPanel {

    Logger logger=Logger.getLogger(PlayerPage.class.getName());
    private JLabel statusBar;
    private TetrisBoard board;

    public PlayerPage(Member member,KeyInput keyInput) {
        logger.info("playerPage Create");
        statusBar = new JLabel(" 0");
        //KeyInput p1Key = new KeyInput('w','s','d','a',(char)(KeyEvent.VK_SPACE),'p','h');
        board = new TetrisBoard(this,keyInput);
    }

    public void init() {
        logger.info("playerPage init");
        setLayout(new BorderLayout());
        add(statusBar, BorderLayout.SOUTH);
        add(board, BorderLayout.CENTER);
        board.start();
        setSize(300, 600);
        setPreferredSize(new Dimension(300, 600));
    }

    JLabel getStatusBar() {
        return statusBar;
    }
}
