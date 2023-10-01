package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.Coordinates;
import kr.ac.jbnu.se.tetris.models.Piece;
import kr.ac.jbnu.se.tetris.views.TetrisBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoardController {
    private BoardModel boardModel;
    private TetrisBoard tetrisBoard;
    private PieceController pieceController;
    private int numLinesRemoved = 0;

    private Timer timer;

    private boolean isStarted;
    private boolean isPaused;

    public BoardController(TetrisBoard tetrisBoard) {
        this.boardModel = new BoardModel();
        this.tetrisBoard = tetrisBoard;
        this.pieceController = new PieceController(this);

        this.boardModel.setTimerDelay(400);

        this.timer = new Timer(boardModel.getTimerDelay(), tetrisBoard);

    }

    /////////////////////////////////////////////////////////////////////////////////

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public TetrisBoard getTetrisBoard() {
        return tetrisBoard;
    }

    public PieceController getPieceController() {
        return pieceController;
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean tutorialMode = true;
    private int tutorialStep = 0;


//////////////////////////////////////////////////////////


    public void gameAction() {
        if (pieceController.getIsFallingFinished()) {
            pieceController.setIsFallingFinished(false);
            pieceController.newPiece();
        } else {
            pieceController.oneLineDown();
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void start() {
        if (isPaused()) return;
        isStarted = true;
        pieceController.setIsFallingFinished(false);
        numLinesRemoved = 0;
        clearBoard();
        pieceController.newPiece();
        timer.start();
        if (tutorialMode) {
            showTutorial();
        }
    }

    public void pause() {
        if (!isStarted) return;

        isPaused = !isPaused;

        if (!isPaused) {
            timer.stop();
            tetrisBoard.setStatusText("paused");
        } else {
            timer.start();
            ;
            tetrisBoard.setStatusText(String.valueOf(numLinesRemoved));
        }
        tetrisBoard.repaint();
    }

    private void clearBoard() {
        for (int i = 0; i < BoardModel.getBoardHeight() * BoardModel.getBoardWidth(); ++i)
            boardModel.setboard(i, ShapeData.Tetrominoes.NoShape);
    }

    private void removeFullLines() {
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

    public void pieceDropped(Piece droppedPiece) {
        for (int i = 0; i < 4; i++) {
            int x = droppedPiece.getCurrentX() + droppedPiece.getCoordinates().x(i);
            int y = droppedPiece.getCurrentY() - droppedPiece.getCoordinates().y(i);
            boardModel.setboard((y * BoardModel.getBoardWidth()) + x, droppedPiece.getPieceShape());
        }

        removeFullLines();

        if (!pieceController.getIsFallingFinished())
            pieceController.newPiece();
    }

    public ShapeData.Tetrominoes shapeAt(int x, int y) {
        return boardModel.getBoard((y * BoardModel.getBoardWidth()) + x);
    }
//////////////////////////////////////////////////////////////////////////////////////

    public void paintHelper(Graphics g, double width, double height) {
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
                int ghostY = pieceController.getGhostPiece().getCurrentGhostPieceY()
                        - pieceController.getGhostPiece().getCurrentGhostPiece().getCoordinates().y(i);

                int x = pieceController.getCurrentPiece().getCurrentX()
                        + pieceController.getCurrentPiece().getCoordinates().x(i);

                int y = pieceController.getCurrentPiece().getCurrentY()
                        - pieceController.getCurrentPiece().getCoordinates().y(i);

                drawSquare(g, x * squareWidth, boardTop + (BoardModel.getBoardHeight() - ghostY - 1) * squareHeight,
                        squareWidth, squareHeight, pieceController.getCurrentPiece().getPieceShape(), true);

                drawSquare(g, x * squareWidth, boardTop + (BoardModel.getBoardHeight() - y - 1) * squareHeight,
                        squareWidth, squareHeight, pieceController.getCurrentPiece().getPieceShape(), false);
            }
        }
    }

    public void drawSquare(Graphics g, int x, int y, int squareWidth, int squareHeight, ShapeData.Tetrominoes shape, boolean isGhost) {
        Color color = ShapeData.SHAPE_COLOR[shape.ordinal()];

        if (isGhost) {
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

    public void paintUpdate() {
        tetrisBoard.repaint();
    }

    public void gameOver() {
        tetrisBoard.setStatusText("game over");
    }

    private void showTutorial() {
        String[] tutorialSteps = {
                "Step 1: 테트리스 규칙\n게임의 룰은 내려오는 블록을 한 줄이 꽉 차도록 합니다.\n 그 줄은 사라지고 이것을 반복하면서 블록이 맨 위까지 안 쌓이게 끝까지 버티면서 플레이하는 게임입니다.\n *튜토리얼을 건너뛰려면 아니오를 누르세요.",
                "Step 2: 블록 이동하기\n블록을 움직이려면 좌우방향키를 움직이세요",
                "Step 3: 블록 회전하기\n블록을 회전하려면 상하방향키를 움직이세요",
                "Step 4: 행 제거하기\n블록을 한 줄에 꽉 차도록 쌓으세요",
                "Step 5: SRS(Super Rotation System) 사용하기\n블록의 빈 공간에 다다르면 회전하면서 홈 사이에 블록을 넣으세요",
                "튜토리얼 끝! 게임을 시작합니다!"
        };


        if (tutorialStep < tutorialSteps.length) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    tutorialSteps[tutorialStep],
                    "튜토리얼 단계" + (tutorialStep + 1),
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                // 사용자가 튜토리얼을 진행하려고 선택한 경우
                tutorialStep++;
                System.out.println("튜토리얼을 시작합니다.");

                if (tutorialStep == 4) {
                    boolean inTutorial = true;
                    String imagePath = "C:/Users/yesye/Downloads/1695977668.gif/";
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    JLabel imageLabel = new JLabel();
                    imageLabel.setIcon(imageIcon);
                    JOptionPane.showMessageDialog(
                            this.getTetrisBoard(),
                            imageLabel,
                            tutorialSteps[4],
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                start();
            } else {
                // 사용자가 튜토리얼을 건너뛰려고 선택한 경우
                System.out.println("튜토리얼을 건너뜁니다.");
                // 여기에서 건너뛰는 동작 또는 메인 게임 화면을 시작하는 코드를 추가합니다.
                // }
            }
        }
    }
}

