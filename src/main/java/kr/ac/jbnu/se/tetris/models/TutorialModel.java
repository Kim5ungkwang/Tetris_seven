package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;
import lombok.Getter;

public class TutorialModel extends BoardModel{

    @Getter
    public static int currentStepIndex = 0; //현재 튜토리얼 스텝
    public static final String[] tutorialSteps = {
            "Step 1: 테트리스 규칙\n게임의 룰은 내려오는 블록을 한 줄이 꽉 차도록 합니다.\n 그 줄은 사라지고 이것을 반복하면서 블록이 맨 위까지 안 쌓이게 끝까지 버티면서 플레이하는 게임입니다.\n *튜토리얼을 건너뛰려면 아니오를 누르세요.",
            "Step 2: 블록 이동하기\n블록을 움직이려면 좌우방향키를 움직이세요",
            "Step 3: 블록 회전하기\n블록을 회전하려면 상하방향키를 움직이세요",
            "Step 4: 행 제거하기\n블록을 한 줄에 꽉 차도록 쌓으세요",
            "Step 5: SRS(Super Rotation System) 사용하기\n블록의 빈 공간에 다다르면 회전하면서 홈 사이에 블록을 넣으세요",
            "튜토리얼 끝! 게임을 시작합니다!"
    };

    public TutorialModel(TutorialModel tutorialModel) {
        super();
    }

    @Override
    public void setboard(int index, ShapeData.Tetrominoes pieceShape){
    }
}


