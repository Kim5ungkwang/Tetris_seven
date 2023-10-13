package kr.ac.jbnu.se.tetris.models;

import lombok.Getter;

/**
 * 보드의 모델 클래스
 */
public class BoardModel {
    static private final int BOARD_WIDTH = 10;  //board의 너비
    static private final int BOARD_HEIGHT = 22; //board의 높이
    ShapeData.Tetrominoes[] board;

    @Getter
    private int loopDelay;  //board에서 블럭이 떨어지는 시간
    private boolean isStarted;  //게임이 시작 되었는지
    private boolean isPaused;   //게임이 일시중지 되었는지
    @Getter
    private int numLinesRemoved;    //얼마 만큼의 블럭이 삭제되었는지

    /**
     * 생성자는 게임을 진행 할 보드의 크기를 지정한다.
     */
    public BoardModel(){
        board = new ShapeData.Tetrominoes[BOARD_WIDTH * BOARD_HEIGHT];
    }

    //////////////////////////////////////////////////

    public static int getBoardWidth(){
        return BOARD_WIDTH;
    }

    public static int getBoardHeight(){
        return BOARD_HEIGHT;
    }
    public ShapeData.Tetrominoes getBoard(int index){
        return board[index];
    }


    public boolean getIsStarted(){
        return isStarted;
    }

    public boolean getIsPaused(){
        return isPaused;
    }

    ///////////////////////////////////////////////

    public void setLoopDelay(int loopDelay){
        this.loopDelay = loopDelay;
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

    /**
     * 보드의 해당 위치에 Tetrominoes를 설정하는 메서드
     * @param index 보드의 index (x + y * BoardHeight)
     * @param pieceShape 해당 index에 들어갈 Tetrominoes 값
     */
    public void setboard(int index, ShapeData.Tetrominoes pieceShape){
        this.board[index] = pieceShape;
    }
}
