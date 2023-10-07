package kr.ac.jbnu.se.tetris;


import kr.ac.jbnu.se.tetris.service.RankingService;
import kr.ac.jbnu.se.tetris.views.Page;
import kr.ac.jbnu.se.tetris.views.TetrisFrame;
import kr.ac.jbnu.se.tetris.views.TetrisFrameForTwo;

 public class TetrisStartGame {
    public
    static void main(String[] args) {
        RankingService service= new RankingService();
        service.getHighRank();
        //TetrisFrame game = new TetrisFrame();
        TetrisFrame game= new TetrisFrameForTwo();

        game.setLocationRelativeTo(null);
        game.init();
        new Page();


    }
}
