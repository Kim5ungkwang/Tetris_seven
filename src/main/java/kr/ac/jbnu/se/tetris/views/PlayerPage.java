package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class PlayerPage extends JPanel {

    Logger logger=Logger.getLogger(PlayerPage.class.getName());
    static final public int PLAYERPAGE_WIDTH = 400;
    static final public int PLAYERPAGE_HEIGTH = 600;
    private JLabel statusBar;
    @Getter
    private TetrisBoard board;
    @Getter
    private NextBlockPanel nextBlockPanel;

    public PlayerPage(Member member,KeyInput keyInput) {
        logger.info("playerPage Create");
        statusBar = new JLabel(" 0");
        //KeyInput p1Key = new KeyInput('w','s','d','a',(char)(KeyEvent.VK_SPACE),'p','h');
        nextBlockPanel = new NextBlockPanel(this);
        board = new TetrisBoard(this,keyInput);
    }

    public void init() {
        logger.info("playerPage init");

        setLayout(new BorderLayout());
        add(statusBar, BorderLayout.SOUTH);
        add(board, BorderLayout.CENTER);
        add(nextBlockPanel, BorderLayout.WEST);
        setPreferredSize(new Dimension(PLAYERPAGE_WIDTH, PLAYERPAGE_HEIGTH));

        board.init();
        board.start();

        nextBlockPanel.init();
    }

    JLabel getStatusBar() {
        return statusBar;
    }
}
