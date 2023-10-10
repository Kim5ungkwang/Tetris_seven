package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.controllers.BoardController;
import kr.ac.jbnu.se.tetris.controllers.TutorialBoardController;
import kr.ac.jbnu.se.tetris.controllers.TutorialPieaceController;
import kr.ac.jbnu.se.tetris.models.BrickQueueManager;

public class TutorialQueue extends BrickQueueManager {
    TutorialPieaceController controller;
    public TutorialQueue() {
        brickQueue = new ShapeData.Tetrominoes[11];
        brickQueueIndex = 0;
        brickQueueSize = 11;
        setBrickQueue();
    }

    @Override
    public ShapeData.Tetrominoes getNewShape(){
        this.brickQueueIndex %= brickQueueSize;
        /*if(TutorialModel.getCurrentStepIndex() == 4){
            brickQueueIndex = 0;
            return brickQueue[0];
        }*/
            if(TutorialBoardController.getPieceFixedCount() == 1){
                return brickQueue[1];
            }else if(TutorialBoardController.getPieceFixedCount() < 1){
                BoardController.clearBoard();
                brickQueueIndex = 0;
                return brickQueue[0];
            }

        if(brickQueueIndex == 1){
            if(TutorialBoardController.getPieceFixedCount() == 2){
                return brickQueue[2];
            }
        }
        if(brickQueueIndex == 2){
            if(TutorialBoardController.getPieceFixedCount() == 3)
            return brickQueue[3];
        }
        if(brickQueueIndex == 3){
            if(TutorialBoardController.getPieceFixedCount() == 4){
                return brickQueue[4];
            }
        }
        if(brickQueueIndex == 4){
            if(TutorialBoardController.getPieceFixedCount() == 5){
                return brickQueue[5];
            }
        }
        if(brickQueueIndex == 5){
            if(TutorialBoardController.getPieceFixedCount() == 6){
                return brickQueue[6];
            }
        }
        if(brickQueueIndex == 6){
            if(TutorialBoardController.getPieceFixedCount() == 7){
                return brickQueue[3];
            }
        }
        return brickQueue[brickQueueIndex++];
    }

    public void setBrickQueue(){
        brickQueueIndex = 0;
        brickQueue[0] = ShapeData.Tetrominoes.MirroredLShape;
        brickQueue[1] = ShapeData.Tetrominoes.SShape;
        brickQueue[2] = ShapeData.Tetrominoes.SquareShape;
        brickQueue[3] = ShapeData.Tetrominoes.ZShape;
        brickQueue[4] = ShapeData.Tetrominoes.LShape;
        brickQueue[5] = ShapeData.Tetrominoes.MirroredLShape;
        brickQueue[6] = ShapeData.Tetrominoes.TShape;
        brickQueue[7] = ShapeData.Tetrominoes.TShape;
        brickQueue[8] = ShapeData.Tetrominoes.TShape;
        brickQueue[9] = ShapeData.Tetrominoes.TShape;
        brickQueue[10] = ShapeData.Tetrominoes.TShape;
    }
}
