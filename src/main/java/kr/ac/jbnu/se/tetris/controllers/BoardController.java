package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.ShapeData;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.Piece;
import kr.ac.jbnu.se.tetris.views.PlayerPage;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *게임이 이루어지는 보드를 관리하는 클래스
 */
public class BoardController extends JPanel implements ActionListener {
    protected JLabel statusBar;
    @Getter
    protected BoardModel boardModel;
    @Getter
    protected PlayerPage playerPage;
    @Getter
    protected PieceController pieceController;
    protected int numLinesRemoved = 0;
    @Getter
    protected Timer timer;
    protected GameTimerController gameTimerController;

    protected boolean isStarted = false;
    protected boolean isPaused = false;
    @Getter
    protected Random rand;
    /**
     * BoardController 생성자
     * @param parent 해당 보드가 그려지는 PlayerPage
     */
    public BoardController(PlayerPage parent, Random rand){
        this.rand = rand;
        this.boardModel = new BoardModel();
        this.playerPage = parent;

        this.statusBar = parent.getStatusBar();
        this.pieceController = new PieceController(this);

        this.boardModel.setLoopDelay(700);  //루프 딜레이 설정 400

        this.timer = new Timer(boardModel.getLoopDelay(), this);
        this.gameTimerController = new GameTimerController(this);

        //AdapterController adapterController = new AdapterController();
        //addKeyListener(adapterController);
        //adapterController.addList(new KeyInputController(input,this));
        //setFocusable(true);
    }

    public BoardController() {
        //상속용
    }


    /////////////////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////////////////

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

    /**
     * 시간마다 테트로미노가 떨어질 수 있게 gameAction메서드를 호출
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        gameAction();
    }

    public boolean isPaused() {
        return isPaused;
    }

    /**
     * board의 게임을 시작한다.
     */
    public void start() {
        if (isPaused()) return; // 일시정지 상태에선 게임을 시작할 수 없다.
        isStarted = true;
        pieceController.setIsFallingFinished(false);
        numLinesRemoved = 0;
        clearBoard();
        pieceController.setNextBlockPanel();
        pieceController.newPiece();
        timer.start();
        gameTimerController.timerStart();
    }

