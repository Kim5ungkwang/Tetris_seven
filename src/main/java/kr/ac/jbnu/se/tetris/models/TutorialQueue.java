package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.controllers.TutorialPieaceController;

import java.util.Random;

public class TutorialQueue extends BrickQueueManager {
    private final TutorialPieaceController controller;
    public TutorialQueue(TutorialPieaceController tutorialPieaceController) {
        super(new Random(1));
        controller = tutorialPieaceController;
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

    public void resetBrickQueueIndex(){
        brickQueueIndex = 0;
    }
}
