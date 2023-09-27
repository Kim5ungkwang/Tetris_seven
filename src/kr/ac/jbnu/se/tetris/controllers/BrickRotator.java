package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.Shape;

public class BrickRotator {

    //인스턴스화 방지
    private BrickRotator(){
    };

    //블록을 왼쪽으로 회전시키는 메서드
    public static void rotateLeft(BoardController boardController,Shape currentPiece) {
        boolean isMoved;    //회전이 성공했는지 여부를 저장하는 변수
        int currentX = boardController.getCurrentX();   //회전 직전 위치
        int currentY = boardController.getCurrentY();   //회전 직전 위치
        if(currentPiece.getPieceShape() == Shape.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            boardController.tryMove(currentPiece.rotateLeft(), currentX, currentY);
        }
        else if(currentPiece.getPieceShape() == Shape.Tetrominoes.LineShape) {   //I블록인 경우 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.IShapeSrsKik[currentPiece.getRotateIndex() + 4][i][0];    //offset에 따라 x좌표 수정
                int moveY = Shape.IShapeSrsKik[currentPiece.getRotateIndex() + 4][i][1];    //offset에 따라 y좌표 수정
                isMoved = boardController.tryMove(currentPiece.rotateLeft(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.minusRotateIndex();    //회전에 성공했다면 회전 인덱스를 -1
                    break;
                }
            }
        }

        else{   //나머지 Tetromino에 대해선 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.srsKick[currentPiece.getRotateIndex() + 4][i][0];     //offset에 따라 x좌표 수정
                int moveY = Shape.srsKick[currentPiece.getRotateIndex() + 4][i][1];     //offset에 따라 y좌표 수정
                isMoved = boardController.tryMove(currentPiece.rotateLeft(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.minusRotateIndex();    //회전에 성공했다면 회전 인덱스 +1
                    break;
                }
            }
        }
    }

    public static void rotateRight(BoardController boardController,Shape currentPiece) {
        boolean isMoved;    //회전이 성공했는지 여부를 저장하는 변수
        int currentX = boardController.getCurrentX();   //회전 직전 위치
        int currentY = boardController.getCurrentY();   //회전 직전 위치
        if(currentPiece.getPieceShape() == Shape.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            boardController.tryMove(currentPiece.rotateRight(), currentX, currentY);    //회전에 성공했다면 회전 인덱스 +1
        }

        else if(currentPiece.getPieceShape() == Shape.Tetrominoes.LineShape) {   //I블록인 경우 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.IShapeSrsKik[currentPiece.getRotateIndex()][i][0];    //offset에 따라 x좌표 수정
                int moveY = Shape.IShapeSrsKik[currentPiece.getRotateIndex()][i][1];    //offset에 따라 y좌표 수정
                isMoved = boardController.tryMove(currentPiece.rotateRight(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.plusRotateIndex(); //회전에 성공했다면 회전 인덱스 +1
                    break;
                }
            }
        }

        else{   //나머지 Tetromino에 대해선 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.srsKick[currentPiece.getRotateIndex()][i][0];
                int moveY = Shape.srsKick[currentPiece.getRotateIndex()][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateRight(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.plusRotateIndex(); //회전에 성공했다면 회전 인덱스 +1
                    break;
                }
            }
        }
    }
}
