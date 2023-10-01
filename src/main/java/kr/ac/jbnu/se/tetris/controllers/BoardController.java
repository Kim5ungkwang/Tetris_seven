package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.Coordinates;
import kr.ac.jbnu.se.tetris.models.Piece;
import kr.ac.jbnu.se.tetris.views.TetrisBoard;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoardController{
    @Getter
    private BoardModel boardModel;
    @Getter
    private TetrisBoard tetrisBoard;
    @Getter
    private PieceController pieceController;
    private int numLinesRemoved = 0;
    @Getter
    private Timer timer;

    private boolean isStarted;
    private boolean isPaused;

    /**
     * 게임이 이루어지는 보드 클래스
     * @param tetrisBoard 뷰와 연결
     */
    public BoardController(TetrisBoard tetrisBoard){
        this.boardModel = new BoardModel();
        this.tetrisBoard = tetrisBoard;
        this.pieceController = new PieceController(this);

        this.boardModel.setTimerDelay(400);

        this.timer = new Timer(boardModel.getTimerDelay(), tetrisBoard);

    }

    /////////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////

    /**
     * Timer Delay마다 실행되는 메서드
     */
    public void gameAction() {
        if (pieceController.getIsFallingFinished()) {
            pieceController.setIsFallingFinished(false);
            pieceController.newPiece();
        } else {
            pieceController.oneLineDown();
        }
    }

    public boolean isPaused(){
        return isPaused;
    }

    public void start(){
        if(isPaused()) return;
        isStarted = true;
        pieceController.setIsFallingFinished(false);
        numLinesRemoved = 0;
        clearBoard();
        pieceController.newPiece();
        timer.start();
    }

    public void pause(){
        if(!isStarted) return;

        isPaused = !isPaused;

        if(!isPaused){
            timer.stop();
            tetrisBoard.setStatusText("paused");
        }else{
            timer.start();;
            tetrisBoard.setStatusText(String.valueOf(numLinesRemoved));
        }
        tetrisBoard.repaint();
    }

    private void clearBoard(){
        for (int i = 0; i < BoardModel.getBoardHeight() * BoardModel.getBoardWidth(); ++i)
            boardModel.setboard(i, ShapeData.Tetrominoes.NoShape);
    }

    private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = BoardModel.getBoardHeight() - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardModel.getBoardWidth(); ++j) {
                if (shapeAt(j, i) == ShapeData.Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardModel.getBoardHeight() - 1; ++k) {
                    for (int j = 0; j < BoardModel.getBoardWidth(); ++j)
                        boardModel.setboard((k * BoardModel.getBoardWidth()) + j, shapeAt(j, k + 1));
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            tetrisBoard.setStatusText(String.valueOf(numLinesRemoved));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            tetrisBoard.repaint();
        }
    }

    public void pieceDropped(Piece droppedPiece){
        for(int i = 0; i < 4; i++){
            int x = droppedPiece.getCurrentX() + droppedPiece.getCoordinates().x(i);
            int y = droppedPiece.getCurrentY() - droppedPiece.getCoordinates().y(i);
            boardModel.setboard((y * BoardModel.getBoardWidth()) + x, droppedPiece.getPieceShape()) ;
        }

        removeFullLines();

        if(!pieceController.getIsFallingFinished())
            pieceController.newPiece();
    }

    public ShapeData.Tetrominoes shapeAt(int x, int y){
        return boardModel.getBoard((y * BoardModel.getBoardWidth()) + x);
    }
//////////////////////////////////////////////////////////////////////////////////////

public void paintHelper(Graphics g, double width, double height){
    int squareWidth = (int) width / BoardModel.getBoardWidth();
    int squareHeight = (int) height / BoardModel.getBoardHeight();

    int boardTop = (int) height - BoardModel.getBoardHeight() * squareHeight;


    for (int i = 0; i < BoardModel.getBoardHeight(); ++i) {

        for (int j = 0; j < BoardModel.getBoardWidth(); ++j) {
            ShapeData.Tetrominoes shape = shapeAt(j, BoardModel.getBoardHeight() - i - 1);

            if (shape != ShapeData.Tetrominoes.NoShape)
                drawSquare(g, j * squareWidth, boardTop + i * squareHeight, squareWidth,
                        squareHeight, shape, false);
        }
    }

    if (pieceController.getCurrentPiece().getPieceShape() != ShapeData.Tetrominoes.NoShape) {
        for (int i = 0; i < 4; ++i) {
            int ghostY =  pieceController.getGhostPiece().getCurrentGhostPieceY()
                    - pieceController.getGhostPiece().getCurrentGhostPiece().getCoordinates().y(i);

            int x = pieceController.getCurrentPiece().getCurrentX()
                    + pieceController.getCurrentPiece().getCoordinates().x(i);

            int y = pieceController.getCurrentPiece().getCurrentY()
                    - pieceController.getCurrentPiece().getCoordinates().y(i);

            drawSquare(g, x * squareWidth, boardTop + (BoardModel.getBoardHeight() - ghostY - 1) * squareHeight,
                    squareWidth, squareHeight,pieceController.getCurrentPiece().getPieceShape(), true);

            drawSquare(g, x * squareWidth, boardTop + (BoardModel.getBoardHeight() - y - 1) * squareHeight,
                    squareWidth, squareHeight, pieceController.getCurrentPiece().getPieceShape(), false);
        }
    }
}

    public void drawSquare(Graphics g, int x, int y, int squareWidth, int squareHeight, ShapeData.Tetrominoes shape, boolean isGhost)
    {
        Color color = ShapeData.SHAPE_COLOR[shape.ordinal()];

        if(isGhost){
            color = ShapeData.SHAPE_COLOR[shape.ordinal() + ShapeData.TETROMINOES_SIZE];   //GhostPiece라면 연한색으로 출력
        }

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1,
                x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1, y + squareHeight - 1,
                x + squareWidth - 1, y + 1);
    }

    public void paintUpdate(){
        tetrisBoard.repaint();
    }

    public void gameOver(){
        tetrisBoard.setStatusText("game over");
    }

}
