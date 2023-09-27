package kr.ac.jbnu.se.tetris.models;

import java.awt.event.KeyEvent;

//키 입력 세팅 자체를 클래스로 만들고, 이걸 TetrisBoard클래스에 넣는 방향으로 구상변경
public class KeyInput{
    //각 동작마다의 키 바인딩.
    //세팅 UI를 따로 준비하고 거기서 플레이어가 변경하도록 만들것.
    private KeyEvent rotateRight;
    private KeyEvent rotateLeft;
    private KeyEvent moveRight;
    private KeyEvent moveLeft;

    private KeyEvent pause;
    private KeyEvent blockHold;


    public KeyInput() {
    }

    public KeyEvent getRotateRight() {
        return rotateRight;
    }

    public void setRotateRight(KeyEvent rotateRight) {
        this.rotateRight = rotateRight;
    }

    public KeyEvent getRotateLeft() {
        return rotateLeft;
    }

    public void setRotateLeft(KeyEvent rotateLeft) {
        this.rotateLeft = rotateLeft;
    }

    public KeyEvent getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(KeyEvent moveRight) {
        this.moveRight = moveRight;
    }

    public KeyEvent getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(KeyEvent moveLeft) {
        this.moveLeft = moveLeft;
    }

    public KeyEvent getPause() {
        return pause;
    }

    public void setPause(KeyEvent pause) {
        this.pause = pause;
    }

    public KeyEvent getBlockHold() {
        return blockHold;
    }

    public void setBlockHold(KeyEvent blockHold) {
        this.blockHold = blockHold;
    }
}
