package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.controllers.TutorialPieceController;

import java.util.Random;

public class TutorialQueue extends BrickQueueManager {

    public TutorialQueue(TutorialPieceController tutorialPieceController) {
        super(new Random(1));
        //final TutorialPieceController controller = tutorialPieceController;
        brickQueue = new ShapeData.Tetrominoes[11];
        brickQueueIndex = 0;
        brickQueueSize = 11;
        setBrickQueue();
    }
    @Override
    public ShapeData.Tetrominoes getNewShape(){
        this.brickQueueIndex %= brickQueueSize;
        return brickQueue[brickQueueIndex++];
    }

    // SRS를 수행하는 튜토리얼 블럭
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

    // 튜토리얼 블럭 단계 초기화
    public void resetBrickQueueIndex(){
        brickQueueIndex = 0;
    }
}
