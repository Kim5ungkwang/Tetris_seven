package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.controllers.pages.TimeAttackPage;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import lombok.Getter;

/**
 * 타임어택 모드 보드 컨트롤러
 */
public class TimeAttackBoardController extends BoardController{
    @Getter
    private ReverseCountTimer reverseCountTimer;    //거꾸로 초를 세는 타이머
    @Getter
    private TimeAttackPage timeAttackPage;

    /**
     * 타임어택 모드 보드 컨트롤러 생성자
     * @param parent 게임이 그려질 페이지
     * @param input 게임을 진행할 때 사용하는 키
     */
    public TimeAttackBoardController(TimeAttackPage parent, KeyInput input){
        super(parent, input);
        timeAttackPage = parent;
        playerPage = parent;
        reverseCountTimer = new ReverseCountTimer(this);
    }

    /**
     * 게임을 시작할 때 호출하는 메서드
     */
    @Override
    public void start(){
        if (isPaused()) return; // 일시정지 상태에선 게임을 시작할 수 없다.
        isStarted = true;
        pieceController.setIsFallingFinished(false);
        numLinesRemoved = 0;
        clearBoard();
        pieceController.setNextBlockPanel();
        pieceController.newPiece();
        timer.start();
        reverseCountTimer.timerStart(); //기존 코드와 차이점 일반 타이머를 리버스 타이머로 변경
    }

    /**
     * 블럭이 차있을 때 한줄을 지우는 메서드
     */
    @Override
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
            new SoundEffectPlayer().start();        // 블럭사라짐 효과음
            reverseCountTimer.addTimeLimit(numFullLines);   //기존 코드와 차이점 -> 줄을 지운만큼 타이머 시간을 더한다.
            numLinesRemoved += numFullLines;
            timeAttackPage.getRemovedLine().setText(String.valueOf(numLinesRemoved));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            repaint();
        }
    }

    /**
     * 게임을 종료할 때 호출하는 메서드 (이 모드에 게임 오버는 없음)
     */
    public void gameClear(){
        pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
        reverseCountTimer.stop();
        timer.stop();
        getBoardModel().setIsStarted(false);
        repaint();
        timeAttackPage.gameClear(String.valueOf(numLinesRemoved));
    }
    @Override
    public void pause() {
        if (!isStarted) return;

        isPaused = !isPaused;

        if (isPaused) {
            timer.stop();
            reverseCountTimer.timerPause();
        } else {
            timer.start();
            reverseCountTimer.timerStart();
        }
        repaint();
    }
}
