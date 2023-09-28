package kr.ac.jbnu.se.tetris;

import kr.ac.jbnu.se.tetris.controllers.BoardController;
import kr.ac.jbnu.se.tetris.models.Shape;
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
    public static void rotateLeft(BoardController boardController, Shape currentPiece) {
        boolean isMoved;    //회전이 성공했는지 여부를 저장하는 변수
        int currentX = boardController.getCurrentX();
        int currentY = boardController.getCurrentY();
        if(currentPiece.getPieceShape() == Shape.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            boardController.tryMove(currentPiece.rotateLeft(), currentX, currentY);
        }
        else if(currentPiece.getPieceShape() == Shape.Tetrominoes.LineShape) {   //I블록인 경우 SRS IShape의 offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.IShapeSrsKik[currentPiece.getRotateIndex() + 4][i][0];
                int moveY = Shape.IShapeSrsKik[currentPiece.getRotateIndex() + 4][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateLeft(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.minusRotateIndex();    //회전에 성공했다면 회전 인덱스를 변경
                    break;
                }
            }
        }

        else{   //나머지 Tetromino에 대해서 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.srsKick[currentPiece.getRotateIndex() + 4][i][0];
                int moveY = Shape.srsKick[currentPiece.getRotateIndex() + 4][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateLeft(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.minusRotateIndex();    //회전에 성공했다면 회전 인덱스 변경
                    break;
                }
            }
        }
    }

    /**
     *블록을 오른쪽으로 회전하는 메서드이다
     * SRS를 적용했다.
     */
    public static void rotateRight(BoardController boardController,Shape currentPiece) {
        boolean isMoved;    //회전이 성공했는지 여부를 저장하는 변수
        int currentX = boardController.getCurrentX();
        int currentY = boardController.getCurrentY();
        if(currentPiece.getPieceShape() == Shape.Tetrominoes.SquareShape){  //O블록인 경우 SRS 적용 안함
            boardController.tryMove(currentPiece.rotateRight(), currentX, currentY);
        }

        else if(currentPiece.getPieceShape() == Shape.Tetrominoes.LineShape) {   //I블록인 경우 IShape SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.IShapeSrsKik[currentPiece.getRotateIndex()][i][0];
                int moveY = Shape.IShapeSrsKik[currentPiece.getRotateIndex()][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateRight(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.plusRotateIndex(); //회전에 성공했다면 회전 인덱스 변경
                    break;
                }
            }
        }

        else{   //나머지 Tetromino에 대해서 SRS offset적용
            for (int i = 0; i < 4; i++) {
                int moveX = Shape.srsKick[currentPiece.getRotateIndex()][i][0];
                int moveY = Shape.srsKick[currentPiece.getRotateIndex()][i][1];
                isMoved = boardController.tryMove(currentPiece.rotateRight(), currentX + moveX, currentY + moveY);
                if (isMoved) {
                    currentPiece.plusRotateIndex(); //회전에 성공했다면 회전 인덱스 변경
                    break;
                }
            }
        }
    }
}
