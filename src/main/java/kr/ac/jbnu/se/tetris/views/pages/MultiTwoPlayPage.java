package kr.ac.jbnu.se.tetris.views.pages;



import kr.ac.jbnu.se.tetris.controllers.*;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;
import kr.ac.jbnu.se.tetris.service.MyWebSocketClient;
import kr.ac.jbnu.se.tetris.service.WebSocketService;
import kr.ac.jbnu.se.tetris.views.PlayerPage;
import kr.ac.jbnu.se.tetris.controllers.TwoPlayerBoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MultiTwoPlayPage extends LocalTwoPlayPage {

    private PlayerPage playerPage1;
    private PlayerPage playerPage2;
    private int sizeX=1280;
    private int sizeY=720;
    private JLabel player1SpeedLabel;
    private JLabel player2SpeedLable;
    private int player1Speed = 1;
    private int player2Speed = 1;
    private JLabel player1NumLinesRemovedCount;
    private JLabel player2NumLinesRemovedCount;
    private JFrame gameEndFrame;
    private Member player1;
    private Member player2;
    private JLabel player1Lable;
    private JLabel player2Lable;
    KeyInput p1Key;
    KeyInput p2Key;
    private JButton goBackButton;
    private String roomId;

    public MultiTwoPlayPage( KeyInput p1Key,  KeyInput p2Key) {
        super();
        player1= new Member();
        player2= new Member();

        WebSocketService.getInstance().startGame();
        MyWebSocketClient client= WebSocketService.getInstance().getClient();
        roomId=client.getRoomId();

        player1.setSessionId(client.getSender1());
        player2.setSessionId(client.getSender2());
        Random rand1=new Random((long) client.getSeed1());
        Random rand2=new Random((long) client.getSeed2());

        setLayout(null);
        //플레이어1은 왼쪽으로, 플레이어2는 오른쪽에 배치
        playerPage1=new PlayerPage(this, player1,p1Key,rand1, 1);
        playerPage2=new PlayerPage(this, player2,p2Key,rand2, 2);

        player1Lable = new JLabel("Player 1");
        player2Lable = new JLabel("Player 2");
        player1SpeedLabel = new JLabel("lv 1");
        player2SpeedLable = new JLabel("lv 1");
        player1SpeedLabel.setFont(new Font("Serif", Font.BOLD, 25));
        player2SpeedLable.setFont(new Font("Serif", Font.BOLD, 25));
        player1Lable.setFont(new Font("Serif", Font.BOLD, 25));
        player2Lable.setFont(new Font("Serif", Font.BOLD, 25));
        player1SpeedLabel.setForeground(Color.white);
        player2SpeedLable.setForeground(Color.white);
        player1Lable.setForeground(Color.white);
        player2Lable.setForeground(Color.white);
        this.goBackButton = new JButton("돌아가기");

        player1NumLinesRemovedCount = new JLabel("10");
        player2NumLinesRemovedCount = new JLabel("10");
        player1NumLinesRemovedCount.setFont(new Font("Serif", Font.BOLD, 25));
        player2NumLinesRemovedCount.setFont(new Font("Serif", Font.BOLD, 25));
        player1NumLinesRemovedCount.setForeground(Color.white);
        player2NumLinesRemovedCount.setForeground(Color.white);



        playerPage1.setBounds(185, 110, 300, 500);
        playerPage2.setBounds(845, 110, 300, 500);
        player1SpeedLabel.setBounds(185, 0, 300, 100);
        player2SpeedLable.setBounds(845, 0, 300, 100);
        player1NumLinesRemovedCount.setBounds(285, 0, 300, 100);
        player2NumLinesRemovedCount.setBounds(945, 0, 300, 100);
        player1Lable.setBounds(185, 40, 300, 100);
        player2Lable.setBounds(845, 40, 300, 100);

        add(playerPage1);
        add(playerPage2);
        add(player1SpeedLabel);
        add(player2SpeedLable);
        add(player1NumLinesRemovedCount);
        add(player2NumLinesRemovedCount);
        add(player1Lable);
        add(player2Lable);
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
        client.setController1(new MultiActionController(playerPage1.getBoard()));
        client.setController2(new MultiActionController(playerPage2.getBoard()));
        //adapterController.addList(new KeyInputController(p2Key, playerPage2.getBoard()));

    }
    public void init(){

    }


    public void reducePlayer2GameDelay(int reduceAmount){
        TwoPlayerBoardController rivalBoard = (TwoPlayerBoardController) playerPage2.getBoard();
        rivalBoard.reduceMyGameDelay(reduceAmount);
        player2Speed++;
        player2SpeedLable.setText("lv " + String.valueOf(player2Speed));
    }

    public void reducePlayer1GameDelay(int reduceAmount){
        TwoPlayerBoardController rivalBoard = (TwoPlayerBoardController) playerPage1.getBoard();
        rivalBoard.reduceMyGameDelay(reduceAmount);
        player1Speed++;
        player1SpeedLabel.setText("lv " + String.valueOf(player1Speed));
    }

    public void updatePlayer1NumLinesRemovedCount(String numLinesCount){
        player1NumLinesRemovedCount.setText(numLinesCount);
    }

    public void updatePlayer2NumLinesRemovedCount(String numLinesCount){
        player2NumLinesRemovedCount.setText(numLinesCount);
    }

    public void endGame(int loserNum){
        playerPage1.getBoard().pause();
        playerPage2.getBoard().pause();
        gameClear(loserNum);
    }

    public void gameClear(int loserNum){
        gameEndFrame = new JFrame();
        gameEndFrame.setSize(500,250);

        JLabel clearScore = new JLabel();
        clearScore.setFont(new Font("Serif", Font.BOLD, 35));

        clearScore.setBounds(100, 50, 500, 50);
        goBackButton.setBounds(100, 120, 300, 50);

        gameEndFrame.setLayout(null);
        gameEndFrame.add(clearScore);
        gameEndFrame.add(goBackButton);

        gameEndFrame.setVisible(true);
        if(loserNum == 1){
            clearScore.setText("winner is player 2");
        }else{
            clearScore.setText("winner is player 1");
        }
        buttonAction();

        gameEndFrame.setVisible(true);
        gameEndFrame.setLocationRelativeTo(null);
    }

    public void buttonAction(){
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEndFrame.dispose(); // 다른 프레임도 닫기
                dispose(); // 현재 프레임 닫기
            }
        });
    }
}