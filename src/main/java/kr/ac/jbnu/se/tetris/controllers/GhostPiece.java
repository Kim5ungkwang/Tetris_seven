package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.Coordinates;
import kr.ac.jbnu.se.tetris.models.Piece;
import kr.ac.jbnu.se.tetris.views.TetrisBoard;

/**
 *바닥에 떨어질 위치를 미리 표시해 주는 GhostPiece를 관리하는 클래스이다.
 */
public class GhostPiece{
    private PieceController pieceController;
    private Piece currentGhostPiece;    //고스트피스
    private int currentGhostPieceY;
    private int currentGhostPieceX;
    /**
     * BoardController를 매개변수로 한다.
     */
    public GhostPiece(PieceController pieceController){
        this.pieceController = pieceController;
        this.currentGhostPiece = pieceController.getCurrentPiece();
        this.currentGhostPieceY = pieceController.getCurrentPiece().getCurrentY();
    }
    /**
     * GhostPieced의 x값과 y값을 Current Piece와 같게 업데이트해주는 메서드
     */
    public void updateGhostPiece(){
        this.currentGhostPiece = pieceController.getCurrentPiece();
        this.currentGhostPieceY = pieceController.getCurrentPiece().getCurrentY();  //piece의 y값을 가져옴
        this.currentGhostPieceX =  pieceController.getCurrentPiece().getCurrentX(); //piece의 X값을 가져옴
        makeGhostBrick();
    }
    /**
     * GhostPiece가 떨어질 위치에 다른 블록이 있는지 체크하는 메서드이다.
     */
    private boolean makeGhostBrickHelper(Piece newGhostPiece, int newX, int newY){
        for(int i = 0; i < 4; ++i){
            int y = newY - newGhostPiece.getCoordinates().y(i);
            int x = newX + newGhostPiece.getCoordinates().x(i);
            if(y < 0 || y >= BoardModel.getBoardHeight())
                return false;

            if(this.pieceController.shapeAt(x, y) != ShapeData.Tetrominoes.NoShape)
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

    public Piece getCurrentGhostPiece(){
        return currentGhostPiece;
    }
    public int getCurrentGhostPieceY(){
        return currentGhostPieceY;
    }
}
