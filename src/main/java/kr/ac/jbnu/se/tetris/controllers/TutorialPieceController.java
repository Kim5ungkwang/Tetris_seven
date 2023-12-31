package kr.ac.jbnu.se.tetris.controllers;
import kr.ac.jbnu.se.tetris.models.Piece;
import kr.ac.jbnu.se.tetris.models.TutorialQueue;
import lombok.Getter;

@Getter
public class TutorialPieceController extends PieceController {
    TutorialBoardController tutorialBoardController;

    /**
     * PieceController 생성자
     *
     * @param boardController 게임이 진행되는 boardController
     */
    public TutorialPieceController(TutorialBoardController boardController) {
        tutorialBoardController = boardController;
        this.boardController = tutorialBoardController;
        this.currentPiece = new Piece();
        this.ghostPiece = new GhostPiece(this);
        this.brickQueueManager = new TutorialQueue(this);
        this.holdPiece = new Piece();
        isFallingFinished = false;
        this.setNextBlockPanel();
    }
    @Override
    public void pieceDropped(){
        //BREAK
        tutorialBoardController.pieceDropped(currentPiece);
    }

    //튜토리얼 블럭 순서 초기화
    public void resetBrickQueue(){
        TutorialQueue tutorialQueue = (TutorialQueue) brickQueueManager;    //타입 캐스팅
        tutorialQueue.resetBrickQueueIndex();
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

