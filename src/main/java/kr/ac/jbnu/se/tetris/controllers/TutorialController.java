package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.*;
import lombok.Getter;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static kr.ac.jbnu.se.tetris.models.TutorialModel.tutorialSteps;

public class TutorialController extends BoardController {
    @Getter
    final private TutorialModel tutorialModel;
    Piece piece;
    TutorialModel brickQueueManager;


    private Timer timer;

    public TutorialController(PlayerPageController parent, KeyInput input) {
        super(parent, input);
        this.tutorialModel = new TutorialModel();
        startTutorial();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveToNextStep();
            }
        }, 0, 10000);
    }

    public void startTutorial() {
        tutorialModel.resetCurrentStepIndex();
        displayCurrentStep();
    }

    @Override
    public void start() {
        super.start();
        startTutorial();

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
            drawBackgroundblock();

        } else {
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

    public void drawNextTutorialBlock() {
        piece = new Piece();
        ShapeData.Tetrominoes block = tutorialModel.getTutorialBlock(tutorialModel.getCurrentBrickIndex());
    }
}
