package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.BrickRotator;
import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.*;
import lombok.Getter;

/**
 * 움직이는 piece를 관리하는 클래스
 */
public class PieceController {
    protected BoardController boardController;  //게임이 이루어지는 보드
    @Getter
    protected Piece currentPiece;   //지금 움직이고 있는 피스객체
    @Getter
    protected Piece holdPiece;  //hold 상태인 피스를 저장하는 피스객체
    @Getter
    protected GhostPiece ghostPiece;    //사용자가 움직이고 있는 피스의 고스트 객체
    @Getter
    protected BrickQueueManager brickQueueManager;  //새로운 블럭을 생성할때 쓰는 brickQueueManager
    private NextBlockPanelController nextBlockPanelController;  //다음 피스와 hold중인 피스를 그리는 NextBlockPanelController
    static boolean isFallingFinished = false;   //떨어지는 상태인지 확인
    protected boolean isHolding = false;    //hold중인 상태인지 확인 -> hold를 반복하는 것을 방지
    private boolean isInfinity; //인피니티 체크 인피니티는 블록이 바닥에 닿아도 일정시간 움직일 수 있는 기능이다.

    /**
     * PieceController 생성자
     * @param boardController 게임이 진행되는 boardController
     */
    public PieceController(BoardController boardController){
        this.boardController = boardController;
        this.currentPiece = new Piece();
        this.ghostPiece = new GhostPiece(this);
        this.brickQueueManager = new BrickQueueManager(boardController.getRand());
        this.holdPiece = new Piece();
    }

    public PieceController() {
        //상속용
    }

    ///////////////////////////////////////////////////////////////////

    public void init(){
        setNextBlockPanel();
    }   //pieceControlelr와 nextBlockBox를 연결하기 위함

    public BoardController getBoardController(){
        return boardController;
    }

    public int getBoardWidth(){
        return BoardModel.getBoardWidth();
    }

    public int getBoardHeight(){
        return BoardModel.getBoardHeight();
    }

    public boolean getIsFallingFinished(){
        return isFallingFinished;
    }

    public boolean getIsInfinity(){
        return isInfinity;
    }


    ////////////////////////////////////////////////////////////////////

    /**
     * 의존성 주입
     */
    public void setNextBlockPanel(){
        nextBlockPanelController = boardController.getPlayerPage().getNextBlockPanelController();
    }

    /**
     * isFallingFinished를 초기화하는 메서드
     * @param isFallingFinished true, false
     */
    public void setIsFallingFinished(boolean isFallingFinished){
        this.isFallingFinished = isFallingFinished;
    }

    public void setIsInfinity(boolean isInfinity){
        this.isInfinity = isInfinity;
    }

    ////////////////////////////////////////////////////////////////////

    /**
     * 인피니티를 적용한 currentPiece를 한칸 아래로 떨어뜨리는 메서드
     */
    public void oneLineDown()
    {
        if(isInfinity && !tryMove(currentPiece, currentPiece.getCurrentX(), currentPiece.getCurrentY() - 1)) {
            pieceDropped();
        }
        else if (!tryMove(currentPiece, currentPiece.getCurrentX(), currentPiece.getCurrentY() - 1)) {
            isInfinity = true;  //바닥에 붙은 상태에서 한번 더 기회를 준다.
            return;
        }

        isInfinity = false; //한칸 아래로 내려갈 수 있다면 인피니티는 초기화 -> 한번 더 가능
    }

