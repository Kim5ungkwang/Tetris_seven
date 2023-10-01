package kr.ac.jbnu.se.tetris.views;

import javax.swing.*;
import java.awt.*;

public class NextBlockPanel extends JPanel {
    TetrisBoard tetrisBoard;
    JPanel next1;
    JPanel next2;
    JPanel next3;
    JPanel hold;
    public NextBlockPanel(){
        next1 = new JPanel();
        next2 = new JPanel();
        next3 = new JPanel();
        hold = new JPanel();
        init();
    }

    public void init(){
        next1.setSize(100, 100);
        next2.setSize(100, 100);
        next3.setSize(100, 100);
        hold.setSize(100, 100);
        setSize(300,100);
        setLayout(new FlowLayout());
        add(hold);
        add(next1);
        add(next2);
        add(next3);
    }

}
