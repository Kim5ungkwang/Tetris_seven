package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.controllers.TutorialBoardController;
import kr.ac.jbnu.se.tetris.controllers.TutorialPieaceController;
import kr.ac.jbnu.se.tetris.controllers.pages.TutorialPage;

public class TutorialQueue extends BrickQueueManager {
    TutorialPieaceController controller;
    public TutorialQueue(TutorialPieaceController tutorialPieaceController) {
        controller = tutorialPieaceController;
        brickQueue = new ShapeData.Tetrominoes[11];
        brickQueueIndex = 0;
        brickQueueSize = 11;
        setBrickQueue();
    }

    @Override
    public ShapeData.Tetrominoes getNewShape(){
        this.brickQueueIndex %= brickQueueSize;
        if(controller.getTutorialBoardController().getPieceFixedCount() == 0){
            return brickQueue[0];
        }
        if(controller.getTutorialBoardController().getPieceFixedCount() == 1){
            return brickQueue[1];
        } else if(controller.getTutorialBoardController().getPieceFixedCount() < 1){
            controller.getBoardController().clearBoard();
            controller.getTutorialBoardController().startTutorial();
            brickQueueIndex = 0;
            return brickQueue[0];
        }
        if(controller.getTutorialBoardController().getPieceFixedCount() == 2){
            return brickQueue[2];
        } else if(controller.getTutorialBoardController().getPieceFixedCount() < 2){
            controller.getBoardController().clearBoard();
            controller.getTutorialBoardController().startTutorial();
            brickQueueIndex = 0;
            return brickQueue[0];
        }
        if(controller.getTutorialBoardController().getPieceFixedCount() == 3){
            return brickQueue[3];
        } else if(controller.getTutorialBoardController().getPieceFixedCount() < 3){
            controller.getBoardController().clearBoard();
            controller.getTutorialBoardController().startTutorial();
            brickQueueIndex = 0;
            return brickQueue[0];
        }
        if(controller.getTutorialBoardController().getPieceFixedCount() == 4){
            return brickQueue[4];
        } else if(controller.getTutorialBoardController().getPieceFixedCount() < 4){
            controller.getBoardController().clearBoard();
            controller.getTutorialBoardController().startTutorial();
            brickQueueIndex = 0;
            return brickQueue[0];
        }
        if(controller.getTutorialBoardController().getPieceFixedCount() == 5){
            return brickQueue[5];
        } else if(controller.getTutorialBoardController().getPieceFixedCount() < 5){
            controller.getBoardController().clearBoard();
            controller.getTutorialBoardController().startTutorial();
            brickQueueIndex = 0;
            return brickQueue[0];
        }
        if(controller.getTutorialBoardController().getPieceFixedCount() == 6){
            return brickQueue[6];
        } else if(controller.getTutorialBoardController().getPieceFixedCount() < 6){
            controller.getBoardController().clearBoard();
            controller.getTutorialBoardController().startTutorial();
            brickQueueIndex = 0;
            return brickQueue[0];
        }
        if((controller.getTutorialBoardController().getPieceFixedCount() == 7) && (TutorialBoardController.numLinesRemoved != 3)){
            controller.getBoardController().clearBoard();
            controller.getTutorialBoardController().startTutorial();
            brickQueueIndex = 0;
            return brickQueue[0];
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
