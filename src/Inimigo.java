package com.mycompany.jogobrabo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public abstract class Inimigo extends Personagem{
    public Ellipse2D range;
    public int width, height, rangeRadius;
    
    public Inimigo(String path, int linha, int coluna, int vida, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, gamePanel, angle);
        this.updateRange();
    }
    
    public void updateRange() {
        this.range = new Ellipse2D.Double(this.getX() - rangeRadius/2 + width/2, this.getY() - rangeRadius/2 + height/2, rangeRadius, rangeRadius);
    }
    
    public void rotate() {
        BufferedImage rotatedImage = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotatedImage.createGraphics();

        g2.rotate(Math.atan2((this.gamePanel.hero.getY() - Consts.MAX_HEIGHT/2), this.gamePanel.hero.getX() - Consts.MAX_WIDTH/2) + angle, rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
        
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(sprite, null, 0, 0);

        g2.dispose();
        
        rotateSprite = rotatedImage;
    }
    
    public boolean posicaoValida() {
        return !(this.range.intersects(this.gamePanel.hero.hitbox));
    }
    
    public abstract void ataque();
    
    @Override
    public boolean update() {
        if(this.posicaoValida()) {
            this.setLocation(this.getX() + (gamePanel.hero.getX() - this.getX())/40,
                             this.getY() + (gamePanel.hero.getY() - this.getY())/40);
        }
        
        this.updateHitbox();
        this.updateRange();
        
        if(this.range.intersects(this.gamePanel.hero.hitbox)) {
            this.ataque();
        }
        
        this.rotate();
        
        return vida > 0;
    }
}
