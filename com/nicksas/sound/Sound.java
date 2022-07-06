package com.nicksas.sound;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sound {

    Clip clip = null;
    AudioInputStream ais = null;
    Boolean playing = false;

    public Sound() {
        String fileName = "resources/sound.wav";
        try {
            InputStream bufferedIn = new BufferedInputStream(getFileFromResourceAsStream(fileName));
            ais = AudioSystem.getAudioInputStream(bufferedIn);
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
        if (close) {
            clip.close();
        }
        playing = false;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
