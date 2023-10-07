package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 홀드 블록과 다음 블록을 보여주는 박스를 관리하는 클래스
 */
public class NextBlockPanelController extends JPanel {
    static public int NEXT_BLOCK_PANEL_SIZE_WIDTH = 100;
    static public int NEXT_BLOCK_PANEL_SIZE_HEIGHT = 600;

    @Getter
    PlayerPageController playerPage;
    NextBlockBox next1Box;
    NextBlockBox next2Box;
    NextBlockBox next3Box;
    NextBlockBox holdBox;

    public NextBlockPanelController(PlayerPageController parent){
        this.playerPage = parent;
        next1Box = new NextBlockBox(this);
        next2Box = new NextBlockBox(this);
        next3Box = new NextBlockBox(this);
        holdBox = new NextBlockBox(this);

        setPreferredSize(new Dimension(NEXT_BLOCK_PANEL_SIZE_WIDTH, NEXT_BLOCK_PANEL_SIZE_HEIGHT));

        setLayout(new FlowLayout());

        add(new JLabel("///////HOLD///////"));
        add(holdBox);
        add(new JLabel("///////NEXT///////"));
        add(next1Box);
        add(new JLabel("///////////////////////////"));
        add(next2Box);
        add(new JLabel("///////////////////////////"));
        add(next3Box);

        setBackground(new Color(66, 66, 66));
        setVisible(true);
    }
    public void NextBlockPanelUpdate(){
        holdBox.holdUpdate();
        next1Box.next1Update();
        next2Box.next2Update();
        next3Box.next3Update();
    }

    public void paint(Graphics g){
        NextBlockPanelUpdate();
        super.paint(g);
    }



}
