package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.Piece;
import kr.ac.jbnu.se.tetris.models.TutorialQueue;

public class TutorialPieaceController extends PieceController {

    /**
     * PieceController 생성자
     *
     * @param boardController 게임이 진행되는 boardController
     */
    public TutorialPieaceController(BoardController boardController) {
        this.boardController = boardController;
        this.currentPiece = new Piece();
        this.ghostPiece = new GhostPiece(this);
        this.brickQueueManager = new TutorialQueue();
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
}

