package kr.ac.jbnu.se.tetris;


import kr.ac.jbnu.se.tetris.controllers.TetrisFrameController;

public class TetrisStartGame {
    public
    static void main(String[] args) {
        TetrisFrameController game = new TetrisFrameController();
        game.setLocationRelativeTo(null);
        game.init();
        //new Page();
    }
}
