package kr.ac.jbnu.se.tetris.controllers;

import kr.ac.jbnu.se.tetris.ShapeData;
import kr.ac.jbnu.se.tetris.models.BoardModel;
import kr.ac.jbnu.se.tetris.models.TutorialModel;
import kr.ac.jbnu.se.tetris.views.TetrisBoard;
import lombok.Getter;

import static kr.ac.jbnu.se.tetris.models.TutorialModel.currentStepIndex;
import static kr.ac.jbnu.se.tetris.models.TutorialModel.tutorialSteps;

public class TutorialController extends BoardController {
    @Getter
    final private TutorialModel tutorialModel;

    /**
     * 게임이 이루어지는 보드를 관리하는 클래스
     *
     * @param tetrisBoard 뷰와 연결
     */
    public TutorialController(TetrisBoard tetrisBoard, TutorialModel tutorialModel) {
        super(tetrisBoard);
        this.tutorialModel = tutorialModel;
    }

    @Override
    public void start() {
        super.start();
    }

    private void displayCurrentStep() {
        if (currentStepIndex >= 0 && currentStepIndex < tutorialSteps.length) {
            String currentStepText = tutorialSteps[currentStepIndex];
            tetrisBoard.setStatusText(currentStepText);
        }
    }

    public void moveToNextStep() {
        if (currentStepIndex < tutorialSteps.length - 1) {
            currentStepIndex++;
            displayCurrentStep(); // 다음 튜토리얼을 게임 보드에 표시
            performStepAciton();
        } else if (currentStepIndex == 4) {
            drawBackgroundblock();
        }else {
            // 튜토리얼 마지막 단계 도달한 경우 로직 추가
            start();
        }
    }
    private void performStepAciton() {
        switch(currentStepIndex) {
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
                        tutorialModel.setboard(x + y * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.NoShape);
                    }
                    tutorialModel.setboard(boardX + boardY * BoardModel.getBoardWidth(), ShapeData.Tetrominoes.TutorialBackground);
                }
            }
        }
    }
}
