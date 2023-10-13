package kr.ac.jbnu.se.tetris.views.pages;

import kr.ac.jbnu.se.tetris.controllers.*;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.MainPageModel;
import kr.ac.jbnu.se.tetris.models.Member;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Random;


public class TimeAttackPage extends PlayerPage {
    private JPanel backgroundPanel;
    @Getter
    private JLabel scoreLable;
    @Getter
    private JLabel timeLimit;
    private JFrame gameClearFrame;
    private JFrame timeAttackPageFrame;
    private JButton goBackButton;
    @Getter
    private JLabel gameLevelLabel;
    private TimeAttackBoardController timeAttackBoardController;

    JLabel highScoreLabel;
    int highScore;
    @SneakyThrows
    public TimeAttackPage(Member member, KeyInput keyInput, Random rand){
        super();

        timeAttackPageFrame = new JFrame();
        timeAttackPageFrame.setSize(1280, 720);
        timeAttackPageFrame.setLayout(null);

        this.highScoreLabel = new JLabel("High Score : " + getHighScore());
        this.scoreLable = new JLabel("0");
        this.gameTimer = new JLabel("00 : 00"); //안씀
        this.goBackButton = new JButton("돌아가기");
        this.timeLimit = new JLabel("00 : 00");
        this.gameLevelLabel = new JLabel("Lv 1");

        scoreLable.setFont(new Font("Serif", Font.BOLD, 35));
        scoreLable.setForeground(Color.white);
        timeLimit.setFont(new Font("Serif", Font.BOLD, 35));    //
        timeLimit.setForeground(Color.white);   //
        gameLevelLabel.setFont(new Font("Serif", Font.BOLD, 40));
        gameLevelLabel.setForeground(Color.white);
        highScoreLabel.setFont(new Font("Serif", Font.BOLD, 35));
        highScoreLabel.setForeground(Color.white);

        this.timeAttackBoardController = new TimeAttackBoardController(this, rand);
        board = timeAttackBoardController;
        this.nextBlockPanelController = new NextBlockPanelController(this);
        this.board.start();
        board.setVisible(true);

        board.setPreferredSize(new Dimension(BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT));
        board.setBounds(515, 110, BOARD_SIZE_WIDTH, BOARD_SIZE_HEIGHT);
        scoreLable.setBounds(800,400,300, 100);
        timeLimit.setBounds(800, 300, 150, 100);
        //gameTimer.setBounds(900, 450, 150, 150);
        nextBlockPanelController.setBounds(415, 110, 100, 500);
        gameLevelLabel.setBounds(700, 10, 100, 100);
        highScoreLabel.setBounds(100, 0, 300, 100);

        timeAttackPageFrame.add(scoreLable);
        timeAttackPageFrame.add(timeLimit);
        timeAttackPageFrame.add(board);
        timeAttackPageFrame.add(nextBlockPanelController);
        timeAttackPageFrame.add(gameLevelLabel);
        timeAttackPageFrame.add(highScoreLabel);

        setSize(new Dimension(1280, 720));

        backgroundPanel = new JPanel(){ //배경 패널 설정
            public void paintComponent(Graphics g){
                g.drawImage(MainPageModel.getGameBackgroundImg().getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setBounds(0,0,1280,720);
        timeAttackPageFrame.add(backgroundPanel);

        timeAttackPageFrame.setVisible(true);
        timeAttackPageFrame.setResizable(false);
        timeAttackPageFrame.setLocationRelativeTo(null);

        timeAttackPageFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        timeAttackPageFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // 창이 닫힐 때 board 객체 정리
                board.getTimer().stop(); // 게임 루프 중지
                timeAttackBoardController.getReverseCountTimer().stop();
                timeAttackPageFrame.getContentPane().removeAll(); // 컴포넌트 제거
                timeAttackPageFrame.dispose(); // 창 닫기
            }
        });

        //키 입력 관련
        AdapterController adapterController = new AdapterController();
        timeAttackPageFrame.setFocusable(true);
        timeAttackPageFrame.addKeyListener(adapterController);
        adapterController.addList(new KeyInputController(keyInput,board));

    }
    public void gameClear(int score){
        if(highScore < score){
            replaceHighScore(score);
        }
        gameClearFrame = new JFrame();
        gameClearFrame.setSize(500,250);

        JLabel clearScore = new JLabel();
        clearScore.setFont(new Font("Serif", Font.BOLD, 35));

        clearScore.setBounds(150, 50, 300, 50);
        goBackButton.setBounds(100, 120, 300, 50);

        gameClearFrame.setLayout(null);
        gameClearFrame.add(clearScore);
        gameClearFrame.add(goBackButton);

        gameClearFrame.setVisible(true);
        clearScore.setText("Score : " + String.valueOf(score));
        buttonAction();

        gameClearFrame.setVisible(true);
        gameClearFrame.setLocationRelativeTo(null);


    }
    public void buttonAction(){
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeAttackPageFrame.dispose(); // 현재 프레임 닫기
                gameClearFrame.dispose(); // 다른 프레임도 닫기
            }
        });
    }
    @Override
    public void raiseGameOverFrame(){
        gameClear(Integer.parseInt(scoreLable.getText()));
    }

    public String getHighScore() throws FileNotFoundException {
        try {
            Reader reader= new FileReader("src/main/java/kr/ac/jbnu/se/tetris/data/highScore.json");
            JSONParser parser=new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String highScoreData;
            highScoreData = jsonObject.get("timeAttackHighScore").toString();
            highScore = Integer.parseInt(highScoreData);

        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
        return String.valueOf(highScore);
    }

    public void replaceHighScore(int newHighScore) {
        try {
            Reader reader = new FileReader("src/main/java/kr/ac/jbnu/se/tetris/data/highScore.json");
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            jsonObject.replace("timeAttackHighScore", newHighScore);

            // 파일에 데이터를 다시 쓰기
            try (FileWriter fileWriter = new FileWriter("src/main/java/kr/ac/jbnu/se/tetris/data/highScore.json")) {
                fileWriter.write(jsonObject.toJSONString());
            }
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }


}
