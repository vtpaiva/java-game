package com.mycompany.jogobrabo;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Movimento implements KeyListener, MouseListener, MouseMotionListener{
    public MyPanel panel;
    private boolean mouseButtonPressed = false;

    Movimento(MyPanel panel){
        this.panel = panel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            panel.setLocation(panel.getX() - panel.posicaoXValida(), panel.getY() - panel.posicaoYValida());
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseButtonPressed = true; // Marcar o botão do mouse como pressionado
            panel.addEntidade(new Projetil(
                    "caveira.png",
                    panel.hero.getX(),
                    panel.hero.getY(),
                        (int) (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2)/100,
                        (int) (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2)/100
                ));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseButtonPressed = false; // Marcar o botão do mouse como liberado
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panel.hero.rotate(MouseInfo.getPointerInfo().getLocation());
    }

}
