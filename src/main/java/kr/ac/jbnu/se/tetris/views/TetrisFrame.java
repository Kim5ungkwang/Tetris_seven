package kr.ac.jbnu.se.tetris.views;
import kr.ac.jbnu.se.tetris.controllers.AdapterController;
import kr.ac.jbnu.se.tetris.controllers.KeyInputController;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;

import javax.swing.*;
import java.awt.*;


public class TetrisFrame extends JFrame {
    static private final int TETRIS_FRAME_SIZE_WIDTH = 1280;
    static private final int TETRIS_FRAME_SIZE_HEIGHT = 720;

    public TetrisFrame() {
    }

    public void init() {

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(TETRIS_FRAME_SIZE_WIDTH, TETRIS_FRAME_SIZE_HEIGHT));

        KeyInput p1Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player1key.json");
        PlayerPage playerPage = new PlayerPage(new Member(), p1Key);
        add(playerPage);

        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        AdapterController adapterController = new AdapterController();
        addKeyListener(adapterController);
        adapterController.addList(new KeyInputController(p1Key,playerPage.board));
        setFocusable(true);
    }

}