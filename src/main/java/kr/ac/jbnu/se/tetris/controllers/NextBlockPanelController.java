package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.views.NextBlockPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 홀드 블록과 다음 블록을 보여주는 박스를 관리하는 클래스
 */
public class NextBlockPanelController extends JPanel {
    private static final int showBoxWidth = 4;
    private static final int showBoxHeight = 4;
    public static final int BOX_WIDTH = 100;
    public static final int BOX_HEIGHT = 100;
    NextBlockPanel nextBlockPanel;
    PieceController pieceController;
    ShapeData.Tetrominoes showBrick;

    /**
     * 홀드 블록과 다음 블록을 보여주는 박스를 관리하는 클래스
     * @param nextBlockPanel view와 연결
     */
    public NextBlockPanelController(NextBlockPanel nextBlockPanel){
        this.nextBlockPanel = nextBlockPanel;
    }

    /**
     * nextBlockPanelController 초기화
     */
    public void init(){
        setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        setBackground(new Color(220, 200, 200));    //Box의 배경색
        setPieceController();
    }

    /**
     * pieceController 의존성 주입
     */
    public void setPieceController() {
         this.pieceController = nextBlockPanel.getPlayerPage().getBoard().getBoardcontroller().getPieceController();
    }

    /**
     * Next Box 초기화
     */
    public void firstNextInit(){
        setPieceController();
        showBrick = pieceController.getBrickQueueManager().getFirstNextTetrominoes();
    }

    /**
     * 2번째 Next Box 초기화
     */
    public void secondNextInit(){
        setPieceController();
        showBrick = pieceController.getBrickQueueManager().getSecondNextTetrominoes();
    }

    /**
     * 3번째 Next Box 초기화
     */
    public void thirdNextInit(){
        setPieceController();
        showBrick = pieceController.getBrickQueueManager().getThirdNextTetrominoes();
    }

    /**
     * hold Box 초기화
     */
    public void holdInit(){
        setPieceController();
        showBrick = pieceController.getHoldPiece().getPieceShape();  //아직 구현 안됨
    }

    /**
     * Box에서 보여줄 블럭들을 그리는 메서드
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g){

        int squareWidth = (int) getSize().getWidth() /showBoxWidth;
          int squareHeight = (int) getSize().getHeight() / showBoxHeight;

        int boardTop = (int) getSize().getHeight() - showBoxHeight * squareHeight;

        super.paint(g);

        for(int i = 0; i < 4; ++i) {
            if(showBrick == ShapeData.Tetrominoes.NoShape) break;
            int drawX = showBoxWidth / 2 - 1 + ShapeData.COORDS_TABLE[showBrick.ordinal()][i][0];
            int drawY = showBoxHeight - 1 - ShapeData.COORDS_TABLE[showBrick.ordinal()][i][1];
            try {
                ShapeData.drawSquare(g, drawX * squareWidth, boardTop + (showBoxHeight - drawY - 1) * squareHeight,
                        squareWidth, squareHeight, showBrick);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Box에 들어갈 piece 블록을 그리는 메서드
     * @param g Graphics g
     * @param x Box의 x좌표
     * @param y Box의 y좌표
     * @param squareWidth 블록 하나의 width
     * @param squareHeight 블록 하나의 height
     * @param shape 블록의 Tetrominoes shape
     */
    public void drawSquare(Graphics g, int x, int y, int squareWidth, int squareHeight, ShapeData.Tetrominoes shape) {


        Color color = ShapeData.SHAPE_COLOR[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x, y, squareWidth, squareHeight);

        g.setColor(color.darker());
        g.drawRect(x, y, squareWidth, squareHeight);


    }
}
