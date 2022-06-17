package com.nicksas.gui;

import com.nicksas.timer.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Gui extends JFrame {
    private JButton start = new JButton("Запустить");
    private JTextField hour = new JTextField(3);
    private JLabel hourLabel = new JLabel("Hour");
    private JTextField minute = new JTextField(3);
    private JLabel minuteLabel = new JLabel("Minute");
    private JTextField second = new JTextField(3);
    private JLabel secondLabel = new JLabel("Second");

    public Gui() {
        super("Timer");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 2, 2, 2));
        container.add(hourLabel);
        container.add(hour);
        container.add(minuteLabel);
        container.add(minute);
        container.add(secondLabel);
        container.add(second);
        start.addActionListener(new StartEventListener());
        container.add(start);
    }

    class StartEventListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Timer timer = new Timer(Integer.parseInt(hour.getText()), Integer.parseInt(minute.getText()), Integer.parseInt(second.getText()));
            timer.start();
            String message = hour + " " + minute + " " + second;
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
