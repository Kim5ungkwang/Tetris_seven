package kr.ac.jbnu.se.tetris.controllers;

import java.awt.event.KeyAdapter;
import java.util.logging.Logger;


public class WebSocketController extends KeyAdapter {

    private Logger logger = Logger.getLogger(WebSocketController.class.getName());
    private KeyInputController inputController1;
    private KeyInputController inputController2;
    public static WebSocketController webSocketController
            = new WebSocketController();

    private WebSocketController(){logger.info("WebSocketController start");}

    public void setInputController1(KeyInputController inputController) {
        this.inputController1= inputController;
    }

    public void setInputController2(KeyInputController inputController2) {
        this.inputController2 = inputController2;
    }
}
