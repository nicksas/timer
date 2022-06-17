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
        playing = true;
        try {
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop(false);
    }

    public void stop(boolean close) {
        clip.stop();
        if (close){
            clip.close();
        }
        playing = false;
    }
}
