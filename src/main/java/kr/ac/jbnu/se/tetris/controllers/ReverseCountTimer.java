package kr.ac.jbnu.se.tetris.controllers;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReverseCountTimer implements ActionListener{
    Timer countDownDelay;
    boolean isRunning;

    public ReverseCountTimer(){
        super();
        countDownDelay = new Timer(1000, this);
        isRunning = false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void reverseCountStart(){

    }
}
