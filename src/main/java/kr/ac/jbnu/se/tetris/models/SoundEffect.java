package kr.ac.jbnu.se.tetris.models;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 블럭이 삭제될떄 나는 효과음을 관리하는 메서드
 */
public class SoundEffect extends Thread {
    private String soundFilePath;
    private boolean isPlaying;

    public SoundEffect() {
        this.soundFilePath = "source/sound/finger_snap.wav";
        this.isPlaying = false;
    }

    @Override
    public void run() {
        playSound();
    }

    public void play() {
        isPlaying = true;
    }

    public void stopPlaying() {
        isPlaying = false;
    }

    private void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            // 재생이 끝날 때까지 대기
            Thread.sleep((clip.getMicrosecondLength() / 1000) - 4);

            clip.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
