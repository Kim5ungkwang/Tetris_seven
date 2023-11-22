package kr.ac.jbnu.se.tetris.controllers;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

public class AdapterController extends KeyAdapter {
    Logger logger = Logger.getLogger(AdapterController.class.getName());
    private final ArrayList<AbstractInputController> memberList= new ArrayList<>();


    public AdapterController(){
        logger.info("adaptController start");
    }
    @Override
    public void keyPressed(KeyEvent e){
        int in= e.getKeyCode();
        in= Character.toLowerCase(in);
        logger.info("pressed : "+in);
        for (AbstractInputController abstractInputController : memberList) {
            try {
                abstractInputController.action(in);
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void addList(AbstractInputController controller){
        memberList.add(controller);
    }

}
