package kr.ac.jbnu.se.tetris.views;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class TetrisFrame extends JFrame {

    private PlayerPage playerPage;


    public TetrisFrame() {
    }

    public void init() {
        KeyInput p1Key = new KeyInput('w','s','d','a',(char)(KeyEvent.VK_SPACE),'p','h');
        setLayout(new BorderLayout());
        setSize(200, 400);
        setPreferredSize(new Dimension(200, 400));
        playerPage=new PlayerPage(new Member(),p1Key);
        add(playerPage,BorderLayout.CENTER);
        playerPage.init();
        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);

    }

}