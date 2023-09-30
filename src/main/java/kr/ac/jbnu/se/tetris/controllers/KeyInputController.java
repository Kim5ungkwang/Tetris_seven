package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;

import java.util.logging.Logger;

public class KeyInputController {
    Logger logger= Logger.getLogger(KeyInputController.class.getName());
    private final KeyInput input;
    private final BoardController controller;
    private final PieceController pieceController;

    public KeyInputController(KeyInput input, BoardController controller) {
        logger.info("keyInputController start");
        this.input = input;
        this.controller=controller;
        this.pieceController = controller.getPieceController();
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
                pieceController.moveLeft();
            }
            else if(keycode==input.getMoveRight()){
                pieceController.moveRight();
            }
            else if(keycode==input.getBlockHold()){
                //블록홀드 추가시 추가할것.
            }
            else if(keycode==input.getRotateLeft()){
                pieceController.rotateLeft();
            }
            else if(keycode==input.getRotateRight()){
                pieceController.rotateRight();
            }
            else if (keycode==input.getDropDown()){
                pieceController.dropDown();
            }



        }

    }

}


