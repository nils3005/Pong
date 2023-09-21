package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound {
    private static final String PADDLE = "/BallHitPaddle.wav";
    private static final String SCORE = "/Scored.wav";
    String score;

    public static void play(SE se) {
        String audioPath = PADDLE;
        Clip clip;
        if (se == SE.SCORE) audioPath = SCORE;
        try {
            InputStream is = Sound.class.getResourceAsStream(audioPath);
            BufferedInputStream bis = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum SE {
        PADDLE,
        SCORE;
    }
}
