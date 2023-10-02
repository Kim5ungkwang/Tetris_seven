package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.views.NextBlockPanel;

import javax.swing.*;
import java.awt.*;

public class NextBlockPanelController extends JPanel {
    private final int showBoxWidth = 4;
    private final int showBoxHeight = 4;
    NextBlockPanel nextBlockPanel;
    PieceController pieceController;
    ShapeData.Tetrominoes showBrick;

    public NextBlockPanelController(NextBlockPanel nextBlockPanel){
        this.nextBlockPanel = nextBlockPanel;
    }

    public void init(){
        setPreferredSize(new Dimension(70, 70));
        setBackground(new Color(220, 200, 200));
        setPieceController();
    }

    /**
     * 의존성 주입
     */
    public void setPieceController() {
         this.pieceController = nextBlockPanel.getPlayerPage().getBoard().getBoardcontroller().getPieceController();
    }

    public void firstNextInit(){
        setPieceController();
        showBrick = pieceController.getBrickQueueManager().getFirstNextTetrominoes();
    }

    public void secondNextInit(){
        setPieceController();
        showBrick = pieceController.getBrickQueueManager().getSecondNextTetrominoes();
    }

    public void thirdNextInit(){
        setPieceController();
        showBrick = pieceController.getBrickQueueManager().getThirdNextTetrominoes();
    }

    public void holdInit(){
        setPieceController();
        showBrick = ShapeData.Tetrominoes.SquareShape;  //아직 구현 안됨
    }

    public void paint(Graphics g){

        int squareWidth = (int) getSize().getWidth() /showBoxWidth;
          int squareHeight = (int) getSize().getHeight() / showBoxHeight;

        int boardTop = (int) getSize().getHeight() - showBoxHeight * squareHeight;

        super.paint(g);

        for(int i = 0; i < 4; ++i) {
            if(showBrick == ShapeData.Tetrominoes.NoShape) break;
            int drawX = showBoxWidth / 2 - 1 + ShapeData.COORDS_TABLE[showBrick.ordinal()][i][0];
            int drawY = showBoxHeight - 1 - ShapeData.COORDS_TABLE[showBrick.ordinal()][i][1];
            drawSquare(g, drawX * squareWidth, boardTop + (showBoxHeight - drawY - 1) * squareHeight,
                    squareWidth, squareHeight, showBrick);
        }
    }

    public void drawSquare(Graphics g, int x, int y, int squareWidth, int squareHeight, ShapeData.Tetrominoes shape) {


        Color color = ShapeData.SHAPE_COLOR[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x, y, squareWidth, squareHeight);

        g.setColor(color.darker());
        g.drawRect(x, y, squareWidth, squareHeight);


    }
}
