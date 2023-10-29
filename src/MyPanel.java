package com.mycompany.jogobrabo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
        public Hero hero;
        public ArrayList <Inimigo> inimigoAtual;
        public ArrayList <Entidade> entidadeAtual;
        
        MyPanel(){            
            entidadeAtual = new ArrayList<>();
            inimigoAtual = new ArrayList<>();
            hero = new Hero("main.png", 500/2, 300/2, 100000,this, Math.PI/2);
            
            Movimento observer = new Movimento(this);
            this.addKeyListener(observer);
            this.addMouseListener(observer);
            this.addMouseMotionListener(observer);
            this.setBackground(Color.BLACK);
        }
        
        public void moveForward() {
            this.setLocation(this.getX() - (int) ((Math.abs(this.getX() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) <= Consts.MAX_WIDTH/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) : 0), 
                           this.getY() - (int) ((Math.abs(this.getY() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) <= Consts.MAX_HEIGHT/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) : 0));
        }
        
        public void moveBackward() {
            this.setLocation(this.getX() + (int) ((Math.abs(this.getX() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) <= Consts.MAX_WIDTH/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) : 0), 
                           this.getY() + (int) ((Math.abs(this.getY() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) <= Consts.MAX_HEIGHT/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) : 0));
        }
        
        public void moveRight() {
            this.setLocation(this.getX() - (int) ((Math.abs(this.getX() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) <= Consts.MAX_WIDTH/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) : 0), 
                           this.getY() + (int) ((Math.abs(this.getY() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) <= Consts.MAX_HEIGHT/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) : 0));
        }
        
        public void moveLeft() {
            this.setLocation(this.getX() + (int) ((Math.abs(this.getX() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) <= Consts.MAX_WIDTH/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) : 0), 
                           this.getY() - (int) ((Math.abs(this.getY() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) <= Consts.MAX_HEIGHT/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) : 0));
        }
        
        public int posicaoXValida() {
            return (int) ((Math.abs(this.getX() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) <= Consts.MAX_WIDTH/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) : 0);
        }
        
        public int posicaoYValida() {
            return (int) ((Math.abs(this.getY() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) <= Consts.MAX_HEIGHT/2) ?
                         ((MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) : 0);
        }
        
        public void addEntidade(Entidade p){
            entidadeAtual.add(p);
        }
        
        public void addInimigo(Inimigo i) {
            inimigoAtual.add(i);
        }
        
        public void updateHero(Graphics g) {
            this.hero.update();
            this.hero.paintComponent(g);
            this.hero.now = System.nanoTime();
        }
        
        public void drawBackground(Graphics g, BufferedImage background){
            g.drawImage(background, 0, 0, null);
        }
        
        public static void drawChar(Graphics g, Entidade p){
            g.drawImage(p.sprite, p.getX(), p.getY(), null);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            entidadeAtual.removeIf(e -> !e.update());
            inimigoAtual.removeIf(i -> !i.update());
            
            updateHero(g);
            
            for(Inimigo i: inimigoAtual) {
                i.now = System.nanoTime();
                i.paintComponent(g);
            }
            
            for(Entidade e: entidadeAtual) {
                e.paintComponent(g);
            }
        }
}
