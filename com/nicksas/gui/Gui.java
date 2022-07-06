package com.nicksas.gui;

import com.nicksas.gui.eventListener.StartEventListener;
import com.nicksas.gui.eventListener.StopEventListener;
import com.nicksas.gui.keyListener.StartKeyListener;
import com.nicksas.gui.keyListener.StopKeyListener;
import com.nicksas.timer.Timer;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private final JButton start = new JButton("Start");
    private final JTextField hour = new JTextField(3);
    private final JLabel hourLabel = new JLabel("Hour");
    private final JTextField minute = new JTextField(3);
    private final JLabel minuteLabel = new JLabel("Minute");
    private final JTextField second = new JTextField(3);
    private final JLabel secondLabel = new JLabel("Second");
    private final JButton stop = new JButton("Stop");
    private final JLabel timeLabel = new JLabel("Finish:");
    private final JLabel time = new JLabel("");
    private Timer timer;
    private final Gui gui;
    private final Container container = this.getContentPane();

    public Gui() {
        super("Timer by nicksas");
        this.setBounds(200, 200, 380, 70);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gui = this;

        container.setLayout(new FlowLayout());

        hour.addKeyListener(new StartKeyListener(gui));
        minute.addKeyListener(new StartKeyListener(gui));
        second.addKeyListener(new StartKeyListener(gui));
        start.addKeyListener(new StartKeyListener(gui));
        start.addActionListener(new StartEventListener(gui));

        addInputsAndStartButton();
    }

    public void showTime(String formatedTime) {
        time.setText(formatedTime);
    }

    public void start() {
        int hourText = !hour.getText().equals("") ? Integer.parseInt(hour.getText()) : 0;
        int minuteText = !minute.getText().equals("") ? Integer.parseInt(minute.getText()) : 0;
        int secondText = !second.getText().equals("") ? Integer.parseInt(second.getText()) : 0;

        timer = new Timer(hourText, minuteText, secondText, gui);
        timer.start();

        container.add(timeLabel);
        showTime();
        container.add(stop);
        container.remove(start);
        container.remove(hourLabel);
        container.remove(hour);
        container.remove(minuteLabel);
        container.remove(minute);
        container.remove(secondLabel);
        container.remove(second);
        rebuild();

        stop.addActionListener(new StopEventListener(gui));
        stop.addKeyListener(new StopKeyListener(gui));
        stop.requestFocus();
    }

    public void stop() {
        timer.stop();
        addInputsAndStartButton();
        container.remove(stop);
        container.remove(timeLabel);
        container.remove(time);
        rebuild();
    }

    private void addInputsAndStartButton(){
        container.add(hourLabel);
        container.add(hour);
        container.add(minuteLabel);
        container.add(minute);
        container.add(secondLabel);
        container.add(second);
        container.add(start);
    }

    private void rebuild() {
        container.revalidate(); // to invoke the layout manager
        container.repaint(); // sometimes needed.
    }

    private void showTime() {
        time.setText(timer.getFormatedTime());
        container.add(time);
    }

}
