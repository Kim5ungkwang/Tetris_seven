package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;

import java.awt.*;

public class Piece {
    private Coordinates coordinates;
    private ShapeData.Tetrominoes pieceShape;

    private int currentX = 0;
    private int currentY = 0;

    private int rotateIndex;

    public Piece(){
        coordinates = new Coordinates();
        setPieceShape(ShapeData.Tetrominoes.NoShape); // 시작블록은 NoShape
    }





    //////////////////////////////////////////////////////////////////////////////////




    public int getCurrentX(){
        return currentX;
    }

    public int getCurrentY(){
        return currentY;
    }


    public int getRotateIndex(){
        return rotateIndex;
    }

    public ShapeData.Tetrominoes getPieceShape(){
        return pieceShape;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }




    ////////////////////////////////////////////



    public void setCurrentX(int currentX){
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY){
        this.currentY = currentY;
    }

    public void setPieceShape(ShapeData.Tetrominoes pieceShape) {
        coordinates.setPieceShape(pieceShape);
        this.pieceShape = pieceShape;
    }

    public void setRotateIndex(int rotateIndex){
        this.rotateIndex = rotateIndex;
    }





    ////////////////////////////////////////////////////////////////




    public void plusRotateIndex(){
        this.rotateIndex = (this.rotateIndex + 1) % 4;
    }

    //회전 인덱스를 1감소하는 메서드
    public void minusRotateIndex(){
        this.rotateIndex = (3 + this.rotateIndex) % 4;
    }

}
