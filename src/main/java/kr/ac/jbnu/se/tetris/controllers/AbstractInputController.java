package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;

import java.util.logging.Logger;

public abstract class  AbstractInputController{

    Logger logger= Logger.getLogger(KeyInputController.class.getName());
    protected KeyInput input;
    protected BoardController controller;

    public AbstractInputController(KeyInput input, BoardController controller) {
        logger.info("InputController start");
        this.input = input;
        this.controller=controller;

    }
    public abstract void action(int keycode)throws CloneNotSupportedException ;


}
