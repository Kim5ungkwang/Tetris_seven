package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.Shape;
import kr.ac.jbnu.se.tetris.views.TetrisBoard;

/**
 *바닥에 떨어질 위치를 미리 표시해 주는 GhostPiece를 관리하는 클래스이다.
 */
public class GhostPiece{
    private BoardController boardController;
    private Shape currentGhostPiece;    //고스트피스
    private int currentGhostPieceY;
    private int currentGhostPieceX;
    /**
     * BoardController를 매개변수로 한다.
     */
    public GhostPiece(BoardController boardController){
        this.boardController = boardController;
        this.currentGhostPiece = boardController.getCurrentPiece();
        this.currentGhostPieceY = boardController.getCurrentY();
    }
    /**
     * GhostPiece를 업데이트한다
     * 업데이트하는 시점은 BoardController의 currentPiece가
     * 움직이는 것을 완료한 시점이다.
     */
    public void updateGhostPiece(){
        this.currentGhostPiece = boardController.getCurrentPiece();
        this.currentGhostPieceY = boardController.getCurrentY();
        this.currentGhostPieceX = boardController.getCurrentX();
        makeGhostBrick();
    }
    /**
     * GhostPiece가 떨어질 위치에 다른 블록이 있는지 체크하는 메서드이다.
     */
    private boolean makeGhostBrickHelper(Shape newGhostPiece, int newX, int newY){
        for(int i = 0; i < 4; ++i){
            int y = newY - newGhostPiece.y(i);
            int x = newX + newGhostPiece.x(i);
            if(y < 0 || y >= TetrisBoard.BOARD_HEIGHT)
                return false;

            if(this.boardController.shapeAt(x, y) != Shape.Tetrominoes.NoShape)
                return false;
        }
        currentGhostPiece = newGhostPiece;
        currentGhostPieceY = newY;
        return true;
    }
    /**
     * GhostPiece를 Y축으로 최대한 떨어뜨리는 메서드
     */
    public void makeGhostBrick() {
        int newY = currentGhostPieceY;
        while (newY > 0) {
            if (!makeGhostBrickHelper(currentGhostPiece, currentGhostPieceX, newY - 1)) {
                break;
            }
            --newY;
        }
    }

    public Shape getCurrentGhostPiece(){
        return currentGhostPiece;
    }
    public int getCurrentGhostPieceY(){
        return currentGhostPieceY;
    }
}
