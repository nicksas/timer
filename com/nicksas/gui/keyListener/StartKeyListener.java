package com.nicksas.gui.keyListener;

import com.nicksas.gui.Gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartKeyListener implements KeyListener {

    private Gui gui;

    public StartKeyListener(Gui gui){
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10){
            gui.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
