package kr.ac.jbnu.se.tetris.views;


import kr.ac.jbnu.se.tetris.controllers.AdapterController;
import kr.ac.jbnu.se.tetris.controllers.BoardController;
import kr.ac.jbnu.se.tetris.controllers.KeyInputController;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;


public class TetrisBoard extends JPanel implements ActionListener {
    Logger logger=Logger.getLogger(TetrisBoard.class.getName());

    final private JLabel statusBar;
    @Getter
    final private PlayerPage playerPage;

    @Getter
    final private BoardController boardcontroller;

    TetrisBoard(PlayerPage parent, KeyInput input) {
        setFocusable(true);
        boardcontroller = new BoardController(this);
        this.playerPage = parent;
        statusBar = parent.getStatusBar();
        addKeyListener(AdapterController.adapterController);
        AdapterController.adapterController.addList(new KeyInputController(input, boardcontroller));
    }

    public void init(){
        boardcontroller.init();
        setBackground(new Color(95, 95, 95));   //배경색 일단 회색
    }

    void start() {
        boardcontroller.start();
    }

    //딜레이 마다 처리될 연산
    @Override
    public void actionPerformed(ActionEvent e) {
        boardcontroller.gameAction();    //시간마다 테트로미노가 떨어짐
     }

    //보드를 그리는 메서드
    public void paint(Graphics g) {
        super.paint(g);
        try {
            boardcontroller.paintHelper(g, getSize().getWidth(), getSize().getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public int toLower(int x){
        if(x>64){
            char cx=(char)x;
            cx=Character.toLowerCase(cx);
            x=cx;
        }

        return x;
    }

    public static void setStatusText(String text) {
        statusBar.setText(text);
    }
}
