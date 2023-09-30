package kr.ac.jbnu.se.tetris.models;


import java.util.logging.Logger;

//키 입력 세팅 자체를 클래스로 만들고, 이걸 TetrisBoard클래스에 넣는 방향으로 구상변경
public class KeyInput {
    Logger logger= Logger.getLogger(KeyInput.class.getName());
    //각 동작마다의 키 바인딩.
    //세팅 UI를 따로 준비하고 거기서 플레이어가 변경하도록 만들것.
    private int rotateRight;
    private int rotateLeft;
    private int moveRight;
    private int moveLeft;
    private int dropDown;
    private int pause;
    private int blockHold;


    public KeyInput() {
    }

    public KeyInput(int rotateRight, int rotateLeft, int moveRight, int moveLeft,
                    int dropDown, int pause, int blockHold) {
        this.rotateRight = rotateRight;
        this.rotateLeft = rotateLeft;
        this.moveRight = moveRight;
        this.moveLeft = moveLeft;
        this.dropDown = dropDown;
        this.pause = pause;
        this.blockHold = blockHold;

        logger.info("rotate"+rotateLeft+rotateRight+dropDown);
    }

    public int getRotateRight() {
        return rotateRight;
    }

    public void setRotateRight(char rotateRight) {
        this.rotateRight = rotateRight;
    }

    public int getRotateLeft() {
        return rotateLeft;
    }

    public void setRotateLeft(char rotateLeft) {
        this.rotateLeft = rotateLeft;
    }

    public int getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(char moveRight) {
        this.moveRight = moveRight;
    }

    public int getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(char moveLeft) {
        this.moveLeft = moveLeft;
    }

    public int getDropDown() {
        return dropDown;
    }

    public void setDropDown(char dropDown) {
        this.dropDown = dropDown;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(char pause) {
        this.pause = pause;
    }

    public int getBlockHold() {
        return blockHold;
    }

    public void setBlockHold(char blockHold) {
        this.blockHold = blockHold;
    }
}
