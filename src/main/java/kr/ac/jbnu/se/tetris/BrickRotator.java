package kr.ac.jbnu.se.tetris;

import kr.ac.jbnu.se.tetris.controllers.PieceController;
import kr.ac.jbnu.se.tetris.models.SRSData;
import kr.ac.jbnu.se.tetris.models.ShapeData;

/**
 *블록의 회전을 담당하는 유틸리티 클래스
 */
public class BrickRotator {

    /**
     *인스턴스화 하는 것을 허용하지 않는다.
     */
    private BrickRotator(){
    };

    /**
     *블록을 왼쪽으로 회전하는 메서드이다
     * SRS를 적용했다.
     */
    public static void rotateLeft(PieceController pieceController) {
        boolean isRotate = false;
        int currentX = pieceController.getCurrentPiece().getCurrentX();
        int currentY = pieceController.getCurrentPiece().getCurrentY();
        if(pieceController.getCurrentPiece().getPieceShape() == ShapeData.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            isRotate = pieceController.tryMove(pieceController.rotateLeftHelper(), currentX, currentY);
        }
        else if(pieceController.getCurrentPiece().getPieceShape() == ShapeData.Tetrominoes.LineShape) {   //I블록인 경우 SRS IShape의 offset적용
            for (int i = 0; i < 5; i++) {
                int moveX = SRSData.IShapeSrsKick[pieceController.getCurrentPiece().getRotateIndex() + 4][i][0];
                int moveY = SRSData.IShapeSrsKick[pieceController.getCurrentPiece().getRotateIndex() + 4][i][1];
                isRotate = pieceController.tryMove(pieceController.rotateLeftHelper(), currentX + moveX, currentY + moveY);
                if(isRotate) break;
            }
        }

        else{   //나머지 Tetromino에 대해서 SRS offset적용
            for (int i = 0; i < 5; i++) {
                int moveX = SRSData.srsKick[pieceController.getCurrentPiece().getRotateIndex() + 4][i][0];
                int moveY = SRSData.srsKick[pieceController.getCurrentPiece().getRotateIndex() + 4][i][1];
                isRotate = pieceController.tryMove(pieceController.rotateLeftHelper(), currentX + moveX, currentY + moveY);
                if(isRotate) break;
            }
        }
        if(isRotate){
            pieceController.getCurrentPiece().minusRotateIndex();
        };
    }

    /**
     *블록을 오른쪽으로 회전하는 메서드이다
     * SRS를 적용했다.
     */
    public static void rotateRight(PieceController pieceController) {
        boolean isRotate = false;
        int currentX = pieceController.getCurrentPiece().getCurrentX();
        int currentY = pieceController.getCurrentPiece().getCurrentY();
        if(pieceController.getCurrentPiece().getPieceShape() == ShapeData.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            isRotate = pieceController.tryMove(pieceController.rotateRightHelper(), currentX, currentY);
        }

        else if(pieceController.getCurrentPiece().getPieceShape() == ShapeData.Tetrominoes.LineShape) {   //I블록인 경우 IShape SRS offset적용
            for (int i = 0; i < 5; i++) {
                int moveX = SRSData.IShapeSrsKick[pieceController.getCurrentPiece().getRotateIndex()][i][0];
                int moveY = SRSData.IShapeSrsKick[pieceController.getCurrentPiece().getRotateIndex()][i][1];
                isRotate = pieceController.tryMove(pieceController.rotateRightHelper(), currentX + moveX, currentY + moveY);
                if(isRotate) break;
            }
        }

        else{   //나머지 Tetromino에 대해서 SRS offset적용
            for (int i = 0; i < 5; i++) {
                int moveX = SRSData.srsKick[pieceController.getCurrentPiece().getRotateIndex()][i][0];
                int moveY = SRSData.srsKick[pieceController.getCurrentPiece().getRotateIndex()][i][1];
                isRotate = pieceController.tryMove(pieceController.rotateRightHelper(), currentX + moveX, currentY + moveY);
                if(isRotate) break;
            }
        }
        if(isRotate) {
            pieceController.getCurrentPiece().plusRotateIndex();
        }
    }
}
