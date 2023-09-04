package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    Clip clip;
    String paddle;
    String score;

    public Sound() {
        paddle = "src/main/resources/BallHitPaddle.wav";
        score = "src/main/resources/Scored.wav";
    }

    public void play(SE se) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(paddle));
            if (se == SE.SCORE){
                ais = AudioSystem.getAudioInputStream(new File(score));
            }
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.start();
    }

    public enum SE {
        PADDLE,
        SCORE;
    }
}
