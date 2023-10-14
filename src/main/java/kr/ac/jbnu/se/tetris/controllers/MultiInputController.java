package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.service.WebSocketService;

import java.util.logging.Logger;

public class MultiInputController {

    Logger logger= Logger.getLogger(KeyInputController.class.getName());
    private final KeyInput input;
    private final BoardController controller;


    public MultiInputController(KeyInput input, BoardController controller) {
        logger.info("MultiInputController start");
        this.input = input;
        this.controller=controller;
        
    }

    public void action(int keycode) throws CloneNotSupportedException {
        logger.info("input : " + keycode);

        if(keycode == input.getPause())
            controller.pause();

        if (!controller.isPaused()) {

            if (keycode == input.getMoveLeft()) {
                WebSocketService.getInstance().sendMessage("left");
            } else if (keycode == input.getMoveRight()) {
                WebSocketService.getInstance().sendMessage("right");
                //pieceController.moveRight();
            } else if (keycode == input.getBlockHold()) {
                WebSocketService.getInstance().sendMessage("hold");
            } else if (keycode == input.getRotateLeft()) {
                WebSocketService.getInstance().sendMessage("rotateLeft");
            } else if (keycode == input.getRotateRight()) {
                WebSocketService.getInstance().sendMessage("rotateRight");
            } else if (keycode == input.getDropDown()) {
                WebSocketService.getInstance().sendMessage("dropDown");
            }else if (keycode==input.getOneLineDown()){
                WebSocketService.getInstance().sendMessage("oneLineDown");
            }
        }
    }
}



