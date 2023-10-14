package kr.ac.jbnu.se.tetris.models;

import lombok.Getter;

@Getter
public class Rank{
    private String id;
    private int rank;
    private float score;
    public Rank(String id,int rank,float score) {
        this.id=id;
        this.rank=rank;
        this.score=score;
    }
}