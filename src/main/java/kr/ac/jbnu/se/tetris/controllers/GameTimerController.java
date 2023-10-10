package kr.ac.jbnu.se.tetris.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * 게임이 진행되는 시간을 관리하는 클래스
 * 클래스의 변수는 항상 업데이트 된 상태는 아니므로 반드시 메서드를 사용해서 접근할 것
 */
public class GameTimerController implements ActionListener {
    final private Timer printTimer;
    protected static boolean isRunning;
    protected static int pasuedTime;
    protected static int oldTime;
    protected static String timerBuffer;
    private static BoardController boardController;


    /**
     * 게임이 진행되는 시간을 관리하는 클래스
     */
    public GameTimerController(BoardController parent){
        boardController = parent;
        isRunning = false;
        pasuedTime = 0;
        oldTime = 0;
        printTimer = new Timer(1000,this);  //actionPerformed가 delay마다 실행
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        printGameTime();    //시간 표시
    }

    /**
     * 타이머 시작
     */
    public void timerStart(){
        if(!isRunning) {
            pasuedTime = (int) System.currentTimeMillis() / 1000 - oldTime;
            isRunning = true;
            printTimer.start();
        }
    }

    /**
     * 타이머 일시중지
     */
    public void timerPause(){
        if(isRunning) {
            oldTime = (int) System.currentTimeMillis() / 1000 - pasuedTime;
            isRunning = false;
            printTimer.stop();
        }
    }

    /**
     * 초단위 시간을 분 : 초로 바꿔주는 메서드
     * @param secs 초단위 시간
     * timerBuffer에 초단위 시간을 기록한다.
     */
    protected void secToMMSS(int secs){
        int min, sec;
        sec = secs % 60;
        min = secs / 60 % 60;

        this.timerBuffer = String.format("%02d : %02d", min, sec);
    }

    /**
     * 진행된 시간을 반환하는 메서드
     * @return 진행된 시간을 초 단위로 반환
     */
    public int getCurrentTimeSec(){
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
        printTimer.stop();
    }

}
