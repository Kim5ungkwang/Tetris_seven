package kr.ac.jbnu.se.tetris.controllers.pages;

import kr.ac.jbnu.se.tetris.controllers.AdapterController;
import kr.ac.jbnu.se.tetris.controllers.KeyInputController;
import kr.ac.jbnu.se.tetris.controllers.PlayerPage;
import kr.ac.jbnu.se.tetris.controllers.TetrisFrameController;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class LocalTwoPlayPage extends TetrisFrameController {

    private PlayerPage playerPage1;
    private PlayerPage playerPage2;
    private int sizeX=1280;
    private int sizeY=720;

    private Member player1;
    private Member player2;
    KeyInput p1Key;
    KeyInput p2Key;

    public LocalTwoPlayPage(Member player1, KeyInput p1Key, Random p1Rand, Member player2, KeyInput p2Key, Random p2Rand) {
        this.p1Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player1key.json");
        this.p2Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player2key.json");

        Member p1= new Member();
        Member p2=new Member();
        setLayout(null);
        //플레이어1은 왼쪽으로, 플레이어2는 오른쪽에 배치
        playerPage1=new PlayerPage(p1,p1Key,p1Rand);
        playerPage2=new PlayerPage(p2,p2Key,p2Rand);

        playerPage1.setBounds(185, 110, 300, 500);
        playerPage2.setBounds(845, 110, 300, 500);

        add(playerPage1);
        add(playerPage2);
        //각 창의 타이틀에 각 플레이어의 이름을 배치
        //playerPage1.init();
        //playerPage2.init();

        setPreferredSize(new Dimension(sizeX, sizeY));
        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel(){ //배경 패널 설정
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getGameBackgroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setBounds(0,0,1280,720);
        add(backgroundPanel);

        AdapterController adapterController = new AdapterController();
        setFocusable(true);
        addKeyListener(adapterController);
        adapterController.addList(new KeyInputController(p1Key,playerPage1.getBoard()));
        adapterController.addList(new KeyInputController(p2Key, playerPage2.getBoard()));

    }
}