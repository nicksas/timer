package com.nicksas.gui;

import com.nicksas.timer.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopEventListener implements ActionListener {
    private final Timer timer;
    private final Container container;
    private final JButton start;

    StopEventListener(Timer timer, Container container, JButton start){
        this.timer = timer;
        this.container = container;
        this.start = start;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timer.stop();
        container.add(start);
        container.revalidate(); // to invoke the layout manager
        container.repaint(); // sometimes needed.

    }

}
