package com.nicksas.timer;

import com.nicksas.gui.Gui;
import com.nicksas.sound.Sound;

public class Timer {
    private int totalSecond;
    private boolean isStop = true;
    private final Sound sound = new Sound();
    private final Gui gui;

    public Timer(int hour, int minute, int second, Gui gui) {
        this.gui = gui;
        this.totalSecond += hour * 60 * 60;
        this.totalSecond += minute * 60;
        this.totalSecond += second;
    }

    public String getFormatedTime() {
        int hourResult = totalSecond / 3600;
        int minuteResult = (totalSecond % 3600) / 60;
        int secondResult = (totalSecond % 3600) % 60;

        return hourResult + "h. " + minuteResult + "m. " + secondResult + "s. ";
    }

    public void start() {
        if (!isStop) {
            return;
        }
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                isStop = false;
                printTimer();
                sound.start();
            }
        });

        timer.start();
    }

    public void stop() {
        if (isStop) {
            return;
        }
        sound.stop(true);
        isStop = true;
    }

    private void printTimer() {
        while (totalSecond >= 0){

            if (isStop) {
                return;
            }
            gui.showTime(getFormatedTime());
            try {
                totalSecond--;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
