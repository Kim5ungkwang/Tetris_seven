package kr.ac.jbnu.se.tetris;


import kr.ac.jbnu.se.tetris.views.TetrisFrame;
import kr.ac.jbnu.se.tetris.views.TetrisFrameForTwo;

public class TetrisStartGame {
    public
    static void main(String[] args) {
        TetrisFrame game = new TetrisFrameForTwo();
        game.setLocationRelativeTo(null);
        game.init();
    }
}