    /**
     * currentPiece를 하드드롭 시키는 메서드
     */
    public void dropDown()
    {
        if(isInfinity)
            return;
        int newY = currentPiece.getCurrentY();
        while (newY > 0){
            if (!tryMove(currentPiece, currentPiece.getCurrentX(), newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    /**
     * Piece가 해당 위치로 움직였을때 유효한지 체크하는 메서드
     * 움직임이 유효하다면 currentPiece가 newPiece로 초기화되기 때문에 고려해서 newPiece 객체를 넘겨주어야한다.
     * @param newPiece newX와 newY위치에 들어갈 Piece
     * @param newX 새로운 좌표 X
     * @param newY 새로운 좌표 Y
     * @return 움직임이 유효하다면 true, 그렇지 않다면 false
     */
    public boolean tryMove(Piece newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.getCoordinates().x(i);
            int y = newY - newPiece.getCoordinates().y(i);
            if (x < 0 || x >= BoardModel.getBoardWidth() || y < 0 || y >= BoardModel.getBoardHeight())
                return false;
            if (boardController.shapeAt(x, y) != ShapeData.Tetrominoes.NoShape)
                return false;
        }
        currentPiece = newPiece;
        currentPiece.setCurrentX(newX);
        currentPiece.setCurrentY(newY);
        ghostPiece.updateGhostPiece();
        boardController.repaint();
        return true;
    }

    /**
     * currentPiece를 board에 고정하는 메서드
     */
    public void pieceDropped()
    {
        boardController.pieceDropped(currentPiece);
    }

    /**
     * 블럭 hold 메서드
     */
    public void holdingPiece() throws CloneNotSupportedException {
        if (!isHolding) {
            Piece holdPieceTmp = holdPiece.clone();
            holdPiece = currentPiece.clone();

            if (holdPieceTmp.getPieceShape() == ShapeData.Tetrominoes.NoShape) {
                newPiece();
                boardController.repaint();
            } else {
                currentPiece = holdPieceTmp.clone();
                currentPiece.setCurrentX(BoardModel.getBoardWidth() / 2 + 1);
                currentPiece.setCurrentY(BoardModel.getBoardHeight() - 1 + currentPiece.getCoordinates().minY());
                boardController.repaint();
            }
            isHolding = true;
            ghostPiece.updateGhostPiece();
            nextBlockPanelController.NextBlockPanelUpdate();
            nextBlockPanelController.repaint();
        }
    }

    /**
     * currentPiece를 한칸 인쪽으로 이동
     */
    public void moveLeft() {
        tryMove(currentPiece, currentPiece.getCurrentX() - 1, currentPiece.getCurrentY());
    }

    /**
     * currentPiece를 한칸 오른쪽으로 이동
     */
    public void moveRight() {
        tryMove(currentPiece, currentPiece.getCurrentX() + 1, currentPiece.getCurrentY());
    }

    /**
     * SRS를 적용한 블록 왼쪽 회전 메서드
     */
    public void rotateLeft(){
        BrickRotator.rotateLeft(this);
        System.out.println("RotateIndex 값: "+currentPiece.getRotateIndex());
    }

    /**
     * SRS를 적용한 블록 오른쪽 회전 메서드
     */
    public void rotateRight(){
        BrickRotator.rotateRight(this);
        System.out.println("RotateIndex 값: "+currentPiece.getRotateIndex());
    }

    /**
     * roateLeft()메서드를 사용하기 위해 currnetPiece의 coordinate를 바꾸는 메서드
     * 새로운 piece객체를 return하기 때문에 주의해야한다.
     * @return 회전된 coordinate가 적용된 새로운 Piece객체
     */
    public Piece rotateLeftHelper() {
        if (currentPiece.getPieceShape() == ShapeData.Tetrominoes.SquareShape)
            return currentPiece;

        Piece result = new Piece();
        result.setRotateIndex(currentPiece.getRotateIndex());
        result.setPieceShape(currentPiece.getPieceShape());

        for (int i = 0; i < 4; ++i) {
            result.getCoordinates().setX(i, currentPiece.getCoordinates().y(i));
            result.getCoordinates().setY(i, -currentPiece.getCoordinates().x(i));
        }
        return result;
    }


    /**
     * roateRight()메서드를 사용하기 위해 currnetPiece의 coordinate를 바꾸는 메서드
     * 새로운 piece객체를 return하기 때문에 주의해야한다.
     * @return 회전된 coordinate가 적용된 새로운 Piece객체
     */
    public Piece rotateRightHelper() {
        if (currentPiece.getPieceShape() == ShapeData.Tetrominoes.SquareShape)
            return currentPiece;

        Piece result = new Piece();
        result.setRotateIndex(currentPiece.getRotateIndex());
        result.setPieceShape(currentPiece.getPieceShape());

        for (int i = 0; i < 4; ++i) {
            result.getCoordinates().setX(i, -currentPiece.getCoordinates().y(i));
            result.getCoordinates().setY(i, currentPiece.getCoordinates().x(i));
        }
        return result;
    }

    /**
     * 보드의 해당 좌표에 어떤 Tetrominoes가 들어있는지 확인하는 메서드
     * @param x board의 x값
     * @param y board의 y값
     * @return board에 들어있는 Tetrominoes 값
     */
    public ShapeData.Tetrominoes shapeAt(int x, int y){
        return boardController.shapeAt(x, y);
    }

    /**
     *새로운 Piece객체 생성 후 해당 Piece객체를 board좌표 맨 위로 올린다.
     */
    public void newPiece(){
        isHolding = false;
        currentPiece = new Piece();
        currentPiece.setPieceShape(brickQueueManager.getNewShape());
        nextBlockPanelController.NextBlockPanelUpdate();    //brickQueueManager에서 새로운 블럭을 받아온 후 다음 블럭들을 패널에 갱신
        nextBlockPanelController.repaint();
        currentPiece.setCurrentX(BoardModel.getBoardWidth() / 2 + 1);
        currentPiece.setCurrentY(BoardModel.getBoardHeight() - 1 + currentPiece.getCoordinates().minY());
        currentPiece.setRotateIndex(0);

        if (!tryMove(currentPiece, currentPiece.getCurrentX(), currentPiece.getCurrentY())) {
            currentPiece.setPieceShape(ShapeData.Tetrominoes.NoShape);
            boardController.getTimer().stop();
            boardController.getBoardModel().setIsStarted(false);
            boardController.gameOver();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
}
