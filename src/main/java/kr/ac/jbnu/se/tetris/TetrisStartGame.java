package kr.ac.jbnu.se.tetris;


import kr.ac.jbnu.se.tetris.views.Page;
import kr.ac.jbnu.se.tetris.views.TetrisFrame;
import kr.ac.jbnu.se.tetris.views.TetrisFrameForTwo;

public class TetrisStartGame {
    public
    static void main(String[] args) {
        TetrisFrame game = new TetrisFrame();
        game.setLocationRelativeTo(null);
        game.init();
        //new Page();
    }
}
