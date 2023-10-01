package kr.ac.jbnu.se.tetris.models;

import kr.ac.jbnu.se.tetris.ShapeData;

/**
*다음에 등장할 블록의 배열을 관리하는 클래스이다.
*/
public class BrickQueueManager {
    private ShapeData.Tetrominoes[] brickQueue; //블록이 저장될 배열
    private int brickQueueIndex;    //brickQueue에 사용되는 인덱스
    private BrickGenerator brickGenerator;  //블록 배열을 만들기 위한 객체
    private final int BRICK_QUEUE_SIZE = ShapeData.TETROMINOES_SIZE * 2;    //블록 배열의 크기

    /**
     *길이가 (테트로미노의 개수 * 2)인 블록 배열을 생성한다.
     * 블록배 배열에 새로운 블록들을 저장하기 위해 BrickGenerator의 객체를 초기화한다.
     * 첫번째 블록 배열의 인덱스는 0으로
     * 해당 인덱스부터 블록 배열은 BrickGenerator을 통해 새로운 블록들을 전달받는다.
     */
    public BrickQueueManager(){
        brickQueue = new ShapeData.Tetrominoes[BRICK_QUEUE_SIZE];
        brickGenerator = new BrickGenerator();

        brickQueueIndex = 0;
        setBrickQueue(brickQueueIndex);
    }

    /**
     *getNewShape는 다음 순서에 나올 새로운 테트로미노의 값을 반환하는 메서드이다.
     */
    public ShapeData.Tetrominoes getNewShape(){
        this.brickQueueIndex %= BRICK_QUEUE_SIZE;
        if(brickQueueIndex == 0){
            setBrickQueue(BRICK_QUEUE_SIZE / 2);  //인덱스가 0이되면 배열의 절반 부터 배열의 끝가지 업데이트 한다.
        }
        else if(brickQueueIndex == BRICK_QUEUE_SIZE / 2){
            setBrickQueue(0);   //인덱스가 배열의 절반을 넘기면 배열의 처음부터 배열의 절반까지 업데이트 한다.
        }
        return brickQueue[brickQueueIndex++];
    }

    /**
     *setBrickQueue는 brckQueue의 절반을 기준으로
     *이미 사용된 테트로미노 값들을 업데이트하는 메서드이다.
     */
    public void setBrickQueue(int index) {
        brickGenerator.initializeBrickGenerator();
        for(int i = 0; i < BRICK_QUEUE_SIZE / 2; i++){
            brickQueue[index + i] = brickGenerator.getBrickQueue()[i];
        }
    }

}
