package Entidades;

import Niveis.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public abstract class Drop extends Entidade {
    private double last;
    private boolean coletavel;

    public Drop(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
        this.last = System.nanoTime();
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public boolean isColetavel() {
        return coletavel;
    }

    public void setColetavel(boolean coletavel) {
        this.coletavel = coletavel;
    }
    
    public final void rotate() {
        BufferedImage rotatedImage = new BufferedImage(this.getSprite().getWidth(), this.getSprite().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotatedImage.createGraphics();

        g2.rotate(Math.atan2((this.getY() - this.gamePanel.getHero().getY()), this.getX() - this.gamePanel.getHero().getX()), rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(this.getSprite(), null, 0, 0);

        g2.dispose();
        
        this.setSprite(rotatedImage);
    }
    
    public abstract void efeitoColetavel();
    
    @Override
    public boolean update() {
        if(this.coletavel && this.getHitbox().intersects(this.gamePanel.getHero().getHitbox())) {
            this.efeitoColetavel();
            return false;
        }
        return true;
    }
}
