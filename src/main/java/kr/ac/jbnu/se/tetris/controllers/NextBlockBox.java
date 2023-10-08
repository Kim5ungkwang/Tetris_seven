package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NextBlockBox extends JPanel {
    private static final int showBoxWidth = 4;
    private static final int showBoxHeight = 4;
    private static final int BOX_WIDTH = 100;
    private static final int BOX_HEIGHT = 100;
    private ShapeData.Tetrominoes showBox;
    NextBlockPanelController nextBlockPanelController;
    PieceController pieceController;

    public NextBlockBox(NextBlockPanelController parent){
        this.nextBlockPanelController = parent;

        setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        setBackground(new Color(66, 66, 66, 179));    //Box의 배경색
        setVisible(true);
    }

    /**
     * 의존성 주입
     */
    public void setPieceController() {
        this.pieceController = nextBlockPanelController.getPlayerPage().getBoard().pieceController;
    }

    public void next1Update(){
        setPieceController();
        showBox = pieceController.getBrickQueueManager().getFirstNextTetrominoes();
    }

    public void next2Update(){
        setPieceController();
        showBox = pieceController.getBrickQueueManager().getSecondNextTetrominoes();
    }

    public void next3Update(){
        setPieceController();
        showBox = pieceController.getBrickQueueManager().getThirdNextTetrominoes();
    }

    public void holdUpdate(){
        setPieceController();
        showBox = pieceController.getHoldPiece().getPieceShape();
    }

    public void paint(Graphics g){
        super.paint(g);
        int squareWidth = (int) getSize().getWidth() /showBoxWidth;
        int squareHeight = (int) getSize().getHeight() / showBoxHeight;

        int boardTop = (int) getSize().getHeight() - showBoxHeight * squareHeight;


        for(int i = 0; i < 4; ++i) {
            if(showBox == ShapeData.Tetrominoes.NoShape) break;
            int drawX = showBoxWidth / 2 - 1 + ShapeData.COORDS_TABLE[showBox.ordinal()][i][0];
            int drawY = showBoxHeight - 3 - ShapeData.COORDS_TABLE[showBox.ordinal()][i][1];
            try {
                ShapeData.drawSquare(g, drawX * squareWidth, boardTop + (showBoxHeight - drawY - 1) * squareHeight,
                        squareWidth, squareHeight, showBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


