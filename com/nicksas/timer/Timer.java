package com.nicksas.timer;

import com.nicksas.sound.Sound;

import java.util.Scanner;

public class Timer {
    private int hours;
    private int minutes;
    private int seconds;
    private boolean isStop = false;

    public Timer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество часов: ");
        int hours = scanner.nextInt();

        System.out.println("Введите количество минут: ");
        int minutes = scanner.nextInt();

        System.out.println("Введите количество секунд: ");
        int seconds = scanner.nextInt();

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    private int timeToSeconds() {
        int seconds = 0;
        seconds += hours * 60 * 60;
        seconds += minutes * 60;
        seconds += this.seconds;
        return seconds;
    }

    public int getSeconds() {
        return timeToSeconds();
    }

    public void start() {
        printTimer();

        Sound sound = new Sound();

        Thread play = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    sound.start();
                }
            }
        });

        Thread stop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Остановить?");
                    boolean result = scanner.nextBoolean();
                    if (result) {
                        sound.stop(true);
                        isStop = true;
                    }
                }
            }
        });

        play.start();
        stop.start();

    }

    private void printTimer() {
        System.out.println("Таймер запущен!");
        for (int i = getSeconds(); i >= 0; i--) {
            System.out.println("Осталось " + i + " секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Что-то пошло не так");
                e.printStackTrace(System.out);
            }
        }
        System.out.println("Время вышло");
    }
}
