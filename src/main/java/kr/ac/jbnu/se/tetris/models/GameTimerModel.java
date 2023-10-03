package kr.ac.jbnu.se.tetris.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Timer;

public class GameTimerModel{

    @Getter
    @Setter
    private int timeLimit;  //타임어택 모드에서 사용

    public GameTimerModel(){
    }
}
