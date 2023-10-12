package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.Piece;
import kr.ac.jbnu.se.tetris.models.TutorialQueue;
import lombok.Getter;

public class TutorialPieaceController extends PieceController {
    @Getter
    TutorialBoardController tutorialBoardController;

    /**
     * PieceController 생성자
     *
     * @param boardController 게임이 진행되는 boardController
     */
    public TutorialPieaceController(TutorialBoardController boardController) {
        tutorialBoardController = boardController;
        this.boardController = tutorialBoardController;
        this.currentPiece = new Piece();
        this.ghostPiece = new GhostPiece(this);
        this.brickQueueManager = new TutorialQueue(this);
        this.holdPiece = new Piece();
        this.isFallingFinished = false;
        this.setNextBlockPanel();


    }
    public int getRotateIndex(){
        return currentPiece.getRotateIndex();
    }
    @Override
    public boolean getIsFallingFinished() {
        return TutorialPieaceController.isFallingFinished;
    }
    @Override
    public void pieceDropped(){
        tutorialBoardController.pieceDropped(currentPiece);
        tutorialBoardController.pieceFixedCount++;
        System.out.println("PieceFixedCount: " + tutorialBoardController.getPieceFixedCount());
    }
    /*@Override
    public void dropDown(){
        if(getIsInfinity())
            return;
        int newY = currentPiece.getCurrentY();
        while (newY > 0){
            if (!tryMove(currentPiece, currentPiece.getCurrentX(), newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }*/
}

