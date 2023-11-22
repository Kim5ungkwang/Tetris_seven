package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;

public class KeyInputController extends AbstractInputController {
     private final PieceController pieceController;

    public KeyInputController(KeyInput input, BoardController controller) {
        super(input, controller);
        this.pieceController = controller.getPieceController();

    }

    public void action(int keycode) throws CloneNotSupportedException {
        logger.info("input : " + keycode);
        //if(keycode == input.getPause())
            //controller.pause();
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




