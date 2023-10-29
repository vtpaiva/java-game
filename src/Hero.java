package com.mycompany.jogobrabo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public final class Hero extends Personagem {
    
    public Hero(String path, int linha, int coluna, int vida, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, gamePanel, angle);
        this.rotateSprite = this.sprite;
        this.angle = 0;
    }
    
    public void rotate(Point mouse) {
        BufferedImage rotatedImage = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotatedImage.createGraphics();

        g2.rotate(Math.atan2((mouse.getY() - Consts.MAX_HEIGHT/2), mouse.getX() - Consts.MAX_WIDTH/2) + angle, rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
        
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(sprite, null, 0, 0);

        g2.dispose();
        
        rotateSprite = rotatedImage;
    }
    
    @Override
    public boolean update() {
        this.setLocation(Consts.MAX_WIDTH/2 - gamePanel.getX(), Consts.MAX_HEIGHT/2 - gamePanel.getY());
        this.updateHitbox();
        
        return vida > 0;
    }
}
