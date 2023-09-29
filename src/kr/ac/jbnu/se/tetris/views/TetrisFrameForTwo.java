package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TetrisFrameForTwo extends TetrisFrame {
    private TetrisBoard tetrisBoard1;
    private TetrisBoard tetrisBoard2;
    private int sizeX=400;
    private int sizeY=400;

    private Member player1;
    private Member player2;

    public TetrisFrameForTwo() {


        KeyInput p1Key = new KeyInput('w','s','d','a',(char)(KeyEvent.VK_SPACE),'p','h');
        KeyInput p2Key = new KeyInput('i','k','l','j','o','p','i');


        tetrisBoard1 =new TetrisBoard(this,p1Key);
        tetrisBoard2 =new TetrisBoard(this,p2Key);


    }
    public void init(){
        setLayout(new GridLayout(1,2));
        //플레이어1은 왼쪽으로, 플레이어2는 오른쪽에 배치
        add(tetrisBoard1, BorderLayout.WEST);
        add(tetrisBoard2, BorderLayout.EAST);
        //각 창의 타이틀에 각 플레이어의 이름을 배치
        tetrisBoard1.start();
        tetrisBoard2.start();

        setSize(sizeX, sizeY);
        setPreferredSize(new Dimension(sizeX, sizeY));
        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }
}