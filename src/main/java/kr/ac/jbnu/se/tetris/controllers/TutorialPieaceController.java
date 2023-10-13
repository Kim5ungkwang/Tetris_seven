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
    public void pieceDropped(){
        //BREAK
        tutorialBoardController.pieceDropped(currentPiece);
    }

    public void resetBrickQueue(){
        TutorialQueue tutorialQueue = (TutorialQueue) brickQueueManager;    //타입 캐스팅
        tutorialQueue.resetBrickQueueIndex();
    }
}

