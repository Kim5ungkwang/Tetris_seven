package kr.ac.jbnu.se.tetris.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MultiAdapterController extends KeyAdapter {


    Logger logger = Logger.getLogger(MultiAdapterController.class.getName());

    private AbstractInputController multiInputController;

    public MultiAdapterController(){
        logger.info("MultiAdaptController start");
    }
    @Override
    public void keyPressed(KeyEvent e){

        int in= e.getKeyCode();
        in= Character.toLowerCase(in);
        logger.info("pressed : " + in);


            try {
                multiInputController.action(in);
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
        }


        public void addList(AbstractInputController controller){
        this.multiInputController=controller;

    }

}
