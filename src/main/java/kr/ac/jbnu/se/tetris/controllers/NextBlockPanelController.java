package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.views.PlayerPage;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * 홀드 블록과 다음 블록을 보여주는 박스를 관리하는 클래스
 */
public class NextBlockPanelController extends JPanel {
    static public int NEXT_BLOCK_PANEL_SIZE_WIDTH = 100;
    static public int NEXT_BLOCK_PANEL_SIZE_HEIGHT = 600;

    @Getter
    PlayerPage playerPage;
    NextBlockBox next1Box;
    NextBlockBox next2Box;
    NextBlockBox next3Box;
    NextBlockBox holdBox;

    /**
     * 4개의 NextBlockBox를 가지고있습니다
     * 각각의 상자는 hold 상자 1개와 next블럭을 보여주는 상자 3개로 입니다.
     * @param parent 보드와 함께 그려질 PlayerPage
     */
    public NextBlockPanelController(PlayerPage parent){
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
        setOpaque(true);
        setBackground(new Color(255, 255, 255));
        setVisible(true);
    }

    /**
     * hold블럭과 next블럭이 바뀔 때마다 호출하여
     * Box상태를 갱신하는 메서드 입니다.
     */
    public void NextBlockPanelUpdate(){
        holdBox.holdUpdate();
        next1Box.next1Update();
        next2Box.next2Update();
        next3Box.next3Update();
    }


    /**
     * 상자의 내용 그리고 상자를 담을 패널을 그리는 함수입니다.
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g){
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        NextBlockPanelUpdate();
        super.paint(g);
    }



}
