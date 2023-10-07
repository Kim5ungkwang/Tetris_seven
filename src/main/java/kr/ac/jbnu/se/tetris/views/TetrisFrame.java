package kr.ac.jbnu.se.tetris.views;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;
import kr.ac.jbnu.se.tetris.controllers.PlayerPageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class TetrisFrame extends JFrame {
    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 700;
    public TetrisFrame() {
    }

    public void init() {

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        KeyInput p1Key = new KeyInput('w','s','d','a',(char)(KeyEvent.VK_SPACE),'p','h');
        PlayerPageController playerPage = new PlayerPageController(new Member(), p1Key);
        add(playerPage);

        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

}