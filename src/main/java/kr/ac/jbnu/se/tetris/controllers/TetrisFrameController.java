package kr.ac.jbnu.se.tetris.controllers;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;
import kr.ac.jbnu.se.tetris.controllers.PlayerPageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class TetrisFrameController extends JFrame {
    public TetrisFrameController() {
    }

    public void init() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 800));

        KeyInput p1Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player1key.json");
        PlayerPageController playerPage = new PlayerPageController(new Member(), p1Key);
        add(playerPage,BorderLayout.CENTER);

        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

}