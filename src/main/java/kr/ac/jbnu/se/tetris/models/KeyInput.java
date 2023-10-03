package kr.ac.jbnu.se.tetris.models;


import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;

@Getter
@Setter
//키 입력 세팅 자체를 클래스로 만들고, 이걸 TetrisBoard클래스에 넣는 방향으로 구상변경
public class KeyInput {
    Logger logger= Logger.getLogger(KeyInput.class.getName());
    //각 동작마다의 키 바인딩.
    //세팅 UI를 따로 준비하고 거기서 플레이어가 변경하도록 만들것.
    private Long rotateRight;
    private Long rotateLeft;
    private Long moveRight;
    private Long moveLeft;
    private Long dropDown;
    private Long pause;
    private Long blockHold;


    public KeyInput(String filePath)  {
        try {
            Reader reader= new FileReader(filePath);
            JSONParser parser=new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            rotateRight= (long) jsonObject.get("rotateR");
            rotateLeft=(long)  jsonObject.get("rotateL");
            moveRight=(long)  jsonObject.get("moveR");
            moveLeft=(long)  jsonObject.get("moveL");
            dropDown=(long)  jsonObject.get("down");
            blockHold=(long) jsonObject.get("blockHold");
            pause=(long)  jsonObject.get("pause");
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void playerKeySetting(String filePath,String key, long val) {
        try {
            Reader reader = new FileReader(filePath);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            jsonObject.replace(key, val);
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}