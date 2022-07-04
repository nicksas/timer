package com.nicksas.timer;

import com.nicksas.gui.Gui;

import java.util.Scanner;

public class Timer {
    private int hour;
    private int minute;
    private int second;
    private int totalSecond;
    private Sound sound = new Sound();
    private boolean isStop = true;
    private Gui gui;

    public Timer(int hour, int minute, int second, Gui gui) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
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
        System.out.println(isStop);
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
        System.out.println("Таймер остановлен");
    }

    private void printTimer() {
        System.out.println("Таймер запущен!");
        while (totalSecond >= 0){

            if (isStop) {
                return;
            }
            gui.showTime(getFormatedTime());
            System.out.println("Осталось " + totalSecond + " секунд");
            try {
                totalSecond--;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Что-то пошло не так");
                e.printStackTrace(System.out);
            }
        }

        System.out.println("Время вышло");
    }
}
