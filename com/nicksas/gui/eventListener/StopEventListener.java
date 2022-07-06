package com.nicksas.gui.eventListener;

import com.nicksas.gui.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopEventListener implements ActionListener {
    private Gui gui;

    public StopEventListener(Gui gui){
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent e) {
        gui.stop();
    }
}