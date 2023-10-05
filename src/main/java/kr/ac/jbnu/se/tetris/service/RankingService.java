package kr.ac.jbnu.se.tetris.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RankingService {
    String filePath = "src/main/java/kr/ac/jbnu/se/tetris/data/ranking.json";
    ObjectMapper objectMapper = new ObjectMapper();

    //랭킹 가져오기
    public List<Integer> readRanking() {

        List<Integer> scoreList = new ArrayList<>();
        try {
            // JSON 파일을 읽어옴
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            // "score" 배열을 Java의 List로 변환

            JsonNode scoreArrayNode = rootNode.get("score");

            if (scoreArrayNode.isArray()) {
                for (JsonNode scoreNode : scoreArrayNode) {
                    if (scoreNode.isInt()) {
                        scoreList.add(scoreNode.intValue());
                    }
                }
            }
            // 결과 출력
            for(int i=0;i<10;i++){
                System.out.println(scoreList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scoreList;
    }
    //랭킹 순위 가져오기.
    public List getHighRank(){
        List<Integer> highRank=new ArrayList<>();
        List<Integer> allList=readRanking();
        Collections.sort(allList);
        for(int i=0;i<10;i++){
            highRank.add(allList.get(i));
        }
        System.out.println(highRank);
        return highRank;
    }
    //랭킹 저장하기
    public void saveRanking(int score){
        try {
            // JSON 파일 열기
            File jsonFile = new File(filePath);
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // 데이터 추가
            ArrayNode scoreArray = (ArrayNode) rootNode.get("score");
            scoreArray.add(score);

            // JSON 파일 다시 쓰기
            objectMapper.writeValue(jsonFile, rootNode);

            System.out.println("데이터 추가 완료");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

