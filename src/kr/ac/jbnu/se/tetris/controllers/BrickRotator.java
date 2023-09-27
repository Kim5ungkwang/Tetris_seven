package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.Shape;

public class BrickRotator {

    private BrickRotator(){
    };

    public static void rotateLeft(BoardController boardController,Shape currentPiece) {
        boolean isMoved;
        int currentX = boardController.getCurrentX();
        int currentY = boardController.getCurrentY();
        if(currentPiece.getPieceShape() == Shape.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            boardController.tryMove(currentPiece.rotateLeft(), currentX, currentY);
        }
        else if(currentPiece.getPieceShape() == Shape.Tetrominoes.LineShape) {   //I블록인 경우 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.IShapeSrsKik[currentPiece.getRotateIndex() + 4][i][0];
                int moveY = Shape.IShapeSrsKik[currentPiece.getRotateIndex() + 4][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateLeft(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.minusRotateIndex();
                    break;
                }
            }
        }

        else{   //나머지 Tetromino에 대해선 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.srsKick[currentPiece.getRotateIndex() + 4][i][0];
                int moveY = Shape.srsKick[currentPiece.getRotateIndex() + 4][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateLeft(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.minusRotateIndex();
                    break;
                }
            }
        }
    }

    public static void rotateRight(BoardController boardController,Shape currentPiece) {
        boolean isMoved;
        int currentX = boardController.getCurrentX();
        int currentY = boardController.getCurrentY();
        if(currentPiece.getPieceShape() == Shape.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            boardController.tryMove(currentPiece.rotateRight(), currentX, currentY);
        }

        else if(currentPiece.getPieceShape() == Shape.Tetrominoes.LineShape) {   //I블록인 경우 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.IShapeSrsKik[currentPiece.getRotateIndex()][i][0];
                int moveY = Shape.IShapeSrsKik[currentPiece.getRotateIndex()][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateRight(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.plusRotateIndex();
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
                    currentPiece.plusRotateIndex();
                    break;
                }
            }
        }
    }
}
