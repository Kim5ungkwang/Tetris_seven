package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.controllers.pages.SprintPage;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.KeyInput;

public class SprintBoardController extends BoardController{
    protected final int gameClearPoint;
    protected final SprintPage sprintPage;
    public SprintBoardController(SprintPage sprintPage, KeyInput keyInput){
        super(sprintPage, keyInput);
        this.sprintPage = sprintPage;
        playerPage = sprintPage;
        gameClearPoint = 40;
    }


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
            //playerPage.getStatusBar().setText(String.valueOf(numLinesRemoved));
            sprintPage.getRemovedLine().setText(String.valueOf(numLinesRemoved) + " / " + String.valueOf(gameClearPoint));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            repaint();
            if(numLinesRemoved >= gameClearPoint)
                gameClear();
        }
    }



    public void gameClear(){
        pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
        gameTimerController.stop();
        timer.stop();
        getBoardModel().setIsStarted(false);
        repaint();
        sprintPage.gameClear(gameTimerController.printGameTime());
    }

    @Override
    public void start(){
        super.start();
        sprintPage.getRemovedLine().setText(String.valueOf(numLinesRemoved) + " / " + String.valueOf(gameClearPoint));
    }


}
