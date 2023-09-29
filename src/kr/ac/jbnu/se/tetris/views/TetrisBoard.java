package kr.ac.jbnu.se.tetris.views;


import kr.ac.jbnu.se.tetris.controllers.AdapterController;
import kr.ac.jbnu.se.tetris.controllers.BoardController;
import kr.ac.jbnu.se.tetris.controllers.GhostPiece;
import kr.ac.jbnu.se.tetris.controllers.KeyInputController;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;


public class TetrisBoard extends JPanel implements ActionListener {
    Logger logger=Logger.getLogger(TetrisBoard.class.getName());

    static public final int BOARD_WIDTH = 10;
    static public final int BOARD_HEIGHT = 22;
    private JLabel statusBar;

    private BoardController controller;

    TetrisBoard(PlayerPage parent, KeyInput input) {
        setFocusable(true);
        controller = new BoardController(BOARD_WIDTH, BOARD_HEIGHT, this);

        statusBar = parent.getStatusBar();
        addKeyListener(AdapterController.adapterController);
        AdapterController.adapterController.addList(new KeyInputController(input,controller));
    }

    void start() {
        controller.start();
    }

    //딜레이 마다 처리될 연산
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.gameAction();    //시간마다 테트로미노가 떨어짐
    }

    //보드를 그리는 메서드
    public void paint(Graphics g) {
        super.paint(g);
        controller.paint(g, getSize().getWidth(), getSize().getHeight());

    }

    private int squareWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int squareHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }
    /**
     * 블록 하나를 그리는 메서드이다
     * isGhost는 GhostPiece 여부를 확인하는 변수이다.
     */
    public void drawSquare(Graphics g, int x, int y, Shape.Tetrominoes shape, boolean isGhost)
    {
        Color colors[] = { new Color(0, 0, 0),
                new Color(204, 102, 102),
                new Color(102, 204, 102),
                new Color(102, 102, 204),
                new Color(204, 204, 102),
                new Color(204, 102, 204),
                new Color(102, 204, 204),
                new Color(218, 170, 0),
                new Color(255, 102, 102),   // 연한 빨강
                new Color(102, 255, 102),   // 연한 초록
                new Color(102, 102, 255),   // 연한 파랑
                new Color(255, 255, 102),   // 연한 노랑
                new Color(255, 102, 255),   // 연한 자주
                new Color(102, 255, 255),   // 연한 시안
                new Color(255, 204, 0)      // 연한 주황
        };

            Color color = colors[shape.ordinal()];

            if(isGhost){
                color = colors[shape.ordinal() + Shape.TETROMINOES_SIZE];   //GhostPiece라면 연한색으로 출력
            }

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

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
