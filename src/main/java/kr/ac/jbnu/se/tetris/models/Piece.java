package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;

import java.awt.*;

/**
 * 피스를 관리하는 클래스
 */
public class Piece implements Cloneable{
    private Coordinates coordinates;
    private ShapeData.Tetrominoes pieceShape;

    private int currentX = 0;   //piece의 중심을 기준으로 x값이 board의 어디에 위치하는지
    private int currentY = 0;   //piece의 중심을 기준으로 y값이 board의 어디에 위치하는지

    private int rotateIndex = 0;    //회전 인덱스 0>1은 오른쪽 회전 1>0은 왼쪽 회전

    /**
     * Coordinates 의 객체를 가치며
     * 생성자 호출 시 PieceShape를 Noshape로 초기화 한다.
     */
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

    /**
     * 해당 객체의 currentPiece의 Tetrominoes shape 반환
     * @return Tetrominoes
     */

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


    /**
     * 회전 인덱스를 1증가
     */
    public void plusRotateIndex(){
        this.rotateIndex = (this.rotateIndex + 1) % 4;
    }

    /**
     * 회전 인덱스를 1감소
     */
    public void minusRotateIndex(){
        this.rotateIndex = (3 + this.rotateIndex) % 4;
    }

    @Override
    public Piece clone() throws CloneNotSupportedException{
        return (Piece) super.clone();
    }

}

