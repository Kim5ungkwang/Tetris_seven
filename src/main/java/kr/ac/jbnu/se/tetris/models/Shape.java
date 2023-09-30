package kr.ac.jbnu.se.tetris.models;

public class Shape {
    static final public int TETROMINOES_SIZE = 7;
    public enum Tetrominoes {
        NoShape, ZShape, SShape, LineShape, TShape, SquareShape,
        LShape, MirroredLShape
    }

    private Tetrominoes pieceShape;
    private int[][] coords;
    private int[][][] coordsTable;
    static public int[][][] srsKick;
    static public int[][][] IShapeSrsKik;
    private int rotateIndex;    //회전 상태를 저장하는 변수
    BrickQueueManager brickQueueManager;


    public Shape() {
        srsKick = new int[][][]{    //증가는 right로테이션 감소는 left로테이션, x, y순서임
                {{0,0}, {-1, 0}, {-1, 1}, {0, -2}, {-1, -2}},   //0>>1
                {{0,0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},       //1>>2
                {{0,0}, {1, 0}, {1, 1}, {0, -2}, {1, -2}},      //2>>3
                {{0,0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}},    //3>>0
                {{0,0}, {1, 0}, {1, 1}, {0, -2}, {1, -2}},      //0>>3
                {{0,0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},      //1>>0
                {{0,0}, {-1, 0}, {-1, 1}, {0, -2}, {-1, -2}},   //2>>1
                {{0,0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}},    //3>>2
        };
        IShapeSrsKik = new int[][][]{
                {{0,0}, {-2, 0}, {1, 0}, {-2, -1}, {1, 2}},     //0>>1
                {{0,0}, {-1, 0}, {2, 0}, {-1, 2}, {2, -1}},     //1>>2
                {{0,0}, {2, 0}, {-1, 0}, {2, 1}, {-1, -2}},     //2>>3
                {{0,0}, {1, 0}, {-2, 0}, {1, -2}, {-2, 1}},     //3>>0
                {{0,0}, {-1, 0}, {2, 0}, {-1, 2}, {2, -1}},     //0>>3
                {{0,0}, {2, 0}, {-1, 0}, {2, 1}, {-1, -2}},     //1>>0
                {{0,0}, {1, 0}, {-2, 1}, {1, -2}, {-2, 1}},     //2>>1
                {{0,0}, {-2, 0}, {1, 0}, {-2, -1}, {1, 2}}      //3>>2
        };
        coords = new int[4][2];
        coordsTable = new int[][][]{    // 블럭의 모양을 초기화 x, y 순서임
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{-1, 1}, {0, 1}, {0, 0}, {1, 0}},    //ZShape
                {{-1, 0}, {0, 0}, {0, 1}, {1, 1}},    //SShape
                {{-1, 0}, {0, 0}, {1, 0}, {2, 0}},    //LineShape
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},    //Tshape
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},     //squareShape
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},    //LShape,
                {{-1, 1}, {-1, 0}, {0, 0}, {1, 0}}      //MirroredLShape

        };
        setPieceShape(Tetrominoes.NoShape); // 시작블록은 NoShape
        rotateIndex = 0;    //처음 생성된 블록은 회전 인덱스가 0으로 시작한다 오른쪽으로 회전할때마다 +1 왼쪽으로 회전할때마다 -1
        brickQueueManager = new BrickQueueManager();
    }

    //회전 인덱스를 1증가하는 메서드
    public void plusRotateIndex(){
        this.rotateIndex = (this.rotateIndex + 1) % 4;
    }

    //회전 인덱스를 1감소하는 메서드
    public void minusRotateIndex(){
        this.rotateIndex = (3 + this.rotateIndex) % 4;
    }

    //블록들의 1x1블록 4개의 위치를 저장하는 coords블록을 초기화한다.
    public void setPieceShape(Tetrominoes pieceShape) {
        for (int i = 0; i < 4 ; i++) {
            System.arraycopy(coordsTable[pieceShape.ordinal()][i], 0, coords[i], 0, 2);
        }
        this.pieceShape = pieceShape;
    }

    //회전 인덱스를 반환하는 메서드
    public int getRotateIndex(){
        return rotateIndex;
    }


    //1x1블록의 x값을 수정하는 메서드
    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    //1x1블록의 y값을 수정하는 메서드
    private void setY(int index, int y) {
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

    //해당 블록이 어떤 모양인지 반환하는 메서드
    public Tetrominoes getPieceShape() {
        return pieceShape;
    }

    //랜덤으로 블럭모양을 지정하는 메서드
    public void setRandomShape(){
        setPieceShape(brickQueueManager.getNewShape());
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

    //블록 자체의 왼쪽 회전을 담당하는 메서드
    public Shape rotateLeft() {
        if (pieceShape == Tetrominoes.SquareShape)
            return this;

        Shape result = new Shape();
        result.pieceShape = pieceShape;
        result.rotateIndex = rotateIndex;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }

    //블록 자체의 오른쪽 회전을 담당하는 메서드
    public Shape rotateRight() {
        if (pieceShape == Tetrominoes.SquareShape)
            return this;

        Shape result = new Shape();
        result.pieceShape = pieceShape;
        result.rotateIndex = rotateIndex;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }

}
