package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;

public class Coordinates {
    private int[][] coords;

    public Coordinates() {
        coords = new int[4][2];
    }


    //블록들의 1x1블록 4개의 위치를 저장하는 coords블록을 초기화한다.
    public void setPieceShape(ShapeData.Tetrominoes pieceShape) {
        for (int i = 0; i < 4 ; i++) {
            System.arraycopy(ShapeData.COORDS_TABLE[pieceShape.ordinal()][i], 0, coords[i], 0, 2);
        }
    }

    //1x1블록의 x값을 수정하는 메서드
    public void setX(int index, int x) {
        coords[index][0] = x;
    }

    //1x1블록의 y값을 수정하는 메서드
    public void setY(int index, int y) {
        coords[index][1] = y;
    }

    //1x1블록의 x값을 반환하는 메서드
    public int x(int index) {
        return coords[index][0];
    }

    //1x1블록의 y값을 반환하는 메서드
    public int y(int index) {
        return coords[index][1];
    }


    //1x1블록들 중 가장 작은 x값을 구하는 메서드
    public int minX() {
        int m = coords[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }

    //1x1블록들 중 가장 작은 y값을 구하는 메서드
    public int minY() {
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

}
