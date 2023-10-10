package kr.ac.jbnu.se.tetris.controllers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicController {
    private Clip mainManuClip;

    public MusicController(){
        mainManuMusicInit();
        setMainManuVolume(-20f);    //-80.0f ~ 6.0206f 값
    }

    public void mainManuMusicInit(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("source/sound/mainmanu.wav"));
            mainManuClip = AudioSystem.getClip();
            mainManuClip.open(audioInputStream);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){e.printStackTrace();}

    }

    public void mainManuPlay(){
        if(mainManuClip != null){
            mainManuClip.setFramePosition(0);
            mainManuClip.loop(Clip.LOOP_CONTINUOUSLY);
            mainManuClip.start();
        }
    }

    public void mainManuStop(){
        if(mainManuClip != null){
            mainManuClip.stop();
            mainManuClip.close();
        }
    }

    public void setMainManuVolume(float volume){
        if(mainManuClip != null){
            FloatControl control = (FloatControl) mainManuClip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        }
    }
}
