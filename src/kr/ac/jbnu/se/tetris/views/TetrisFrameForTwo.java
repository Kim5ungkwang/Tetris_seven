package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;

import javax.swing.*;
import java.awt.*;

public class TetrisFrameForTwo extends TetrisFrame {
    private TetrisBoard tetrisBoard1;
    private TetrisBoard tetrisBoard2;
    private int sizeX=400;
    private int sizeY=400;

    private Member player1;
    private Member player2;

    public TetrisFrameForTwo() {
        //더미 플레이어 생성
        Member p1= new Member();
        Member p2= new Member();
        p1.setId("플레이어1");
        p2.setId("플레이어2");

        player1=p1;
        player2=p2;
        tetrisBoard1 =new TetrisBoard(this);
        tetrisBoard2 =new TetrisBoard(this);


    }
    public void init(){
        setLayout(new GridLayout(1,2));
        //플레이어1은 왼쪽으로, 플레이어2는 오른쪽에 배치
        add(tetrisBoard1, BorderLayout.WEST);
        add(tetrisBoard2, BorderLayout.EAST);
        //각 창의 타이틀에 각 플레이어의 이름을 배치


        setSize(sizeX, sizeY);
        setPreferredSize(new Dimension(sizeX, sizeY));
        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }
}