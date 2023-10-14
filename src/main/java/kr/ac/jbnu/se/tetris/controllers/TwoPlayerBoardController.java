package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.ShapeData;
import kr.ac.jbnu.se.tetris.models.BoardModel;

import java.util.Random;

public class TwoPlayerBoardController extends  BoardController{
    int playerNum;
    public TwoPlayerBoardController(PlayerPage parent, Random rand, int playerNum){
        super(parent, rand);
        this.playerNum = playerNum;
    }

    @Override
    protected void removeFullLines() {
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
            numLinesRemoved += numFullLines;
            //playerPage.getStatusBar().setText(String.valueOf(numLinesRemoved));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            repaint();

            int numLinesRemovedCountDown = 10 - (numLinesRemoved % 10);
            updateNumLinesRemovedCountDown(numLinesRemovedCountDown);
            if(numLinesRemovedCountDown == 1){
                reduceRivalGameDelay();
            }
        }
    }

    /**
     * 타이머를 출력하는 메서드
     * 1번 플레이어가 타이머를 관리한다.
     * @param currentTimer 현재 시간
     */
    @Override
    public void printTimer(String currentTimer){
        if(playerNum == 1)
            playerPage.getGameTimer().setText(currentTimer);
    }

    /**
     * 라이벌의 게임 딜레이를 줄이는 메서드
     */
    public void reduceRivalGameDelay(){
        if(playerNum == 1) {
            playerPage.reducePlayer2GameDelay(80);  //80씩 줄인다
            return;
        }
        playerPage.reducePlayer1GameDelay(80);  //80씩 줄인다
    }

    /**
     * 자신의 게임 딜레이를 줄이는 메서드
     * @param reduceAmount 줄이는 양
     */
    public void reduceMyGameDelay(int reduceAmount){
        int newGameDelay = timer.getDelay() - reduceAmount;

        if(timer.getDelay() - reduceAmount < 0) return; //newGameDelay가 음수면 무시

        timer.setDelay(newGameDelay);
    }

    public void updateNumLinesRemovedCountDown(int NumLinesRemovedCount){
        if(playerNum == 1){
            playerPage.updatePlayer1NumLinesRemovedCount(NumLinesRemovedCount);
            return;
        }
        playerPage.updatePlayer2NumLinesRemovedCount(NumLinesRemovedCount);
    }

    @Override
    public void gameOver() {
        playerPage.getLocalTwoPlayPage().endGame(playerNum);
    }
}
