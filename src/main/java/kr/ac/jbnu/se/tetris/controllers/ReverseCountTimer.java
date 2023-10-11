package kr.ac.jbnu.se.tetris.controllers;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.Delayed;

/**
 * 카운트
 *다운하는 시간을 관리하는 클래스
 */
public class ReverseCountTimer extends GameTimerController{
    private int timeLimit;  //제한 시간을 저장하는 변수
    private int addNum = 5; //블럭을 지웠을 때 얼마만큼 시간을 더해줄 것인지
    private final int INITIAL_TIME_LIMIT = 10;  // 초기시간
    TimeAttackBoardController board;

    /**
     * 생성자는 초기 시간 제한을 결정한다.
     * @param timeAttackBoardController
     */
    public ReverseCountTimer(TimeAttackBoardController timeAttackBoardController){
        super(timeAttackBoardController);
        this.board = timeAttackBoardController;
        this.timeLimit = INITIAL_TIME_LIMIT;
    }

    /**
     * 제한 시간마다 불러와서 남은 시간을 갱신하는 메서드 default delay는 1초
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(Objects.equals(printGameTime(), "00 : 00")){
            board.gameClear();  //시간이 00 :00이 되면 게임을 종료시킨다.
        }
    }

    /**
     * 남은 시간을 계산하는 메서드
     * @return
     */
    @Override
    public int getCurrentTimeSec(){
        if(isRunning)
            return timeLimit - ((int) System.currentTimeMillis() / 1000 - pasuedTime);
        return timeLimit - oldTime;
    }

    /**
     * 남은 시간을 더하는 메서드
     * @param num 얼마만큼 블럭을 부쉇는지
     */
    public void addTimeLimit(int num){
        timeLimit += num * 5;
    }

    /**
     * 남은 게임 시간을 그리는 메서드
     * @return 남은 시간을 String 타입으로 반환
     */
    @Override
    public String printGameTime(){
        secToMMSS(getCurrentTimeSec());
        board.getTimeAttackPage().getTimeLimit().setText(timerBuffer);
        return timerBuffer;
    }


}
