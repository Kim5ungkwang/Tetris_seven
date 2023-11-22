package kr.ac.jbnu.se.tetris.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.jbnu.se.tetris.models.Member;
import kr.ac.jbnu.se.tetris.models.Rank;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class WebSocketService {

    private static WebSocketService instance= new WebSocketService();
    public enum MessageType{
        ENTER, GAME, START, END
    }
    private String serverUri="http://52.79.64.157:8080/";
    private String socketUri="ws://52.79.64.157:8080/ws/chat";
    //private String serverUri="http://localhost:8080/";
    //private String socketUri="ws://localhost:8080/ws/chat";
    private HttpClient httpClient = HttpClients.createDefault();
    private MyWebSocketClient client;
    protected WebSocketService() {
    }
    public static WebSocketService getInstance(){
        return instance;
    }
    public void startMatching(){
        client=new MyWebSocketClient(URI.create(socketUri));
        client.connect();
        System.out.println("startMatching");
        if(!WebSocketService.getInstance().getClient().getConnection().isOpen()){
            System.out.println("not connected");
        }
    }
    public void startGame(){
        System.out.println("start game");
        sendMessage(MessageType.ENTER,client.getRoomId(), client.getSessionId(),"room enter");
        if(WebSocketService.getInstance().getClient().getConnection().isOpen()){
            System.out.println("connected");
        }
    }
    public MyWebSocketClient getClient(){
        return client;
    }


    public void exitRoom(){
        client.close();
    }

    public void gameEnd(int win){
        MessageType t= MessageType.END;
        String sender= Member.myId;
        String message=new String();
        if(win==1){
            if(client.getSender1().equals(client.getSessionId()))
                message="win";
            else message="lose";
        } else if (win==2) {
            if(client.getSender2().equals(client.getSessionId()))
                message="win";
            else message="lose";
        }
        sendMessage(t, client.getRoomId(), sender,message);
    }

    public void sendMessage(MessageType type, String roomId, String sender, String message){
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("type",type.toString());
        jsonObject.put("roomId",roomId);
        jsonObject.put("sender",sender);
        jsonObject.put("message",message);
        client.send(jsonObject.toJSONString());
        if(WebSocketService.getInstance().getClient().getConnection().isOpen()){
            System.out.println("send message connected");
        }
    }
    public void sendMessage(String message){
        if(!client.getConnection().isOpen())
            System.out.println("not connected");
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("type","GAME");
        jsonObject.put("roomId",client.getRoomId());
        jsonObject.put("sender",client.getSessionId());
        jsonObject.put("message",message);
        client.send(jsonObject.toJSONString());
        if(WebSocketService.getInstance().getClient().getConnection().isOpen()){
            System.out.println("send message 2 connected");
        }
    }


    //check
    public boolean logIn(String id, String password){
        String uri=serverUri+"/memberLogIn?id="+id+"&password="+password;
        try {
            HttpPost request = new HttpPost(uri);
            HttpResponse response = httpClient.execute(request);
            String responseString = EntityUtils.toString(response.getEntity());

            System.out.println(responseString);
            if(responseString.equals("log in success"))
                return true;
            else if(responseString.equals("log in fail"))
                return false;
            else return false;

        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //check
    public boolean join(String id, String password){

        String uri=serverUri+"/memberJoin?id="+id+"&password="+password;

        try {

            HttpPost request = new HttpPost(uri);
            HttpResponse response = httpClient.execute(request);
            String responseString = EntityUtils.toString(response.getEntity());


            System.out.println("join : "+responseString);
            if(responseString.equals("member join success"))
                return true;
            else if(responseString.equals("member join fail"))
                return false;
            else return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //check
    public List<Rank> ranking(){
        List<Rank> list=new ArrayList<>();
        String uri=serverUri+"/ranking";
        try {
            HttpGet httpGet = new HttpGet(uri);
            HttpResponse res =httpClient.execute(httpGet);
            String responseString = EntityUtils.toString(res.getEntity());

            System.out.println(responseString);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseString);
            if (jsonNode.isArray()) {
                // JSON 배열의 각 요소를 반복하여 처리
                for (JsonNode element : jsonNode) {
                    String id = element.get("id").asText();
                    int rank = element.get("rank").asInt();
                    float score=element.get("score").asInt();
                    Rank ranking=new Rank(id,rank,score);
                   list.add(ranking);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public boolean connectTwo(){
        String uri=serverUri+"/loading";
        try {
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);
            String responseString = EntityUtils.toString(response.getEntity());

            System.out.println(responseString);
            if(responseString.equals("two man in"))
                return true;
            else if(responseString.equals("not yet"))
                return false;
            else return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void notIn(){
        String uri=serverUri+"/notIn";
        try {
            HttpGet request = new HttpGet(uri);
            httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
