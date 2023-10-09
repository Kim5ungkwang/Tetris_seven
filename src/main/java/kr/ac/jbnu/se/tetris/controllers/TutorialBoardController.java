package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.*;
import lombok.Getter;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static kr.ac.jbnu.se.tetris.models.TutorialModel.tutorialSteps;

public class TutorialBoardController extends BoardController {
    @Getter
    final private TutorialModel tutorialModel;
    //TutorialPieaceController tutorialPieaceController = new TutorialPieaceController(BoardController);
    private Timer stepTimer;
    public static int pieceFixedCount = 0;

    public TutorialBoardController(PlayerPage parent, KeyInput input){
        this.boardModel = new BoardModel();
        this.playerPage = parent;

        this.statusBar = parent.getStatusBar();
        this.pieceController = new TutorialPieaceController(this);

        this.boardModel.setLoopDelay(1000);  //루프 딜레이 설정 400

        this.timer = new Timer(boardModel.getLoopDelay(), this);
        this.gameTimerController = new GameTimerController();
        stepTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextStep();
            }
        });

        addKeyListener(AdapterController.adapterController);
        AdapterController.adapterController.addList(new KeyInputController(input, this));

        this.tutorialModel = new TutorialModel();
        setFocusable(true);
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
            drawBackgroundblock();
            stepTimer.stop();
            ((TutorialQueue) pieceController.getBrickQueueManager()).setBrickQueue();
        } else if(tutorialModel.getCurrentStepIndex() == 5){
            showImagePanel();
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
    // 5번째 스텝에서 SRS하려면 블럭 어떻게 쌓아야 하는지 이미지 보여주기
    private void showImagePanel(){
        JPanel imagePanel = new JPanel();
        TetrisFrameController frameController = new TetrisFrameController();
        ImageIcon imageIcon = new ImageIcon(""); // 이미지 파일 경로 설정
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel);
        frameController.setContentPane(imagePanel);
    }

    @Override
    public void pieceDropped(Piece droppedPiece){
        pieceFixedCount++;
        System.out.println(pieceFixedCount);
        /*if(!tutorialPieaceController.getIsFallingFinished()){
            tutorialPieaceController.newPiece();
        }*/
    }
}
