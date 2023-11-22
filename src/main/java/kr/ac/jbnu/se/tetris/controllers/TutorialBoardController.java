package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.views.pages.TutorialPage;
import kr.ac.jbnu.se.tetris.models.*;
import lombok.Getter;

import javax.swing.*;
import java.util.Random;
import static kr.ac.jbnu.se.tetris.models.TutorialModel.*;

public class TutorialBoardController extends BoardController {
    @Getter
    private final transient TutorialModel tutorialModel;

    private final TutorialPage tutorialPage;
    private final Timer stepTimer;
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
        this.pieceController = new TutorialPieceController(this);
        this.boardModel.setLoopDelay(600);  //루프 딜레이 설정 400

        this.timer = new Timer(boardModel.getLoopDelay(), this);


        //3000
        //시간에 따라 튜토리얼 다음 단계로 넘어감
        stepTimer = new Timer(10000, e -> moveToNextStep());

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
        /*int numFullLines = 0;

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
        }*/
        super.removeFullLines();

        if (numLinesRemoved == 9) {
            //do{
            //    tutorialPage.tutorialFinished();
            //}while (tutorialPage.isTutorialEndFrame() == false && tutorialPage.isTutorialPageFrame() == true);
            timer.stop();
            tutorialPage.tutorialFinished();
        }
    }

    //현재 튜토리얼 단계에 대한 텍스트를 보여줌
    private void displayCurrentStep() {
        if (tutorialModel.getCurrentStepIndex() >= 0 && tutorialModel.getCurrentStepIndex() < getTutorialSteps().length) {
            String currentStepText = getTutorialSteps()[tutorialModel.getCurrentStepIndex()];
            tutorialPage.getTutorialStep().setText(currentStepText);
        }
    }

    //튜토리얼의 다음 단계로 넘어감
    public void moveToNextStep() {
        if (tutorialModel.getCurrentStepIndex() < getTutorialSteps().length - 1) {
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

    //SRS 수행에 필요한 배경블럭 그리기
    private void drawBackgroundblock() {
        clearBoard();
        for (int x = 3; x < BoardModel.getBoardWidth(); x++) {
            for (int y = 0; y < 12; y++) {
                getBoardModel().setboard(x + y * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.TutorialBackground);
                getBoardModel().setboard(3 + 11 * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.NoShape);
            }
        }
    }
    /**
     * 튜토리얼 리셋 메서드
     */
    public void resetTutorial(){
        clearBoard();
        TutorialPieceController tutorialPieceController = (TutorialPieceController) pieceController; //타입캐스팅
        tutorialPieceController.resetBrickQueue();
        drawBackgroundblock();
        pieceController.newPiece();
    }
}
