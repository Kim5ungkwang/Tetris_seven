package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.service.WebSocketService;


public class MultiInputController extends AbstractInputController {


    public MultiInputController(KeyInput input, BoardController controller) {
        super(input,  controller);
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



