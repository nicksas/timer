package com.nicksas.gui;

import com.nicksas.gui.keyListener.StartKeyListener;
import com.nicksas.gui.keyListener.StopKeyListener;
import com.nicksas.timer.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
        super("Timer by nicksas");
        this.setBounds(200, 200, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gui = this;

        container.setLayout(new FlowLayout());

        hour.addKeyListener(new StartKeyListener(gui));
        minute.addKeyListener(new StartKeyListener(gui));
        second.addKeyListener(new StartKeyListener(gui));
        start.addKeyListener(new StartKeyListener(gui));
        start.addActionListener(new StartEventListener());

        container.add(hourLabel);
        container.add(hour);
        container.add(minuteLabel);
        container.add(minute);
        container.add(secondLabel);
        container.add(second);
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

    private void rebuild() {
        container.revalidate(); // to invoke the layout manager
        container.repaint(); // sometimes needed.
    }

    public class StartEventListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
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
            stop.addActionListener(new StopEventListener());
            stop.addKeyListener(new StopKeyListener(gui));
            stop.requestFocus();

        }

        public void actionPerformed() {
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
            stop.addActionListener(new StopEventListener());
            stop.addKeyListener(new StopKeyListener(gui));
            stop.requestFocus();

        }
    }

    public class StopEventListener implements ActionListener {

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

        public void actionPerformed() {
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
}
