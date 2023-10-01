package kr.ac.jbnu.se.tetris;

import java.awt.*;

public class ShapeData {
    static final public int TETROMINOES_SIZE = 7;
    public enum Tetrominoes {
        NoShape, ZShape, SShape, LineShape, TShape, SquareShape,
        LShape, MirroredLShape
    }
    static public final int[][][] COORDS_TABLE = new int[][][]{
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
            {{-1, 1}, {0, 1}, {0, 0}, {1, 0}},    //ZShape
            {{-1, 0}, {0, 0}, {0, 1}, {1, 1}},    //SShape
            {{-1, 0}, {0, 0}, {1, 0}, {2, 0}},    //LineShape
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},    //TShape
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},     //squareShape
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},    //LShape,
            {{-1, 1}, {-1, 0}, {0, 0}, {1, 0}}      //MirroredLShape
    };

    static public final Color[] SHAPE_COLOR = new Color[]{
            new Color(0, 0, 0),
            new Color(204, 102, 102),
            new Color(102, 204, 102),
            new Color(102, 102, 204),
            new Color(204, 204, 102),
            new Color(204, 102, 204),
            new Color(102, 204, 204),
            new Color(218, 170, 0),
            new Color(255, 102, 102),   // 연한 빨강
            new Color(102, 255, 102),   // 연한 초록
            new Color(102, 102, 255),   // 연한 파랑
            new Color(255, 255, 102),   // 연한 노랑
            new Color(255, 102, 255),   // 연한 자주
            new Color(102, 255, 255),   // 연한 시안
            new Color(255, 204, 0)      // 연한 주황
    };

    private ShapeData(){
    }
}
