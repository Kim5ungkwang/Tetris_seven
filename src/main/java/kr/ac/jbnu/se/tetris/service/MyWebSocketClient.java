package kr.ac.jbnu.se.tetris.service;

import kr.ac.jbnu.se.tetris.controllers.MultiActionController;
import kr.ac.jbnu.se.tetris.controllers.PieceController;
import kr.ac.jbnu.se.tetris.views.pages.MultiTwoPlayPage;
import lombok.Getter;
import lombok.Setter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;



@Getter

public class MyWebSocketClient extends WebSocketClient {


    public enum MessageType{
        START,ENTER, GAME,END
    }
    private MessageType type;
    private String sender1;
    private String sender2;
    private String action;
    private String roomId;
    private String sessionId;
    private double seed1;
    private double seed2;
    @Setter
    private MultiActionController controller1;
    @Setter
    private MultiActionController controller2;


    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }


    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("connected");
    }

    @Override
    public void onMessage(String message) {
        JSONObject obj;
        JSONParser jsonParser= new JSONParser();
        try {
            obj= (JSONObject) jsonParser.parse(message);
            //막 들어갔을때, 받기

            //start
            if(obj.get("type").equals( MessageType.START)){
                if(obj.get("sender").equals("roomId")){
                    roomId=obj.get("message").toString();
                }
                else if(obj.get("sender").equals("sessionId")){
                    sessionId=obj.get("message").toString();

                }
            }
            //enter
            else if(obj.get("type").equals(MessageType.ENTER)){
                if(obj.get("sender").equals("player1")){
                    sender1=obj.get("message").toString();
                    System.out.println(sender1);
                }
                else if(obj.get("sender").equals("player2")){
                    sender2=obj.get("message").toString();
                    System.out.println(sender2);
                }
                else if(obj.get("sender").equals("seed")){
                    String splitStrArr[] = obj.get("message").toString().split(",");
                     seed1=Double.parseDouble(splitStrArr[0]);
                     seed2=Double.parseDouble(splitStrArr[1]);
                    System.out.println("seed1 : "+seed1 +" seed2 : "+seed2);
                }
            }
            //game
            else if(obj.get("type").equals(MessageType.GAME)){
                String sessionId=obj.get("sender").toString();
                try {
                    if(sessionId.equals("플레이어1")){
                        action=obj.get(message).toString();
                        controller1.action(action);
                        System.out.println("action : "+action);
                        //작동 호출
                    }else if(sessionId.equals("플레이어2")){
                        action=obj.get(message).toString();
                        controller2.action(action);
                        System.out.println("action : "+action);
                        //작동 호출
                    }
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }


            }
            //end
            else if(obj.get("type").equals(MessageType.END)){
                System.out.println("end game");
                //게임 종료
            }



        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String text) {
        super.send(text);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("close");
    }

    @Override
    public void onError(Exception ex) {

    }
}
