package kr.ac.jbnu.se.tetris.controllers;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

public class AdapterController extends KeyAdapter {

    Logger logger = Logger.getLogger(AdapterController.class.getName());
    public static AdapterController adapterController= new AdapterController();
    ArrayList<KeyInputController> memberList= new ArrayList<>();

    private AdapterController(){
        logger.info("adaptController start");
    }
    @Override
    public void keyPressed(KeyEvent e){

        int in= e.getKeyCode();
        in= Character.toLowerCase(in);
        logger.info("pressed : "+in);

        for(int i=0;i<memberList.size();i++){
            memberList.get(i).action(in);
        }
    }
    public void addList(KeyInputController controller){
        memberList.add(controller);
    }

}
