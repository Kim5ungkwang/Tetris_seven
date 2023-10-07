package kr.ac.jbnu.se.tetris.views;


import kr.ac.jbnu.se.tetris.controllers.AdapterController;
import kr.ac.jbnu.se.tetris.controllers.BoardController;
import kr.ac.jbnu.se.tetris.controllers.KeyInputController;
import kr.ac.jbnu.se.tetris.models.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;


public class TetrisBoard extends JPanel implements ActionListener {
    Logger logger=Logger.getLogger(TetrisBoard.class.getName());

    private JLabel statusBar;

    private BoardController boardcontroller;

    TetrisBoard(PlayerPage parent, KeyInput input) {
        setFocusable(true);
        boardcontroller = new BoardController(this);

        statusBar = parent.getStatusBar();
        addKeyListener(AdapterController.adapterController);
        AdapterController.adapterController.addList(new KeyInputController(input, boardcontroller));
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
        boardcontroller.paintHelper(g, getSize().getWidth(), getSize().getHeight());

    }

    /**
     * 블록 하나를 그리는 메서드이다
     * isGhost는 GhostPiece 여부를 확인하는 변수이다.
     */

    public int toLower(int x){
        if(x>64){
            char cx=(char)x;
            cx=Character.toLowerCase(cx);
            x=cx;
        }

        return x;
    }

    public void setStatusText(String text) {
        statusBar.setText(text);
    }



}
