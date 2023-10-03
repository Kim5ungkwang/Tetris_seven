package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.controllers.NextBlockPanelController;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * NextBlock과 HoldBlock을 보여주는 Box를 담당하는 클래스
 */
public class NextBlockPanel extends JPanel {
    @Getter
    PlayerPage playerPage;
    NextBlockPanelController next1Box;
    NextBlockPanelController next2Box;
    NextBlockPanelController next3Box;
    NextBlockPanelController holdBox;
    public static final int PANEL_WIDTH = 100;
    public static final int PANEL_HEIGHT = 600;

    /**
     * NextBlock과 HoldBlock을 보여주는 Box를 담당하는 클래스
     * @param playerPage 게임이 이루어지는 playerPage
     */
    public NextBlockPanel(PlayerPage playerPage){
        this.playerPage = playerPage;
        this.next1Box = new NextBlockPanelController(this);
        this.next2Box = new NextBlockPanelController(this);
        this.next3Box = new NextBlockPanelController(this);
        this.holdBox = new NextBlockPanelController(this);
    }

    /**
     * nextBlockPanel 초기화
     */
    public void init(){
        next1Box.init();
        next2Box.init();
        next3Box.init();
        holdBox.init();

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

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

    /**
     * next블럭이나 hold블럭이 바뀌면 업데이트
     */
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
