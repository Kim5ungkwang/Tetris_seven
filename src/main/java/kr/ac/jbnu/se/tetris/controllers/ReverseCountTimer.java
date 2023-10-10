package kr.ac.jbnu.se.tetris.controllers;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.Delayed;

public class ReverseCountTimer extends GameTimerController{
    private int timeLimit;  //초 단위
    private final int INITIAL_TIME_LIMIT = 10;
    TimeAttackBoardController board;
    public ReverseCountTimer(TimeAttackBoardController timeAttackBoardController){
        super(timeAttackBoardController);
        this.board = timeAttackBoardController;
        this.timeLimit = INITIAL_TIME_LIMIT;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(Objects.equals(printGameTime(), "00 : 00")){
            board.gameClear();
        }
    }

    @Override
    public int getCurrentTimeSec(){
        if(isRunning)
            return timeLimit - ((int) System.currentTimeMillis() / 1000 - pasuedTime);
        return timeLimit - oldTime;
    }

    public void addTimeLimit(int num){
        timeLimit += num * 5;
    }

    @Override
    public String printGameTime(){
        secToMMSS(getCurrentTimeSec());
        board.getTimeAttackPage().getTimeLimit().setText(timerBuffer);
        System.out.println("왜 안돼ㅐ");
        return timerBuffer;
    }


}
