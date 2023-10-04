package kr.ac.jbnu.se.tetris;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShapeData {
    static final public int TETROMINOES_SIZE = 7;

    /**
     * Tetrominoes
     */
    public enum Tetrominoes {
        NoShape, ZShape, SShape, LineShape, TShape, SquareShape,
        LShape, MirroredLShape, InterruptShape
    }

    /**
     * piece의 coordinates 정보
     */
    static public final int[][][] COORDS_TABLE = new int[][][]{
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
            {{-1, 0}, {0, 0}, {0, 1}, {1, 1}},    //ZShape
            {{-1, 1}, {0, 1}, {0, 0}, {1, 0}},    //SShape
            {{-1, 1}, {0, 1}, {1, 1}, {2, 1}},    //LineShape
            {{-1, 1}, {0, 1}, {1, 1}, {0, 0}},    //TShape
            {{0, 1}, {1, 1}, {0, 0}, {1, 0}},     //squareShape
            {{-1, 1}, {0, 1}, {1, 1}, {1, 0}},    //LShape,
            {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}}      //MirroredLShape
    };

    /**
     * Tetrominoes 색상 표
     */
    static public final Color[] SHAPE_COLOR = new Color[]{
            new Color(0, 0, 0),
            new Color(255, 102, 102),   // 연한 빨강
            new Color(102, 255, 102),   // 연한 초록
            new Color(102, 102, 255),   // 연한 파랑
            new Color(255, 255, 102),   // 연한 노랑
            new Color(255, 102, 255),   // 연한 자주
            new Color(102, 255, 255),   // 연한 시안
            new Color(255, 204, 0),      // 연한 주황
            new Color(204, 102, 102),
            new Color(102, 204, 102),
            new Color(102, 102, 204),
            new Color(204, 204, 102),
            new Color(204, 102, 204),
            new Color(102, 204, 204),
            new Color(218, 170, 0)

    };

    static public void drawSquare(Graphics g, int x, int y, int imgWidth, int imgHeight, Tetrominoes shape) throws IOException {
        BufferedImage bufferedImage = getImageFile(getBlockImagePath(shape));


        g.drawImage(bufferedImage, x, y, imgWidth, imgHeight, null);
    }

    public static BufferedImage getImageFile(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    /**
     * 블럭 이미지 파일 가져옴
     * @param shape Tetrominoes
     * @return 블럭 이미지 파일 위치
     */
    public static String getBlockImagePath(Tetrominoes shape){
        String imgPath = "";
        switch(shape){
            case ZShape:
                imgPath = "source/image/blocks/01.png";
                break;
            case SShape:
                imgPath = "source/image/blocks/02.png";
                break;
            case LineShape:
                imgPath = "source/image/blocks/03.png";
                break;
            case TShape:
                imgPath = "source/image/blocks/04.png";
                break;
            case SquareShape:
                imgPath = "source/image/blocks/05.png";
                break;
            case LShape:
                imgPath = "source/image/blocks/06.png";
                break;
            case MirroredLShape:
                imgPath = "source/image/blocks/07.png";
                break;
        }
        return imgPath;
    }

    private ShapeData(){
    }
}
