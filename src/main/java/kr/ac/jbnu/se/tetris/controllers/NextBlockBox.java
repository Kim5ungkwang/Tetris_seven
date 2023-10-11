package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 다음 블록을 보여주는 상자를 관리하는 클래스 입니다.
 */
public class NextBlockBox extends JPanel {
    private static final int showBoxWidth = 4;  //상자의 배열 크기
    private static final int showBoxHeight = 4; //상자의 배열 크기
    private static final int BOX_WIDTH = 100;   //상자의 크기 pixel단위
    private static final int BOX_HEIGHT = 100;  //상자의 크기 pixel단위
    private ShapeData.Tetrominoes showBox;  //상자에 들어갈 테트로미노의 종류
    NextBlockPanelController nextBlockPanelController;
    PieceController pieceController;    //상자에 들어갈 테트로미노를 받기위하여 pieceController와 연결


    /**
     * 다음 블록을 보여주는 박스의 크기와 색을 지정해주는 생성자 입니다.
     * @param parent NextBlockPanelController 객체
     */
    public NextBlockBox(NextBlockPanelController parent){
        this.nextBlockPanelController = parent;

        setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        setBackground(new Color(66, 66, 66, 179));    //Box의 배경색
        setVisible(true);
    }

    /**
     * 의존성 주입
     * 다음 블럭 혹은 hold블럭이 무엇인지 확인하기 위함
     */
    public void setPieceController() {
        this.pieceController = nextBlockPanelController.getPlayerPage().getBoard().pieceController;
    }

    /**
     * 첫번째 다음 블럭을 업데이트하는 메서드
     */
    public void next1Update(){
        setPieceController();
        showBox = pieceController.getBrickQueueManager().getFirstNextTetrominoes();
    }

    /**
     * 두번째 다음 블럭을 업데이트하는 메서드
     */
    public void next2Update(){
        setPieceController();
        showBox = pieceController.getBrickQueueManager().getSecondNextTetrominoes();
    }

    /**
     * 세번째 다음 블럭을 업데이트하는 메서드
     */
    public void next3Update(){
        setPieceController();
        showBox = pieceController.getBrickQueueManager().getThirdNextTetrominoes();
    }

    /**
     * hold 블럭이 무엇인지 업데이트하는 메서드
     */
    public void holdUpdate(){
        setPieceController();
        showBox = pieceController.getHoldPiece().getPieceShape();
    }

    /**
     * 상자안에 showBox에 해당하는 테트로미노를 그리는 메서드
     * @param g  the <code>Graphics</code> context in which to paint
     */
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


