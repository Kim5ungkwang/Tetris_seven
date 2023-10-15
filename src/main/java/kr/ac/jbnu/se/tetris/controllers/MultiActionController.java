package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.models.KeyInput;

import java.util.logging.Logger;

public class MultiActionController {
    Logger logger = Logger.getLogger(KeyInputController.class.getName());

    private final BoardController controller;
    private final PieceController pieceController;

    public MultiActionController( BoardController controller) {
        logger.info("MultiActionController start");

        this.controller = controller;
        this.pieceController = controller.getPieceController();
    }

    public void action(String message) throws CloneNotSupportedException {
        logger.info("input : " + message);

        if (message.equals("pause"))
            controller.pause();

        if (!controller.isPaused()) {

            if (message.equals("left")) {
                pieceController.moveLeft();
            } else if (message.equals("right")) {
                pieceController.moveRight();
            } else if (message.equals("hold")) {
                pieceController.holdingPiece();
            } else if (message.equals("rotateLeft")) {
                pieceController.rotateLeft();
            } else if (message.equals("rotateRight")) {
                pieceController.rotateRight();
            } else if (message.equals("dropDown")) {
                pieceController.dropDown();
            } else if (message.equals("oneLineDown")) {
                pieceController.oneLineDown();
            }
        }
    }
}

