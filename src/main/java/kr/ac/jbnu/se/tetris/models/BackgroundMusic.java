package kr.ac.jbnu.se.tetris.models;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 배경음악을 관리하는 클래스
 * 한번 재생이 끝나면 루프를 돌아 끊임 없이 오디오가 나오게 합니다
 */
public class BackgroundMusic {
    private Clip mainManuClip;

    /**
     * 음악을 초기화하고 볼륨을 설정하는 생성자
     */
    public BackgroundMusic(){
        mainManuMusicInit();
        setMainManuVolume(-10f);    //-80.0f ~ 6.0206f 값
    }

    /**
     * 파일을 불러와 mainManueClip에 저장합니다
     */
    public void mainManuMusicInit(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("source/sound/mainmanu.wav"));
            mainManuClip = AudioSystem.getClip();
            mainManuClip.open(audioInputStream);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){e.printStackTrace();}

    }

    /**
     * 음악을 재생합니다
     * 음악을 재생할 때 0초부터 시작합니다
     */
    public void mainManuPlay(){
        if(mainManuClip != null){
            mainManuClip.setFramePosition(0);
            mainManuClip.loop(Clip.LOOP_CONTINUOUSLY);
            mainManuClip.start();
        }
    }

    /**
     * 음악을 중지할때 사용하는 메서드
     */
    public void mainManuStop(){
        if(mainManuClip != null){
            mainManuClip.stop();
            mainManuClip.close();
        }
    }

    /**
     * 음악의 볼륨을 설정합니다.
     * 생성자에서 사용하며 volum값은 -80.0f ~ 6.0206f으로 매핑하여 사용합니다
     * @param volume 음악의 볼륨 -80.0f ~ 6.0206f값
     */
    public void setMainManuVolume(float volume){
        if(mainManuClip != null){
            FloatControl control = (FloatControl) mainManuClip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        }
    }
}
