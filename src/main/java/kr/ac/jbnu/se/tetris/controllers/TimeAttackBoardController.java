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
    private int gameLevel = 1;  //게임 진행 레벨, 시작 레벨은 1로

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
            timeAttackPage.getScoreLable().setText(String.valueOf(numLinesRemoved));
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            upLevel();
            repaint();
        }
    }

    /**
     * 게임을 종료할 때 호출하는 메서드
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

    /**
     * 삭제한 줄의 수에 따라 레벨을 변화시키는 메서드
     */
    public void upLevel(){
        switch (this.gameLevel){
            case 1:
                if(numLinesRemoved >= 10){
                    gameLevel++;
                    updateGameLevelState();
                }
                break;
            case 2:
                if(numLinesRemoved >= 20){
                    gameLevel++;
                    updateGameLevelState();
                }
                break;
            case 3:
                if(numLinesRemoved >= 30){
                    gameLevel++;
                    updateGameLevelState();
                }
                break;
            case 4:
                if(numLinesRemoved >= 40){
                    gameLevel++;
                    updateGameLevelState();
                }
                break;
            case 5:
                if(numLinesRemoved >= 50){
                    gameLevel++;
                    updateGameLevelState();
                }
                break;
            case 6:
                if(numLinesRemoved >= 60){
                    gameLevel++;
                    updateGameLevelState();
                }
                break;

            default:
                break;  //게임은 레벨은 7레벨까지 있습니다.
        }
    }

    /**
     * 게임의 레벨을 보여주는 Label과
     * 게임의 레벨에 따라 변화하는 딜레이를 업데이트하는 메서드
     */
    public void updateGameLevelState(){
        timeAttackPage.getGameLevelLabel().setText("Lv " + String.valueOf(gameLevel));
        switch (this.gameLevel){
            case 2:
                timer.setDelay(400);
                break;
            case 3:
                timer.setDelay(270);
                break;
            case 4:
                timer.setDelay(160);
                break;
            case 5:
                timer.setDelay(70);
                break;
            case 6:
                timer.setDelay(40);
                break;
            default:
                break;  //게임은 레벨은 7레벨까지 있습니다.
        }
    }
}
