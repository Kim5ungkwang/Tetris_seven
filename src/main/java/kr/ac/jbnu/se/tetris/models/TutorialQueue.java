package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.BrickQueueManager;

public class TutorialQueue extends BrickQueueManager {
    public TutorialQueue() {
        brickQueue = new ShapeData.Tetrominoes[10];
        brickQueueIndex = 0;
        setBrickQueue();
    }

    @Override
    public ShapeData.Tetrominoes getNewShape(){
        if(brickQueueIndex == 7)
            return brickQueue[brickQueueIndex];
        return brickQueue[brickQueueIndex++];
    }

    public void setBrickQueue(){
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
    }
}
