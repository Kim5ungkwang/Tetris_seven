package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;

import java.util.logging.Logger;

public class KeyInputController {
    Logger logger= Logger.getLogger(KeyInputController.class.getName());
    private final KeyInput input;
    private final BoardController controller;

    public KeyInputController(KeyInput input,BoardController controller) {
        logger.info("keyInputController start");
        this.input = input;
        this.controller=controller;
    }

    public void action(int keycode){
        logger.info("input : "+keycode);
        if (controller.isPaused())
            return;
        {
            if(keycode==input.getPause()){
                controller.pause();
            }
            if(keycode==input.getMoveLeft()){
                controller.moveLeft();
            }
            else if(keycode==input.getMoveRight()){
                controller.moveRight();
            }
            else if(keycode==input.getBlockHold()){
                //블록홀드 추가시 추가할것.
            }
            else if(keycode==input.getRotateLeft()){
                controller.rotateLeft();
            }
            else if(keycode==input.getRotateRight()){
                controller.rotateRight();
            }
            else if (keycode==input.getDropDown()){
                controller.dropDown();
            }



        }

    }

}


