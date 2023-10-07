package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.KeyInput;
import kr.ac.jbnu.se.tetris.models.TutorialModel;
import lombok.Getter;

import static kr.ac.jbnu.se.tetris.models.TutorialModel.tutorialSteps;

public class TutorialController extends BoardController {
    @Getter
    final private TutorialModel tutorialModel;


    public TutorialController(PlayerPageController parent, KeyInput input) {
        super(parent, input);
        this.tutorialModel = new TutorialModel();
    }

    @Override
    public void start() {
        super.start();
    }

    private void displayCurrentStep() {
        if (tutorialModel.getCurrentStepIndex() >= 0 && tutorialModel.getCurrentStepIndex() < tutorialSteps.length) {
            String currentStepText = tutorialSteps[tutorialModel.getCurrentStepIndex()];
            setStatusText(currentStepText);
        }
    }

    public void moveToNextStep() {
        if (tutorialModel.getCurrentStepIndex() < tutorialSteps.length - 1) {
            tutorialModel.plusCurrnetStepIndex();
            displayCurrentStep(); // 다음 튜토리얼을 게임 보드에 표시
            performStepAciton();
        } else if (tutorialModel.getCurrentStepIndex() == 4) {
            drawBackgroundblock();
        }else {
            // 튜토리얼 마지막 단계 도달한 경우 로직 추가
            start();
        }
    }
    private void performStepAciton() {
        switch(tutorialModel.getCurrentStepIndex()) {
            case 0:

        }
    }
    private void drawBackgroundblock() {
        int[][] tutorialBackgroundCoords = ShapeData.COORDS_TABLE[ShapeData.Tetrominoes.TutorialBackground.ordinal()];

        // 배치할 TutorialBackground 블록 단 수
        int numberOfRows = 3;
        int numberOfColumns = 7;
        int targetColumn = 0;

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                for (int i = 0; i < tutorialBackgroundCoords.length; ++i) {
                    int x = tutorialBackgroundCoords[i][0];
                    int y = tutorialBackgroundCoords[i][1];

                    // 각 블록의 초기 위치 계산
                    int initialX = col * tutorialBackgroundCoords.length; // 각 블록 간의 가로 간격 조절
                    int initialY = BoardModel.getBoardHeight() - (row * tutorialBackgroundCoords[0].length); // 각 블록 간의 세로 간격 조절

                    int boardX = initialX + x;
                    int boardY = initialY + y;
                    // 1열 12행에 해당하는 좌표의 블록을 NoShape로 설정
                    if (x == targetColumn && y == 0) {
                        getBoardModel().setboard(x + y * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.NoShape);
                    }
                    getBoardModel().setboard(boardX + boardY * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.TutorialBackground);
                }
            }
        }
    }
}
