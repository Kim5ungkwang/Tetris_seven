package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.GameTimerModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameTimerController implements ActionListener {
    @Getter
    private GameTimerModel gameTimerModel;
    private Timer printTimer;
    private boolean isRunning;
    private int pasuedTime;
    private int runningTime;
    private int oldTime;
    private String timerBuffer;

    public GameTimerController(){
        this.gameTimerModel = new GameTimerModel();
        this.isRunning = false;
        isRunning = false;
        runningTime = 0;
        pasuedTime = 0;
        oldTime = 0;
        printTimer = new Timer(100,this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isRunning){
            printGameTime();
        }
    }

    public void timerstart(){
        if(!isRunning) {
            pasuedTime = (int) System.currentTimeMillis() / 1000 - oldTime;
            isRunning = true;
        }
    }

    public void timerpause(){
        if(isRunning) {
            oldTime = (int) System.currentTimeMillis() / 1000 - pasuedTime;
            isRunning = false;
        }
    }

    public void secToMMSS(int secs){
        int min, sec;
        sec = secs % 60;
        min = secs / 60 % 60;

        this.timerBuffer = String.format("%02d : %02d", min, sec);
    }

    public int getCurrentTimeSec(){
        if(isRunning)
            return (int) System.currentTimeMillis() / 1000 - pasuedTime;
        return oldTime;
    }

    public void printGameTime(){
        secToMMSS(getCurrentTimeSec());
        System.out.println(timerBuffer);
    }
}
