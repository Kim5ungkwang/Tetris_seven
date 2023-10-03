package kr.ac.jbnu.se.tetris.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Timer;

public class GameTimerModel{

    @Getter
    private int timeCount;
    @Getter
    @Setter
    private int timeLimit;

    public GameTimerModel(){
    }
}
