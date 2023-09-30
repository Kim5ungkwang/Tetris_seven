package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;

import java.awt.*;

public class BoardModel {
    static private final int BOARD_WIDTH = 10;
    static private final int BOARD_HEIGHT = 22;
    private ShapeData.Tetrominoes[] board;

    private int timerDelay;
    private boolean isStarted;
    private boolean isPaused;

    private int numLinesRemoved;

    public BoardModel(){
        board = new ShapeData.Tetrominoes[BOARD_WIDTH * BOARD_HEIGHT];
    }

    //////////////////////////////////////////////////

    public int getTimerDelay(){
        return timerDelay;
    }

    public static int getBoardWidth(){
        return BOARD_WIDTH;
    }

    public static int getBoardHeight(){
        return BOARD_HEIGHT;
    }
    public ShapeData.Tetrominoes getBoard(int index){
        return board[index];
    }


    public int getNumLinesRemoved(){
        return numLinesRemoved;
    }

    public boolean getIsStarted(){
        return isStarted;
    }

    public boolean getIsPaused(){
        return isPaused;
    }

    ///////////////////////////////////////////////

    public void setTimerDelay(int timerDelay){
        this.timerDelay = timerDelay;
    }

    public void plusNumLinesRemoved(){
        numLinesRemoved++;
    }

    public void setIsStarted(boolean isStarted){
        this.isStarted = isStarted;
    }

    public void setIsPaused(boolean isPaused){
        this.isPaused = isPaused;
    }

    public void setboard(int index, ShapeData.Tetrominoes pieceShape){
        this.board[index] = pieceShape;
    }
}
