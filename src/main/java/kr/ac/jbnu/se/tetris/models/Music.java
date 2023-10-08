package kr.ac.jbnu.se.tetris.models;

import javax.sound.sampled.*;
import java.io.*;


public class Music {
    private Clip clip;
    private File audiofile;
    private boolean isLoop;
    private AudioInputStream audioInputStream;

    // 음악 파일의 경로와 무한 루프로 재생할지 여부를 매개변수로 받음
    public Music(String pathName, boolean isLoop){
        try{
            clip = AudioSystem.getClip();
            audiofile = new File(pathName);
            audioInputStream = AudioSystem.getAudioInputStream(audiofile);
            clip.open(audioInputStream);
        }catch (LineUnavailableException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        }
    }
    //오디오 재생 시작
    public void start(){
        clip.setFramePosition(0); //오디오 파일 위치로 이동
        clip.start();
        if(isLoop) clip.loop(Clip.LOOP_CONTINUOUSLY); // 음악 무한 반복 재생
    }
    // 오디오 재생 중지
    public void stop(){
        clip.stop();
    }
}
