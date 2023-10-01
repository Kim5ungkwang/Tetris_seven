package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;

/**
 * 피스를 구성하는 1x1블록을 4개를 관리하는 클래스
 */
public class Coordinates {
    private int[][] coords; //1x1 블록

    /**
     * 1x1 블록을 생성한다.
     */
    public Coordinates() {
        coords = new int[4][2];
    }

    /**
     * 1x1블록의 Tetrominoes를 지정해주는 메서드
     * @param pieceShape 1x1블록이 나타낼 Tetrominoes 값
     */
    public void setPieceShape(ShapeData.Tetrominoes pieceShape) {
        for (int i = 0; i < 4 ; i++) {
            System.arraycopy(ShapeData.COORDS_TABLE[pieceShape.ordinal()][i], 0, coords[i], 0, 2);
        }
    }

    /**
     *1x1블록의 x 값을 변경하는 메서드
     * @param index 1x1블록 4개를 가르키는 index
     * @param x 변경하고 싶은 값
     */
    public void setX(int index, int x) {
        coords[index][0] = x;
    }

    /**
     *1x1블록의 y 값을 변경하는 메서드
     * @param index 1x1블록 4개를 가르키는 index
     * @param y 변경하고 싶은 값
     */
    public void setY(int index, int y) {
        coords[index][1] = y;
    }

    /**
     * 1x1블록의 x값을 반환하는 메서드
     * @param index 1x1블록 4개를 가르키는 index
     * @return index에 해당하는 1x1블록의 x값
     */
    public int x(int index) {
        return coords[index][0];
    }

    /**
     * 1x1블록의 y값을 반환하는 메서드
     * @param index 1x1블록 4개를 가르키는 index
     * @return index에 해당하는 1x1블록의 y값
     */
    public int y(int index) {
        return coords[index][1];
    }

    /**
     * 1x1블록중 가장 작은 x값을 반환하는 메서드
     * @return 1x1블록중 가장 작은 x값
     */
    public int minX() {
        int m = coords[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }

    /**
     * 1x1블록중 가장 작은 y값을 반환하는 메서드
     * @return 1x1블록중 가장 작은 y값
     */
    public int minY() {
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

}
