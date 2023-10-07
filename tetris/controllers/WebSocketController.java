package kr.ac.jbnu.se.tetris.controllers;

import lombok.Setter;

import java.awt.event.KeyAdapter;
import java.util.logging.Logger;

@Setter

public class WebSocketController extends KeyAdapter {

    private Logger logger = Logger.getLogger(WebSocketController.class.getName());
    private KeyInputController inputController1;
    private KeyInputController inputController2;
    public static WebSocketController webSocketController
            = new WebSocketController();

    private WebSocketController(){logger.info("WebSocketController start");}


}
