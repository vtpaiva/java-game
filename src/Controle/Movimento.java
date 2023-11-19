package Controle;

import Entidades.MyPanel;
import Entidades.*;
import Niveis.*;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Instância de listener que monitora a entrada do usuário.
public class Movimento implements KeyListener, MouseListener, MouseMotionListener, Serializable{
    private MyPanel panel;
    private boolean listenActive;

    public Movimento(MyPanel panel){
        this.panel = panel;
        this.listenActive = true;
    }

    public MyPanel getPanel() {
        return panel;
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }

    public boolean isListenActive() {
        return listenActive;
    }

    public void setListenActive(boolean listenActive) {
        this.listenActive = listenActive;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(this.getPanel().getGameState() != 3 && e.getKeyCode() == KeyEvent.VK_R) {panel.setGame();}
        if(this.getPanel().getGameState() == 3 && e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.getPanel().setGameState(0);
            this.setListenActive(true);
        }
        if(!this.listenActive) {return;}
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> panel.moveForward();
            case KeyEvent.VK_S -> panel.moveBackward();
            case KeyEvent.VK_D -> panel.moveRight();
            case KeyEvent.VK_A -> panel.moveLeft();
            case KeyEvent.VK_T -> panel.proximaFase();
            case KeyEvent.VK_O -> {
                try{
                    panel.getSaveLoad().save();
                }
                catch(IOException f) {
                    f.getMessage();
                }
            }
            case KeyEvent.VK_P -> {
            try {
                panel.getSaveLoad().load();
            } catch (IOException | ClassNotFoundException ex) {
                ex.getMessage();
            }
            }
            case KeyEvent.VK_Z -> {
                panel.addInimigo(new InimigoBase("enemy.png",
                                                    (int) MouseInfo.getPointerInfo().getLocation().getX() - panel.getX(), 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getY() - panel.getY(), 
                                                    3, Consts.CHAR_BASE, Consts.CHAR_BASE, panel, 0));
            }
            case KeyEvent.VK_X -> {
                panel.addInimigo(new InimigoSniper("sniper.png",
                                                    (int) MouseInfo.getPointerInfo().getLocation().getX() - panel.getX(), 
                                                    (int) MouseInfo.getPointerInfo().getLocation().getY() - panel.getY(), 
                                                    3, Consts.CHAR_BASE, Consts.CHAR_BASE, panel, 0));
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
        if(!this.listenActive) {return;}
        if (e.getButton() == MouseEvent.BUTTON1) {
            panel.getHero().atira();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!this.listenActive) {return;}
        if (e.getButton() == MouseEvent.BUTTON1) {
            panel.getHero().atira();
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
        if(!this.listenActive) {return;}
        panel.getHero().rotate(MouseInfo.getPointerInfo().getLocation());
        panel.getHero().atira();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!this.listenActive) {return;}
        panel.getHero().rotate(MouseInfo.getPointerInfo().getLocation());
    }
}
