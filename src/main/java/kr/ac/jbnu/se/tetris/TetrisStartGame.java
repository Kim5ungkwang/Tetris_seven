package kr.ac.jbnu.se.tetris;


import kr.ac.jbnu.se.tetris.controllers.pages.MainPageController;

import java.io.IOException;

public class TetrisStartGame {
    public static void main(String[] args) throws IOException {
        //TetrisFrameController game = new TetrisFrameController();
        //game.setLocationRelativeTo(null);
        //game.init();
        MainPageController mainPage = new MainPageController();
        mainPage.setLocationRelativeTo(null);
        //new Page();
    }
}
