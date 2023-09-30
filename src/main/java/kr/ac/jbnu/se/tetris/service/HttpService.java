package kr.ac.jbnu.se.tetris.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//싱글톤
public class HttpService {
    Logger logger=Logger.getLogger(HttpService.class.getName());
    public static HttpService httpService=new HttpService();
    URL url;
    public HttpService() {
    }

    //회원가입하기
    public void memberJoin(String id,String password){
        String url="http://localhost:8080/join";
        url= url+ "?id=" + id + "&password=" + password;
        logger.info(url);
        String ans=getConnection(url);
        if(ans=="success"){
            logger.info("join success");
        }
        else{
            logger.info(ans);
        }
    }
    //로그인
    public boolean logIn(String id, String password){

        String url="http://localhost:8080/join";
        url= url + "?id=" + id + "&password=" + password;

        String ans=getConnection(url);
        if(ans=="success"){
            logger.info("logIn success");
            return true;
        }
        else{
            logger.info("logIn fail");
            return false;
        }
    }
    //방 리스트 확인하기
    public List<Integer> roomList(){
        String url="http://localhost:8080/roomList";
        List<Integer> list= getConnection_list(url);
        if(!list.isEmpty()) {
            logger.info("list exist");
            //존재하는 방의 모든 id출력: 현재는 서버에 더미데이터 집어넣어졌음.
            list.forEach(i->logger.info(String.valueOf(i)));
        }
        else{
            logger.info("no list");
        }
        return list;

    }
    //랭킹 가져오기
    public List getRanking(){
        String url="";

        return null;
    }
    private String getConnection(String apiUrl) {
        String  ans;
        try {
            // HTTP 요청을 보낼 URL 설정
            //String apiUrl = "http://example.com/api"; // 대상 API의 URL로 변경

            // URL 객체 생성
            url = new URL(apiUrl);
            // HttpURLConnection을 사용하여 연결 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // GET 요청 설정
            connection.setRequestMethod("POST");

            // 응답 코드 확인 (200은 성공)
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                ans=response.toString();
                // 응답 데이터 출력
                System.out.println("Response Data: " + ans);

            } else {
                System.out.println("HTTP GET 요청 실패");
                ans="fail";
            }

            // 연결 종료
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            ans="fail";
        }
        logger.info("결과 "+ans);
        return ans;
    }


    private List getConnection_list(String apiUrl) {
        List<Integer> ans=new ArrayList<>();
        try {
            // HTTP 요청을 보낼 URL 설정
            //String apiUrl = "http://example.com/api"; // 대상 API의 URL로 변경

            // URL 객체 생성
            url = new URL(apiUrl);
            // HttpURLConnection을 사용하여 연결 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // GET 요청 설정
            connection.setRequestMethod("POST");

            // 응답 코드 확인 (200은 성공)
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답 데이터 읽기
                ObjectMapper objectMapper = new ObjectMapper();
                ans = objectMapper.readValue(connection.getInputStream(), new TypeReference<List<Integer>>() {});

            } else {
                System.out.println("HTTP GET 요청 실패");

            }

            // 연결 종료
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return ans;
    }





}
