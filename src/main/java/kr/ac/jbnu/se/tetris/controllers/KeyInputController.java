package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Music;

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

    public void action(int keycode) throws CloneNotSupportedException {
        logger.info("input : " + keycode);

        if(keycode == input.getPause())
            controller.pause();

        if (!controller.isPaused()) {

            if (keycode == input.getMoveLeft()) {
                pieceController.moveLeft();
            } else if (keycode == input.getMoveRight()) {
                pieceController.moveRight();
            } else if (keycode == input.getBlockHold()) {
                pieceController.holdingPiece();
            } else if (keycode == input.getRotateLeft()) {
                pieceController.rotateLeft();
            } else if (keycode == input.getRotateRight()) {
                pieceController.rotateRight();
            } else if (keycode == input.getDropDown()) {
                pieceController.dropDown();
            }else if (keycode==input.getOneLineDown()){
                pieceController.oneLineDown();
            }
        }
    }
}




