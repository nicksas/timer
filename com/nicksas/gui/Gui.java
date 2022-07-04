package com.nicksas.gui;

import com.nicksas.timer.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Stack;

public class Gui extends JFrame {
    private JButton start = new JButton("Start");
    private JTextField hour = new JTextField(3);
    private JLabel hourLabel = new JLabel("Hour");
    private JTextField minute = new JTextField(3);
    private JLabel minuteLabel = new JLabel("Minute");
    private JTextField second = new JTextField(3);
    private JLabel secondLabel = new JLabel("Second");
    private JButton stop = new JButton("Stop");
    private JLabel timeLabel = new JLabel("Finish:");
    private JLabel time = new JLabel("");
    private Timer timer;
    private Gui gui;
    Container container = this.getContentPane();

    public Gui() {
        super("Timer");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gui = this;

        container.setLayout(new FlowLayout());
        container.add(hourLabel);
        container.add(hour);
        container.add(minuteLabel);
        container.add(minute);
        container.add(secondLabel);
        container.add(second);
        start.addActionListener(new StartEventListener());
        container.add(start);
    }

    public void showTime() {
        time.setText(timer.getFormatedTime());
        container.add(time);
        rebuild();
    }

    public void showTime(String formatedTime) {
        time.setText(formatedTime);
    }

    class StartEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timer = new Timer(Integer.parseInt(hour.getText()), Integer.parseInt(minute.getText()), Integer.parseInt(second.getText()), gui);
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
            stop.addActionListener(new StopEventListener());

        }
    }

    class StopEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            container.add(hourLabel);
            container.add(hour);
            container.add(minuteLabel);
            container.add(minute);
            container.add(secondLabel);
            container.add(second);
            container.add(start);
            container.remove(stop);
            container.remove(timeLabel);
            container.remove(time);
            rebuild();
        }
    }

    private void rebuild() {
        container.revalidate(); // to invoke the layout manager
        container.repaint(); // sometimes needed.
    }
}
