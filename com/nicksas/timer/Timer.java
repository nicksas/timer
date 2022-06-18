package com.nicksas.timer;

import java.util.Scanner;

public class Timer {
    private int hour;
    private int minute;
    private int second;
    private boolean isStop = false;

    public Timer(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Timer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество часов: ");
        int hour = scanner.nextInt();

        System.out.println("Введите количество минут: ");
        int minute = scanner.nextInt();

        System.out.println("Введите количество секунд: ");
        int second = scanner.nextInt();

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    private int timeToSeconds() {
        int seconds = 0;
        seconds += hour * 60 * 60;
        seconds += minute * 60;
        seconds += second;
        return seconds;
    }

    public int getSeconds() {
        return timeToSeconds();
    }

    public void start() {
        Sound sound = new Sound();
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                printTimer();
                sound.start();
            }
        });

        timer.start();

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
