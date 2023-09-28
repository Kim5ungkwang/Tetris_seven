package kr.ac.jbnu.se.tetris.models;

import java.util.Arrays;

/**
 *다음에 등장할 블록을 생성하는 클래스이다
 * 블록 생성의 규칙은 Tetromino의 모양들이 전부 나오는 때를 한 사이클로
 * 사이클이 끝나면 이전에 등장했던 Tetromino가 다시 등장할 수 있다.
 */
public class BrickGenerator {
    private Shape.Tetrominoes[] BrickQueue = new Shape.Tetrominoes[7];  //블록 배열
    private boolean[] visitedTetrominoes = new boolean[8];  //테트로미노가 등장했는지 확인하는 변수
    private int brickQueueIndex;    //블록 배열을 만들때 사용하는 인덱스
    private int seed;   //배열을 만들때 사용되는 시드 값 (네트웍으로 받음)

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
    public void initializeBrickGenerator(){
        Arrays.fill(visitedTetrominoes, false);
        this.seed = 1;  //네트웍에서 받아와야할 부분
        brickQueueIndex = 0;
        makeNewBrickArray(this.seed);
    }

    /**
     *seed값을 메개변수로 새로운 블록 배열을 만드는 메서드이다
     */
    private void makeNewBrickArray(int seed){
        if(brickQueueIndex == Shape.TETROMINOES_SIZE) return;

        int currentIndex = (seed % Shape.TETROMINOES_SIZE) + 1;  //+1은 NoShape이 나오는 것을 방지
        if(isVisitedTetrominoes(currentIndex)){
            makeNewBrickArray(currentIndex);   //메서드 내부에서 currentIndex를 1증가하기 때문에 방문하지 않은 테트로미노를 찾을 수 있다.
        }
        else {
            this.BrickQueue[brickQueueIndex++] = Shape.Tetrominoes.values()[currentIndex];
            visitedTetrominoes[currentIndex] = true;
            makeNewBrickArray(currentIndex + this.seed);    //재귀
        }
    }

    /**
     *index를 매개변수로 해당 블록이 방문되었는지 확인하는 메서드이다.
     */
    private boolean isVisitedTetrominoes(int index){
        return visitedTetrominoes[index];
    }

    /**
     *새로운 블록 배열을 반환하는 메서드이다.
     */
    public Shape.Tetrominoes[] getBrickQueue(){
        return BrickQueue;
    }


}
