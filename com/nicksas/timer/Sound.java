package com.nicksas.timer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    File audioFile = null;
    Clip clip = null;
    AudioInputStream ais = null;
    Boolean playing = false;
    Thread soundStream = null;

    public Sound() {
        try {
            audioFile = new File("sound.wav");

            ais = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        clip.setFramePosition(0);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        playing = true;
    }

    public void stop(boolean close) {
        clip.stop();
        if (close){
            clip.close();
        }
        playing = false;
    }
}