    /**
     * 게임을 일시중지한다.
     */
    public void pause() {
        if (!isStarted) return;

        isPaused = !isPaused;

        if (isPaused) {
            timer.stop();
            gameTimerController.timerPause();
            //playerPage.getStatusBar().setText(("paused"));
        } else {
            timer.start();
            gameTimerController.timerStart();
            //playerPage.getStatusBar().setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    /**
     * 보드의 영역을 NoShape으로 초기화
     */
    public void clearBoard() {
        for (int i = 0; i < BoardModel.getBoardHeight() * BoardModel.getBoardWidth(); ++i)
            boardModel.setboard(i, ShapeData.Tetrominoes.NoShape);
    }

    /**
     * board에서 지울 수 있는 줄이 있다면 해당 줄을 지우고 윗 줄을 한칸씩 내리는 메서드
     */
    protected void removeFullLines() {
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
            //playerPage.getStatusBar().setText(String.valueOf(numLinesRemoved));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            repaint();
        }
    }

    /**
     * 앉은 상태의 piece를 board에 고정하는 메서드
     * @param droppedPiece 앉은 상태의 piece
     */
    public void pieceDropped(Piece droppedPiece) {
        for (int i = 0; i < 4; i++) {
            int x = droppedPiece.getCurrentX() + droppedPiece.getCoordinates().x(i);
            int y = droppedPiece.getCurrentY() - droppedPiece.getCoordinates().y(i);
            boardModel.setboard((y * BoardModel.getBoardWidth()) + x, droppedPiece.getPieceShape());
        }

        removeFullLines();  //고정한 이후 지울 수 있는 줄이 있는지 확인

        if (!pieceController.getIsFallingFinished())
            pieceController.newPiece();
    }

    /**
     * board에 해당 좌표에 어떤 Shape이 있는지 확인하는 메서드
     * @param x board의 x좌표
     * @param y board의 y좌표
     * @return 해당 board 좌표상 위치하는 TetrominoShape
     */
    public ShapeData.Tetrominoes shapeAt(int x, int y) {
        return boardModel.getBoard((y * BoardModel.getBoardWidth()) + x);
    }
//////////////////////////////////////////////////////////////////////////////////////


    /**
     * 보드를 그리는 메서드
     * 먼저 보드위에 어떤 블럭이 있는지 그리고난 후
     * 사용자가 움직이는 피스의 고스트 피스를 그리고
     * 그 위에 사용자가 움직이는 피스를 그립니다.
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @SneakyThrows
    public void paint(Graphics g){
        super.paint(g);
        int squareWidth = (int) getSize().getWidth() / BoardModel.getBoardWidth();
        int squareHeight = (int) getSize().getHeight() / BoardModel.getBoardHeight();

        int boardTop = (int) getSize().getHeight() - BoardModel.getBoardHeight() * squareHeight;


        for (int i = 0; i < BoardModel.getBoardHeight(); ++i) {

            for (int j = 0; j < BoardModel.getBoardWidth(); ++j) {
                ShapeData.Tetrominoes shape = shapeAt(j, BoardModel.getBoardHeight() - i - 1);

                if (shape != ShapeData.Tetrominoes.NoShape)
                    ShapeData.drawSquare(g,j * squareWidth, boardTop + i * squareHeight, squareWidth, squareHeight,
                            shape);
                else{
                    drawEmptySquare(g,j * squareWidth, boardTop + i * squareHeight, squareWidth, squareHeight);
                }
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

                drawGhostSquare(g, x * squareWidth, boardTop + (BoardModel.getBoardHeight() - ghostY - 1) * squareHeight,
                        squareWidth, squareHeight, pieceController.getCurrentPiece().getPieceShape());

                ShapeData.drawSquare(g, x * squareWidth, boardTop + (BoardModel.getBoardHeight() - y - 1) * squareHeight,
                        squareWidth, squareHeight, pieceController.getCurrentPiece().getPieceShape());

            }
        }
    }

    /**
     * 해당 좌표에 텅빈 사각형을 그리는 메서드 입니다.
     * 그리드를 표현하기 위해 사용합니다.
     * @param g Grahpics g
     * @param x x좌표
     * @param y y좌표
     * @param squareWidth 블럭의 width
     * @param squareHeight 블럭의 height
     */
    public void drawEmptySquare(Graphics g, int x, int y, int squareWidth, int squareHeight) {
        Color color = new Color(150, 150, 150);

        g.setColor(color);
        g.drawRect(x, y, squareWidth, squareHeight);
    }

    /**
     * 해당 좌표에 고스트 피스를 그리기 위해 사용합니다
     * @param g
     * @param x x좌표
     * @param y y좌표
     * @param squareWidth 사각형 하나의 크기
     * @param squareHeight 사각형 하나의 크기
     * @param shape 어떤 테트로미노를 그릴것인지
     */
    public void drawGhostSquare(Graphics g, int x, int y, int squareWidth, int squareHeight, ShapeData.Tetrominoes shape){
        Color color = ShapeData.SHAPE_COLOR[shape.ordinal()];
        g.setColor(color.brighter());
        g.fillRect(x, y, squareWidth, squareHeight);

        g.setColor(color.darker());
        g.drawRect(x, y, squareWidth, squareHeight);
    }

    /**
     * gameOver했을때 사용하는 메서드
     */
    public void gameOver() {
        playerPage.raiseGameOverFrame();
    }

    /**
     * 스테이터스바의 텍스트를 지정해주는 메서드
     * 사용안함
     * @param text 어떤 문자열을 그리게 할 것인지
     */
    public void setStatusText(String text) {
        //playerPage.getStatusBar().setText(text);
    }

    public void printTimer(String currentTimer){
        playerPage.getGameTimer().setText(currentTimer);
    }
}

