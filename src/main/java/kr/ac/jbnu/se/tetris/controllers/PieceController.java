package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.BrickRotator;
import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.*;

import java.awt.*;

/**
 * 피스의 움직임과 관련된 클래스
 *
 */

public class PieceController {
    BoardController boardController;
    private Piece currentPiece;
    private GhostPiece ghostPiece;
    private BrickQueueManager brickQueueManager;
    private boolean isFallingFinished = false;
    private boolean isInfinity;


    public PieceController(BoardController boardController){
        this.boardController = boardController;
        this.currentPiece = new Piece();
        this.ghostPiece = new GhostPiece(this);
        this.brickQueueManager = new BrickQueueManager();
    }

    ///////////////////////////////////////////////////////////////////

    public BoardController getBoardControllerRe(){
        return boardController;
    }

    public Piece getCurrentPiece(){
        return currentPiece;
    }

    public GhostPiece getGhostPiece(){
        return ghostPiece;
    }

    public int getBoardWidth(){
        return BoardModel.getBoardWidth();
    }

    public int getBoardHeight(){
        return BoardModel.getBoardHeight();
    }

    public boolean getIsFallingFinished(){
        return isFallingFinished;
    }

    public boolean getIsInfinity(){
        return isInfinity;
    }

    ////////////////////////////////////////////////////////////////////

    public void setIsFallingFinished(boolean isFallingFinished){
        this.isFallingFinished = isFallingFinished;
    }

    public void setIsInfinity(boolean isInfinity){
        this.isInfinity = isInfinity;
    }

    ////////////////////////////////////////////////////////////////////

    public void oneLineDown()
    {
        if(isInfinity && !tryMove(currentPiece, currentPiece.getCurrentX(), currentPiece.getCurrentY() - 1)) {
            pieceDropped();
        }
        else if (!tryMove(currentPiece, currentPiece.getCurrentX(), currentPiece.getCurrentY() - 1)) {
            isInfinity = true;
            return;
        }

        isInfinity = false;
    }

    public void dropDown()
    {
        if(isInfinity)
            return;
        int newY = currentPiece.getCurrentY();
        while (newY > 0){
            if (!tryMove(currentPiece, currentPiece.getCurrentX(), newY - 1))
                break;
            --newY;
        }
    }

    public boolean tryMove(Piece newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.getCoordinates().x(i);
            int y = newY - newPiece.getCoordinates().y(i);
            if (x < 0 || x >= BoardModel.getBoardWidth() || y < 0 || y >= BoardModel.getBoardHeight())
                return false;
            if (boardController.shapeAt(x, y) != ShapeData.Tetrominoes.NoShape)
                return false;
        }
        currentPiece = newPiece;
        currentPiece.setCurrentX(newX);
        currentPiece.setCurrentY(newY);
        ghostPiece.updateGhostPiece();
        boardController.paintUpdate();
        return true;
    }

    private void pieceDropped()
    {
        boardController.pieceDropped(currentPiece);
    }

    public void moveLeft() {
        tryMove(currentPiece, currentPiece.getCurrentX() - 1, currentPiece.getCurrentY());
    }

    //블록을 오른쪽으로 1칸 움직이는 메서드
    public void moveRight() {
        tryMove(currentPiece, currentPiece.getCurrentX() + 1, currentPiece.getCurrentY());
    }

    //SRS를 적용한 블록 왼쪽 회전 메서드
    public void rotateLeft(){
        BrickRotator.rotateLeft(this);
        System.out.println(currentPiece.getRotateIndex());
    }

    //SRS를 적용한 블록 오른쪽 회전 메서드
    public void rotateRight(){
        BrickRotator.rotateRight(this);
        System.out.println(currentPiece.getRotateIndex());
    }

    public Piece rotateLeftHelper() {
        if (currentPiece.getPieceShape() == ShapeData.Tetrominoes.SquareShape)
            return currentPiece;

        Piece result = new Piece();
        result.setRotateIndex(currentPiece.getRotateIndex());
        result.setPieceShape(currentPiece.getPieceShape());

        for (int i = 0; i < 4; ++i) {
            result.getCoordinates().setX(i, currentPiece.getCoordinates().y(i));
            result.getCoordinates().setY(i, -currentPiece.getCoordinates().x(i));
        }
        return result;
    }


    public Piece rotateRightHelper() {
        if (currentPiece.getPieceShape() == ShapeData.Tetrominoes.SquareShape)
            return currentPiece;

        Piece result = new Piece();
        result.setRotateIndex(currentPiece.getRotateIndex());

        for (int i = 0; i < 4; ++i) {
            result.getCoordinates().setX(i, -currentPiece.getCoordinates().y(i));
            result.getCoordinates().setY(i, currentPiece.getCoordinates().x(i));
        }
        return result;
    }

    public ShapeData.Tetrominoes shapeAt(int x, int y){
        return boardController.shapeAt(x, y);
    }

    public void newPiece(){

        currentPiece.setPieceShape(brickQueueManager.getNewShape());
        currentPiece.setCurrentX(BoardModel.getBoardWidth() / 2 + 1);
        currentPiece.setCurrentY(BoardModel.getBoardHeight() - 1 + currentPiece.getCoordinates().minY());
        currentPiece.setRotateIndex(0);

        if (!tryMove(currentPiece, currentPiece.getCurrentX(), currentPiece.getCurrentY())) {
            currentPiece.setPieceShape(ShapeData.Tetrominoes.NoShape);
            boardController.getTimer().stop();
            boardController.getBoardModel().setIsStarted(false);
            boardController.gameOver();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////




}
