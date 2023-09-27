package kr.ac.jbnu.se.tetris.controllers;


import kr.ac.jbnu.se.tetris.models.Shape;
import kr.ac.jbnu.se.tetris.views.TetrisBoard;

import javax.swing.*;
import java.awt.*;


public class BoardController {
    private TetrisBoard tetrisBoard;
    private int boardWidth;
    private int boardHeight;
    private boolean isFallingFinished = false;
    private boolean isStarted = false;
    private boolean isPaused = false;

    private int numLinesRemoved = 0;
    private int currentX = 0;
    private int currentY = 0;
    private Timer timer;

    private Shape currentPiece;
    private Shape.Tetrominoes[] board;

    public BoardController(int boardWidth, int boardHeight, TetrisBoard tetrisBoard) {
        //객체 초기화
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tetrisBoard = tetrisBoard;
        currentPiece = new Shape();
        //타이머
        timer = new Timer(400, tetrisBoard);
        timer.start();
        //보드를 크기 지정
        board = new Shape.Tetrominoes[boardWidth * boardHeight];

        //보드 클리어
        clearBoard();
    }

    //
    public void gameAction() {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isCurrentPieceNoShaped() {
        return currentPiece.getPieceShape() == Shape.Tetrominoes.NoShape;
    }

    //게임 시작
    public void start() {
        if (isPaused) return;
        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();
        newPiece();
        timer.start();

    }

    //게임 일시정지
    public void pause() {
        if (!isStarted)
            return;

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            tetrisBoard.setStatusText("paused");
        } else {
            timer.start();
            tetrisBoard.setStatusText(String.valueOf(numLinesRemoved));
        }
        tetrisBoard.repaint();
    }

    //블록이 한 칸씩 떨어짐
    public void oneLineDown()
    {
        if (!tryMove(currentPiece, currentX, currentY - 1))
            pieceDropped();
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY(){
        return currentY;
    }

    //보드 클리어
    private void clearBoard()
    {
        for (int i = 0; i < boardHeight * boardWidth; ++i)
            board[i] = Shape.Tetrominoes.NoShape;
    }

    //소프트 드롭
    public void dropDown()
    {
        int newY = currentY;
        while (newY > 0) {
            if (!tryMove(currentPiece, currentX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    //보드 위치에 무슨 블록이 들어있는지 확인하는 메서드
    private Shape.Tetrominoes shapeAt(int x, int y) {
        return board[(y * boardWidth) + x];
    }

    //보드와 보드 안의 블록을 그리는 함수
    public void paint(Graphics g, double width, double height) {
        int squareWidth = (int) width / boardWidth;
        int squareHeight = (int) height / boardHeight;
        int boardTop = (int) height - boardHeight * squareHeight;


        for (int i = 0; i < boardHeight; ++i) {
            for (int j = 0; j < boardWidth; ++j) {
                Shape.Tetrominoes shape = shapeAt(j, boardHeight - i - 1);
                if (shape != Shape.Tetrominoes.NoShape)
                    tetrisBoard.drawSquare(g, j * squareWidth,
                            boardTop + i * squareHeight, shape);
            }
        }

        if (currentPiece.getPieceShape() != Shape.Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = currentX + currentPiece.x(i);
                int y = currentY - currentPiece.y(i);
                tetrisBoard.drawSquare(g, x * squareWidth,
                        boardTop + (boardHeight - y - 1) * squareHeight,
                        currentPiece.getPieceShape());
            }
        }
    }

    //새로운 블록을 가져오는 메서드
    private void newPiece()
    {
        currentPiece.setRandomShape();
        currentX = boardWidth / 2 + 1;
        currentY = boardHeight - 1 + currentPiece.minY();

        if (!tryMove(currentPiece, currentX, currentY)) {
            currentPiece.setPieceShape(Shape.Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
            tetrisBoard.setStatusText("game over");
        }
    }

    //블록을 움직이는 시도를 하는 메서드
    public boolean tryMove(Shape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight)
                return false;
            if (shapeAt(x, y) != Shape.Tetrominoes.NoShape)
                return false;
        }

        currentPiece = newPiece;
        currentX = newX;
        currentY = newY;
        tetrisBoard.repaint();
        return true;
    }

    //하드 드롭
    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = currentX + currentPiece.x(i);
            int y = currentY - currentPiece.y(i);
            board[(y * boardWidth) + x] = currentPiece.getPieceShape();
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece();
    }

    //블록이 차면 삭제하는 메서드
    private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = boardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < boardWidth; ++j) {
                if (shapeAt(j, i) == Shape.Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < boardHeight - 1; ++k) {
                    for (int j = 0; j < boardWidth; ++j)
                        board[(k * boardWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            tetrisBoard.setStatusText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            currentPiece.setPieceShape(Shape.Tetrominoes.NoShape);
            tetrisBoard.repaint();
        }
    }

    //블록을 왼쪽으로 1칸 움직이는 메서드
    public void moveLeft() {
        tryMove(currentPiece, currentX - 1, currentY);
    }

    //블록을 오른쪽으로 1칸 움직이는 메서드
    public void moveRight() {
        tryMove(currentPiece, currentX + 1, currentY);
    }

    //SRS를 적용한 블록 왼쪽 회전 메서드
    public void rotateLeft(){
        BrickRotator.rotateLeft(this, currentPiece);
    }

    //SRS를 적용한 블록 오른쪽 회전 메서드
    public void rotateRight(){
        BrickRotator.rotateRight(this, currentPiece);
    }

}
