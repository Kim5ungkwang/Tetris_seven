package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.models.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//일반적인 메뉴를 포함한, 게임 페이즐 제외한 페이지
public class Page extends JFrame {private JLabel statusBar;

    JPanel Board;

    public Page() {
    }

    public void main(){

    }



    JLabel getStatusBar() {
        return statusBar;
    }
}
