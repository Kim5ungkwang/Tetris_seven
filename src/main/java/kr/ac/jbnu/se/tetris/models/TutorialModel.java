package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class TutorialModel{

    @Getter
    private int currentStepIndex = 0; //현재 튜토리얼 스텝
    @Getter
    private int currentBrickIndex = 0; // 현재 블럭 인덱스
    public static BrickQueueManager tutorialQueue;
    private ShapeData.Tetrominoes[] tutorialBlocks;
    public static final String[] tutorialSteps = {
            "<html>Step 1: 테트리스 규칙<br>게임의 룰은 내려오는 블록을 한 줄이 꽉 차도록 합니다.<br> 그 줄은 사라지고 이것을 반복하면서 블록이 맨 위까지 안 쌓이게 끝까지 버티면서 플레이하는 게임입니다.</html>",
            "<html>Step 2: 블록 이동하기<br>블록을 움직이려면 A, D 키를 누르세요</html>",
            "<html>Step 3: 블록 회전하기<br>블록을 회전하려면 Q, E 키를 누르세요</html>",
            "<html>Step 4: 행 제거하기<br>블록을 한 줄에 꽉 차도록 쌓으세요</html>",
            "<html>Step 5: SRS(Super Rotation System) 사용하기<br>블록의 빈 공간에 다다르면 회전하면서 홈 사이에 블록을 넣으세요</html>",
            "튜토리얼 끝! 게임을 시작합니다!"
    };
    public TutorialModel(){
        tutorialQueue = new BrickQueueManager();
        tutorialBlocks = new ShapeData.Tetrominoes[6];
        tutorialBlocks[0] = ShapeData.Tetrominoes.LShape;
        tutorialBlocks[1] = ShapeData.Tetrominoes.SShape;
        tutorialBlocks[2] = ShapeData.Tetrominoes.SquareShape;
        tutorialBlocks[3] = ShapeData.Tetrominoes.ZShape;
        tutorialBlocks[4] = ShapeData.Tetrominoes.TShape;
        tutorialBlocks[5] = ShapeData.Tetrominoes.TShape;
    }
    public ShapeData.Tetrominoes getTutorialBlock(int step){
        return tutorialBlocks[currentBrickIndex++];
    }

    public void plusCurrnetStepIndex(){
        this.currentStepIndex++;
    }

    public void resetCurrentStepIndex() {
        currentStepIndex = 0;
    }
}

