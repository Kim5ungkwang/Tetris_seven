package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.controllers.pages.TimeAttackPage;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import lombok.Getter;

public class TimeAttackBoardController extends BoardController{
    @Getter
    private ReverseCountTimer reverseCountTimer;
    @Getter
    private TimeAttackPage timeAttackPage;
    public TimeAttackBoardController(TimeAttackPage parent, KeyInput input){
        super(parent, input);
        timeAttackPage = parent;
        playerPage = parent;
        reverseCountTimer = new ReverseCountTimer(this);
    }

    @Override
    public void start(){
        if (isPaused()) return; // 일시정지 상태에선 게임을 시작할 수 없다.
        isStarted = true;
        pieceController.setIsFallingFinished(false);
        numLinesRemoved = 0;
        clearBoard();
        pieceController.setNextBlockPanel();
        pieceController.newPiece();
        timer.start();
        reverseCountTimer.timerStart();
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
            reverseCountTimer.addTimeLimit(numFullLines);
            numLinesRemoved += numFullLines;
            timeAttackPage.getRemovedLine().setText(String.valueOf(numLinesRemoved));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            repaint();
        }
    }

    public void gameClear(){
        pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
        reverseCountTimer.stop();
        timer.stop();
        getBoardModel().setIsStarted(false);
        repaint();
        timeAttackPage.gameClear(String.valueOf(numLinesRemoved));
    }
    @Override
    public void pause() {
        if (!isStarted) return;

        isPaused = !isPaused;

        if (isPaused) {
            timer.stop();
            reverseCountTimer.timerPause();
        } else {
            timer.start();
            reverseCountTimer.timerStart();
        }
        repaint();
    }
}
