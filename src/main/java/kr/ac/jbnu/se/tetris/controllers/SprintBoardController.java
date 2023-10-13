package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.ShapeData;
import kr.ac.jbnu.se.tetris.views.pages.SprintPage;
import kr.ac.jbnu.se.tetris.models.BoardModel;

import java.util.Random;

/**
 * 스프린트 게임 모드 보드 컨트롤러 클래스
 */
public class SprintBoardController extends BoardController{
    protected final int gameClearPoint = 1; //게임을 종료하는 조건 defualt 40을 넘기는 스프린트 모드
    protected final SprintPage sprintPage;
    /**
     * 보드 컨트롤러 생성자
     * @param sprintPage 보드가 그려질 페이지
     */
    public SprintBoardController(SprintPage sprintPage, Random rand){
        super(sprintPage, rand);
        this.sprintPage = sprintPage;
        playerPage = sprintPage;
}

    /**
     * 줄을 지우는 메서드
     */
    @Override
    protected void removeFullLines(){
        int numFullLines = 0;

        for (int i = BoardModel.getBoardHeight() - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardModel.getBoardWidth(); ++j) {
                if (shapeAt(j, i) == ShapeData.Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardModel.getBoardHeight() - 1; ++k) {
                    for (int j = 0; j < BoardModel.getBoardWidth(); ++j)
                        boardModel.setboard((k * BoardModel.getBoardWidth()) + j, shapeAt(j, k + 1));
                }
            }
        }

        if (numFullLines > 0) {
            new SoundEffectPlayer().start();        //블럭 사라짐 효과음
            numLinesRemoved += numFullLines;
            sprintPage.getRemovedLine().setText(String.valueOf(numLinesRemoved) + " / " + String.valueOf(gameClearPoint));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            repaint();
            if(numLinesRemoved >= gameClearPoint)
                gameClear();    // 지정해둔 값보다 많은 점수를 얻으면 게임을 종료한다.
        }
    }


    /**
     * 게임이 종료될때 호출하는 메서드
     */
    public void gameClear(){
        pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
        gameTimerController.stop();
        timer.stop();
        getBoardModel().setIsStarted(false);
        repaint();
        sprintPage.gameClear(gameTimerController.printGameTime());
    }

    /**
     * 게임이 시작될때 불러오는 메서드
     */
    @Override
    public void start(){
        super.start();
        sprintPage.getRemovedLine().setText(String.valueOf(numLinesRemoved) + " / " + String.valueOf(gameClearPoint));  // 점수판 초기화
    }

    public int getGameTimeSec(){
        return gameTimerController.getCurrentTimeSec();
    }




}
