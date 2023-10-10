package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static kr.ac.jbnu.se.tetris.controllers.PieceController.currentPiece;
import static kr.ac.jbnu.se.tetris.models.TutorialModel.tutorialSteps;

public class TutorialBoardController extends BoardController {
    @Getter
    final private TutorialModel tutorialModel;
    private Timer stepTimer;
    @Getter
    @Setter
    public static int pieceFixedCount = 0;

    public TutorialBoardController(PlayerPage parent, KeyInput input){
        this.boardModel = new BoardModel();
        this.playerPage = parent;

        this.statusBar = parent.getStatusBar();
        this.pieceController = new TutorialPieaceController(this);

        this.boardModel.setLoopDelay(1000);  //루프 딜레이 설정 400

        this.timer = new Timer(boardModel.getLoopDelay(), this);
        this.gameTimerController = new GameTimerController(this);
        this.gameTimerController = new GameTimerController(this);
        stepTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextStep();
            }
        });

        addKeyListener(AdapterController.adapterController);
        AdapterController.adapterController.addList(new KeyInputController(input, this));

        this.tutorialModel = new TutorialModel();
        setFocusable(true);
        super.pieceDropped(currentPiece);
    }

    public void startTutorial() {
        tutorialModel.resetCurrentStepIndex();
        displayCurrentStep();
        stepTimer.start();
    }

    @Override
    public void start() {
        super.start();
        startTutorial();
        //drawBackgroundblock();
    }

    private void displayCurrentStep() {
        if (tutorialModel.getCurrentStepIndex() >= 0 && tutorialModel.getCurrentStepIndex() < tutorialSteps.length) {
            String currentStepText = tutorialSteps[tutorialModel.getCurrentStepIndex()];
            setStatusText(currentStepText);
        }
    }

    public void moveToNextStep() {
        if (tutorialModel.getCurrentStepIndex() < tutorialSteps.length - 1 && tutorialModel.getCurrentStepIndex() != 4) {
            tutorialModel.plusCurrnetStepIndex();
            displayCurrentStep(); // 다음 튜토리얼을 게임 보드에 표시
        } else if (tutorialModel.getCurrentStepIndex() == 4) {
            //showImagePanel();
        } else if(tutorialModel.getCurrentStepIndex() == 5){
            pieceFixedCount = 0;
            BoardController.clearBoard();
            drawBackgroundblock();
            ((TutorialQueue) pieceController.getBrickQueueManager()).setBrickQueue();
        }
        else {
            // 튜토리얼 마지막 단계 도달한 경우 로직 추가
            start();
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
}
