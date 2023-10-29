package com.mycompany.jogobrabo;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Movimento implements KeyListener, MouseListener, MouseMotionListener{
    public MyPanel panel;

    Movimento(MyPanel panel){
        this.panel = panel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> panel.moveForward();
            case KeyEvent.VK_S -> panel.moveBackward();
            case KeyEvent.VK_D -> panel.moveRight();
            case KeyEvent.VK_A -> panel.moveLeft();
            case KeyEvent.VK_Z -> {
                panel.addInimigo(new InimigoBase("enemy.png", 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getX() - panel.getX(), 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getY() - panel.getY(), 
                                                50, panel, 0));
            }
            case KeyEvent.VK_X -> {
                panel.addInimigo(new InimigoSoco("enemy.png", 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getX() - panel.getX(), 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getY() - panel.getY(), 
                                                50, panel, 0));
            }
            case KeyEvent.VK_C -> {
                panel.addInimigo(new InimigoSniper("enemy.png", 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getX() - panel.getX(), 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getY() - panel.getY(), 
                                                50, panel, 0));
            }
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
        if (e.getButton() == MouseEvent.BUTTON1) {
            panel.addEntidade(new Projetil(
                    "caveira.png",
                    panel.hero.getX(),
                    panel.hero.getY(),
                        (int) (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2)/5,
                        (int) (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2)/5,
                        panel, true));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            panel.addEntidade(new Projetil(
                    "caveira.png",
                    panel.hero.getX(),
                    panel.hero.getY(),
                        (int) (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2)/5,
                        (int) (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2)/5,
                        panel, true));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        panel.hero.rotate(MouseInfo.getPointerInfo().getLocation());
        
        if(panel.hero.now - panel.hero.last > 100000000) {
            panel.addEntidade(new Projetil("caveira.png",
                                            panel.hero.getX(),
                                            panel.hero.getY(),
                                            (int) (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2)/5,
                                            (int) (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2)/5,
                                            panel, true));
            panel.hero.last = System.nanoTime();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panel.hero.rotate(MouseInfo.getPointerInfo().getLocation());
    }
}
