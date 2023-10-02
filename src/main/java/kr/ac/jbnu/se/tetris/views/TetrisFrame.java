package kr.ac.jbnu.se.tetris.views;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class TetrisFrame extends JFrame {
    private PlayerPage playerPage;


    public TetrisFrame() {
    }

    public void init() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 800));

        KeyInput p1Key = new KeyInput('w','s','d','a',(char)(KeyEvent.VK_SPACE),'p','h');
        playerPage = new PlayerPage(new Member(),p1Key);
        add(playerPage,BorderLayout.CENTER);

        playerPage.init();

        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

}