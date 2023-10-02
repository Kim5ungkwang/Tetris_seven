package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.controllers.NextBlockPanelController;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class NextBlockPanel extends JPanel {
    @Getter
    PlayerPage playerPage;
    NextBlockPanelController next1Box;
    NextBlockPanelController next2Box;
    NextBlockPanelController next3Box;
    NextBlockPanelController holdBox;
    public NextBlockPanel(PlayerPage playerPage){
        this.playerPage = playerPage;
        this.next1Box = new NextBlockPanelController(this);
        this.next2Box = new NextBlockPanelController(this);
        this.next3Box = new NextBlockPanelController(this);
        this.holdBox = new NextBlockPanelController(this);
        //next1Box.setPreferredSize(new Dimension(70, 70));
        //next2Box.setPreferredSize(new Dimension(70, 70));
        //next3Box.setPreferredSize(new Dimension(70, 70));
        //holdBox.setPreferredSize(new Dimension(70, 70));
    }

    public void init(){
        next1Box.init();
        next2Box.init();
        next3Box.init();
        holdBox.init();

        setPreferredSize(new Dimension(100, 600));

        setLayout(new FlowLayout());
        add(new JLabel("///////HOLD///////"));
        add(holdBox);
        add(new JLabel("///////NEXT///////"));
        add(next1Box);
        add(new JLabel("//////////////////"));
        add(next2Box);
        add(new JLabel("//////////////////"));
        add(next3Box);

        setBackground(new Color(220, 200, 200));

    }

    public void update(){
        holdBox.holdInit();
        next1Box.firstNextInit();
        next2Box.secondNextInit();
        next3Box.thirdNextInit();
    }
    public void paint(Graphics g){
        super.paint(g);
    }
}
