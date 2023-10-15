package kr.ac.jbnu.se.tetris.views.pages;

import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Member;
import kr.ac.jbnu.se.tetris.models.Rank;
import kr.ac.jbnu.se.tetris.service.WebSocketService;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

/**
 * mainPage에서 멀티플레이버튼을 눌렀을 때 나오는 페이지를 관리하는 메서드
 */
public class MutliPlayPage extends JPanel{
    static public final int BUTTON_WIDTH = 245;
    static public final int BUTTON_HEIGHT = 70;
    static public final int BUTTON_X = 518;
    static public final int BUTTON_Y = 290;
    @Getter
    private JPanel buttonPanel;
    private final MainPage mainPage;
    @Getter
    static ImageIcon localPlayImg, onlineImg, undoImg,rankingImg;
    @Getter
    JButton localPlayBt, onlineBt, undoBt,rankingBt;
    KeyInput p1Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player1key.json");
    KeyInput p2Key = new KeyInput("src/main/java/kr/ac/jbnu/se/tetris/data/player2key.json");
    Random p1Rand;
    Random p2Rand;

    public MutliPlayPage(MainPage parent){
        this.mainPage = parent;
        setLayout(null);
        setSize(1280, 720);
        buttonPanel = new JPanel();
        buttonInit();
        buttonPanelInit();
        setBackground(new Color(0,0,0, 153));
        setVisible(false);

        double x= Math.random()*10000;
        p1Rand= new Random((long) x);
        p1Rand.nextInt();

        double y= Math.random()*10000;
        p2Rand= new Random((long) y);
        p2Rand.nextInt();
    }

    /**
     * 버튼이 들어갈 buttonPanel을 초기화하고 패널에 추가하는 메서드
     */
    public void buttonPanelInit(){
        buttonPanel.setLayout(null);
        buttonPanel.setSize(1280, 600);
        buttonPanel.setBounds(0, 120,1280, 600);
        buttonPanel.setBackground(new Color(0,0,0, 168));
        add(buttonPanel);
        buttonPanel.setVisible(true);
    }

    /**
     * 버튼 모양과 위치를 설정하고 buttonPanel에 추가하는 메서드
     */
    public void buttonInit(){
        localPlayImg = new ImageIcon("source/image/button/localplay.png");
        onlineImg = new ImageIcon("source/image/button/onlineplay.png");
        rankingImg= new ImageIcon("source/image/button/rankingImg.png");
        undoImg = new ImageIcon("source/image/button/undo.png");

        localPlayBt = new JButton(localPlayImg);
        onlineBt = new JButton(onlineImg);
        rankingBt= new JButton(rankingImg);
        undoBt = new JButton(undoImg);

        settingButton(localPlayBt);
        settingButton(onlineBt);

        localPlayBt.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH,BUTTON_HEIGHT);
        onlineBt.setBounds(BUTTON_X, BUTTON_Y + BUTTON_HEIGHT + 30, BUTTON_WIDTH, BUTTON_HEIGHT);
        rankingBt.setBounds(BUTTON_X,BUTTON_Y+ BUTTON_HEIGHT+130,BUTTON_WIDTH,BUTTON_HEIGHT);
        undoBt.setBounds(0, 120, BUTTON_WIDTH,BUTTON_HEIGHT);

        add(localPlayBt);
        add(onlineBt);
        add(rankingBt);
        add(undoBt);

        buttonAction();
    }
    /**
     * 버튼을 눌렀을 때 실행되는 action을 설정힌 메서드
     */
    public void settingButton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    public void buttonAction(){
        undoBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPage.getMainPagePanel().setVisible(true);
            }
        });
        onlineBt.addActionListener(new ActionListener() {
            int i=0;
            @Override
            public void actionPerformed(ActionEvent e) {
                while(true){
                    if(WebSocketService.getInstance().connectTwo()){
                        i=1;
                        break;
                    }
                    int val = JOptionPane.showConfirmDialog(null, "waiting", "waiting", JOptionPane.DEFAULT_OPTION);
                    if (val == -1 || val == JOptionPane.YES_OPTION){
                        WebSocketService.getInstance().notIn();
                        break;
                    }


                }
                if(i==1){
                    WebSocketService.getInstance().startMatching();

                    MultiTwoPlayPage multiTwoPlayPage=new MultiTwoPlayPage(p1Key,p2Key);
                    multiTwoPlayPage.setVisible(true);
                    System.out.println("match success");
                }


                // 온라인 모드

            }
        });
        localPlayBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTwoPlayPage localTwoPlayPage = new LocalTwoPlayPage(new Member(), p1Key, p1Rand, new Member(), p2Key, p2Rand);
                localTwoPlayPage.setVisible(true);
            }
        });

        rankingBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Rank> list = WebSocketService.getInstance().ranking();
                String rank="               <<ranking>>   \n";
                for(int i=0;i<list.size();i++){
                    rank=rank+"   "+ list.get(i).getId()+"    |    "+list.get(i).getRank()+"    |    "+list.get(i).getScore()+"\n";
                    if(i==9){
                        i=list.size();
                    }
                }
                JOptionPane.showMessageDialog
                        (null, rank);
            }
        });


    }
}





