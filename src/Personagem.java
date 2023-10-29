package com.mycompany.jogobrabo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Personagem extends Entidade{
    BufferedImage rotateSprite;
    
    public int vida;
    public double angle, last, now;

    public Personagem(String path, int linha, int coluna, int vida, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, gamePanel);
        this.angle = angle;
        this.rotateSprite = sprite;
        this.vida = vida;
        this.last = this.now = System.nanoTime();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.rotateSprite, this.getX(), this.getY(), null);
    }
}
