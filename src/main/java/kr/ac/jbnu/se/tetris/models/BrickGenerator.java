package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


/**
 *다음에 등장할 블록을 생성하는 클래스이다
 * 블록 생성의 규칙은 Tetromino의 모양들이 전부 나오는 때를 한 사이클로
 * 사이클이 끝나면 이전에 등장했던 Tetromino가 다시 등장할 수 있다.
 */
public class BrickGenerator {
    @Getter
    private ShapeData.Tetrominoes[] BrickQueue = new ShapeData.Tetrominoes[7];  //블록 배열
    private boolean[] visitedTetrominoes = new boolean[8];  //테트로미노가 등장했는지 확인하는 변수
    private int brickQueueIndex;    //블록 배열을 만들때 사용하는 인덱스
    @Setter
    private int seed;   //배열을 만들때 사용되는 시드 값, ThreadLocalRandom -> 동시 접근 문제 서버를 사용하는 우리는 사용할 수 있을듯

    /**
     *visitedTetrominoes를 false로 초기화한다
     * brickQueueIndex를 0으로 초기화한다.
     */
    public BrickGenerator(){
        Arrays.fill(visitedTetrominoes, false);
        brickQueueIndex = 0;
    }

    /**
     *새로운 블록 배열을 받기 위해 초기화하는 메서드이다.
     */
    public void initializeBrickGenerator(int seed){
        Arrays.fill(visitedTetrominoes, false);
        this.seed = seed;  //네트웍에서 받아와야할 부분
        brickQueueIndex = 0;
        makeNewBrickArray(this.seed);
    }

    /**
     * 새로운 블록 배열을 만드는 메서드
     * @param seed 블록 생성을 위한 seed값
     */
    private void makeNewBrickArray(int seed){
        if(brickQueueIndex == ShapeData.TETROMINOES_SIZE) return;

        int currentIndex = (seed % ShapeData.TETROMINOES_SIZE) + 1;  //+1은 NoShape이 나오는 것을 방지
        if(isVisitedTetrominoes(currentIndex)){
            makeNewBrickArray(currentIndex);   //메서드 내부에서 currentIndex를 1증가하기 때문에 방문하지 않은 테트로미노를 찾을 수 있다.
        }
        else {
            this.BrickQueue[brickQueueIndex++] = ShapeData.Tetrominoes.values()[currentIndex];
            visitedTetrominoes[currentIndex] = true;
            makeNewBrickArray(currentIndex + this.seed);    //재귀
        }
    }

    /**
     * Tetrominoes가 방문되었는지 확인하는 메서드이다.
     * @param index 1번부터 Tetrominoes ordinal에 해당하는 숫자
     * @return index에 해당하는 순서의 Tetrominoes가 방문되었는지 여부
     */
    private boolean isVisitedTetrominoes(int index){
        return visitedTetrominoes[index];
    }



}
