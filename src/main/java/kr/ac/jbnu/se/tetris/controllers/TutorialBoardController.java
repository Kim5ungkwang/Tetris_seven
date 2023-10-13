package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.controllers.pages.TutorialPage;
import kr.ac.jbnu.se.tetris.models.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static kr.ac.jbnu.se.tetris.models.TutorialModel.*;

public class TutorialBoardController extends BoardController {
    @Getter
    final private TutorialModel tutorialModel;

    TutorialPage tutorialPage;
    private Timer stepTimer;
    @Getter
    public int pieceFixedCount = 0;
    @Getter
    public boolean tutorialFinishedCalled = false;
    Random random = new Random();
    public TutorialBoardController(TutorialPage parent, KeyInput input){
        super();
        this.boardModel = new BoardModel();
        this.tutorialPage = parent;
        playerPage = tutorialPage;
        gameTimerController = new GameTimerController(this);

        this.statusBar = parent.getStatusBar();
        this.pieceController = new TutorialPieaceController(this);
        this.boardModel.setLoopDelay(1000);  //루프 딜레이 설정 400

        this.timer = new Timer(boardModel.getLoopDelay(), this);


        stepTimer = new Timer(300, new ActionListener() {  //3000
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextStep();
            }
        });

        this.tutorialModel = new TutorialModel();

    }

    public void startTutorial() {
        tutorialModel.resetCurrentStepIndex();
        displayCurrentStep();
        stepTimer.start();
        numLinesRemoved = 0;
    }
    @Override
    public void start(){
        super.start();
        pause();
        startTutorial();
        //drawBackgroundblock();
    }
    @Override
    protected void removeFullLines(){
        int numFullLines = 0;

        for (int i = BoardModel.getBoardHeight() - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardModel.getBoardWidth(); ++j) {
                if (shapeAt(j, i) == ShapeData.Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardModel.getBoardHeight() - 1; ++k) {
                    for (int j = 0; j < BoardModel.getBoardWidth(); ++j)
                        boardModel.setboard((k * BoardModel.getBoardWidth()) + j, shapeAt(j, k + 1));
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            pieceController.setIsFallingFinished(true);
            pieceController.getCurrentPiece().setPieceShape(ShapeData.Tetrominoes.NoShape);
            repaint();
        }
        if (numLinesRemoved == 3) {
            do{
                tutorialPage.tutorialFinished();
            }while (tutorialPage.isTutorialEndFrame() == false && tutorialPage.isTutorialPageFrame() == true);
        }
    }

    private void displayCurrentStep() {
        if (tutorialModel.getCurrentStepIndex() >= 0 && tutorialModel.getCurrentStepIndex() < tutorialSteps.length) {
            String currentStepText = tutorialSteps[tutorialModel.getCurrentStepIndex()];
            tutorialPage.getTutorialStep().setText(currentStepText);
        }
    }

    public void moveToNextStep() {
        if (tutorialModel.getCurrentStepIndex() < tutorialSteps.length - 1 && tutorialModel.getCurrentStepIndex() != 5) {
            tutorialModel.plusCurrnetStepIndex();
            displayCurrentStep(); // 다음 튜토리얼을 게임 보드에 표시
        }
        if(tutorialModel.getCurrentStepIndex() == 4){
            pieceFixedCount = 0;
            pause();
            drawBackgroundblock();
            stepTimer.stop();
        }

    }

    private void drawBackgroundblock() {
        for (int i = 0; i < BoardModel.getBoardHeight() * BoardModel.getBoardWidth(); ++i)
            getBoardModel().setboard(i, ShapeData.Tetrominoes.NoShape);
        for (int x = 3; x < BoardModel.getBoardWidth(); x++) {
            for (int y = 0; y < 12; y++) {
                getBoardModel().setboard(x + y * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.TutorialBackground);
                getBoardModel().setboard(3 + 11 * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.NoShape);
            }
        }
    }

    /*@Override
    public void pieceDropped(Piece droppedPiece){
        getPieceFixedCount();
        /*System.out.println("PieceFixedCount: " + getPieceFixedCount());
        pieceFixedCount++;
        //getPieceFixedCount();
        for (int i = 0; i < 4; i++) {
            int x = droppedPiece.getCurrentX() + droppedPiece.getCoordinates().x(i);
            int y = droppedPiece.getCurrentY() - droppedPiece.getCoordinates().y(i);
            boardModel.setboard((y * BoardModel.getBoardWidth()) + x, droppedPiece.getPieceShape());
        }

        removeFullLines();  //고정한 이후 지울 수 있는 줄이 있는지 확인

        if (!pieceController.getIsFallingFinished())
            pieceController.newPiece();

    }
        */

    /**
     * 튜토리얼 리셋 메서드
     */
    public void resetTutorial(){
        clearBoard();
        TutorialPieaceController tutorialPieaceController = (TutorialPieaceController) pieceController; //타입캐스팅
        tutorialPieaceController.resetBrickQueue();
    }
}
