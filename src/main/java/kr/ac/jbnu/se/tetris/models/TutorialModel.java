package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class TutorialModel extends BoardModel{

    @Getter
    public static int currentStepIndex = 0; //현재 튜토리얼 스텝
    @Getter
    private int currentBrickIndex = 0; // 현재 블럭 인덱스
    public static final String[] tutorialSteps = {
            "<html># Step 1: 테트리스 규칙<br>- 테트리스의 목표는 아래로 떨어지는 블럭을 사용해 가로 한 줄을 완성하는 것입니다.<br>- 한 줄을 채우려고 노력하면서 버티는 게임입니다.<br>- 튜토리얼을 건너뛰려면 스킵 버튼을 눌러주세요</html>",
            "<html># Step 2: 블럭 이동하기<br>- 블럭을 좌우로 움직이려면 A 키(왼쪽)와, D 키(오른쪽)를 사용하세요.</html>",
            "<html># Step 3: 블럭 회전하기<br>- 블럭을 회전하려면 Q 키(반시계 방향), E 키(시계 방향)를 누르세요.</html>",
            "<html># Step 4: 행 제거하기<br>- 블럭을 한 줄에 꽉 차도록 쌓으세요<br>- 가득 찬 줄은 사라지고, 점수를 얻을 수 있습니다.</html>",
            "<html># Step 5: SRS(Super Rotation System) 사용하기<br>- SRS는 블럭의 회전을 더 효율적으로 만드는 시스템입니다.<br>- 그림과 같이 블럭을 쌓고, 빈 공간에 도달하면 블럭을 회전시켜 홈 사이에 넣으세요<br>- T모양 블럭을 이용해서 SRS를 수행하고 세 줄을 한 번에 없애보세요</html>",
            "<html># 잘하셨습니다!<br>이제 테트리스를 더 재미있게 즐길 수 있을 것입니다.<br>메인화면으로 이동하시려면 스킵을 눌러주세요"
    };
    public TutorialModel(){
        currentBrickIndex = 0;
    }

    public void plusCurrnetStepIndex(){
        this.currentStepIndex++;
    }

    public void resetCurrentStepIndex() {
        currentStepIndex = 0;
    }
}

