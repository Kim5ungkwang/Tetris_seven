package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.controllers.pages.TutorialPage;
import kr.ac.jbnu.se.tetris.models.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static kr.ac.jbnu.se.tetris.models.TutorialModel.tutorialSteps;

public class TutorialBoardController extends BoardController {
    @Getter
    final private TutorialModel tutorialModel;
    TutorialPage tutorialPage;
    private Timer stepTimer;
    @Getter
    @Setter
    public static int pieceFixedCount = 0;
    public static int numLinesRemoved = 0;
    public TutorialBoardController(TutorialPage parent, KeyInput input){
        super(parent, input);
        this.boardModel = new BoardModel();
        this.tutorialPage = parent;
        playerPage = tutorialPage;

        this.statusBar = parent.getStatusBar();
        this.pieceController = new TutorialPieaceController(this);


        this.boardModel.setLoopDelay(1000);  //루프 딜레이 설정 400

        this.timer = new Timer(boardModel.getLoopDelay(), this);


        stepTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextStep();
            }
        });


        this.tutorialModel = new TutorialModel();

        AdapterController adapterController = new AdapterController();
        adapterController.addList(new KeyInputController(input,this));
        addKeyListener(adapterController);
        setFocusable(true);

    }

    public void startTutorial() {
        tutorialModel.resetCurrentStepIndex();
        displayCurrentStep();
        stepTimer.start();
    }
    @Override
    public void start(){
        super.start();
        pause();
        startTutorial();
        //drawBackgroundblock();
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
        } else if (tutorialModel.getCurrentStepIndex() == 4) {
        } else if(tutorialModel.getCurrentStepIndex() == 5){
            pieceFixedCount = 0;
            pause();
            drawBackgroundblock();
            stepTimer.stop();
        } else if (numLinesRemoved == 3) {
            tutorialModel.plusCurrnetStepIndex();
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
    }

}
