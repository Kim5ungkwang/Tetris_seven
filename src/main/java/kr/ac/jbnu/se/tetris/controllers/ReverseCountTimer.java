package kr.ac.jbnu.se.tetris.controllers;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Delayed;

public class ReverseCountTimer implements ActionListener{
    private static int INITIAL_TIME_LIMIT = 10 *  1000;
    Timer countDownDelay;
    private static boolean isRunning;
    private static int pasuedTime;
    private static int oldTime;
    private static int timeLimit;
    private static String timerBuffer;
    BoardController boardController;

    public ReverseCountTimer(){
        countDownDelay = new Timer(1000, this);
        isRunning = false;
        pasuedTime = 0;
        oldTime = 0;
        timeLimit = 0;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void reverseCountStart(){
        if(!isRunning) {
            pasuedTime = (int) System.currentTimeMillis() / 1000 - oldTime;
            isRunning = true;
            countDownDelay.start();
        }
    }

    /**
     * 타이머 일시중지
     */
    public void timerpause(){
        if(isRunning) {
            oldTime = (int) System.currentTimeMillis() / 1000 - pasuedTime;
            isRunning = false;
            countDownDelay.stop();
        }
    }

    /**
     * 초단위 시간을 분 : 초로 바꿔주는 메서드
     * @param secs 초단위 시간
     * timerBuffer에 초단위 시간을 기록한다.
     */
    private void secToMMSS(int secs){
        int min, sec;
        sec = secs % 60;
        min = secs / 60 % 60;

        this.timerBuffer = String.format("%02d : %02d", min, sec);
    }

    /**
     * 진행된 시간을 반환하는 메서드
     * @return 진행된 시간을 초 단위로 반환
     */
    public static int getCurrentTimeSec(){
        if(isRunning)
            return (int) System.currentTimeMillis() / 1000 - pasuedTime;
        return oldTime;
    }

    /**
     * 진행된 시간을 분 : 초로 consol에 띄우는 메서드
     */
    public String printGameTime(){
        secToMMSS(getCurrentTimeSec());
        boardController.getPlayerPage().getGameTimer().setText(timerBuffer);
        return timerBuffer;
    }

    public void stop(){
        countDownDelay.stop();
    }
}
