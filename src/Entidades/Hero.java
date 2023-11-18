package Entidades;

import Niveis.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public final class Hero extends Personagem implements Serializable{
    private int ammo, maxAmmo;
    
    public Hero(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle, int ammo) {
        super(path, linha, coluna, vida, entityWidth, entityHeight, gamePanel, angle);
        this.ammo = this.maxAmmo = ammo;
        this.setRotateSprite(this.getSprite());
        this.setAngle(0);
        this.gamePanel.setLocation(linha * Consts.TILE_HEIGHT, coluna * Consts.TILE_WIDTH);
    }
    
    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }
    
    public void rotate(Point mouse) {
        BufferedImage rotatedImage = new BufferedImage(this.getSprite().getWidth(), this.getSprite().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotatedImage.createGraphics();

        g2.rotate(Math.atan2((mouse.getY() - Consts.MAX_HEIGHT/2), mouse.getX() - Consts.MAX_WIDTH/2) + this.getAngle(), rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(this.getSprite(), null, 0, 0);

        g2.dispose();
        
        this.setRotateSprite(rotatedImage);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.getRotateSprite(), this.getX(), this.getY(), null);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(this.getX() - 1, this.getY() - Consts.TILE_HEIGHT - 10, Consts.TILE_WIDTH + 2, 7);
        g2.setColor(Color.green);
        g2.fillRect(this.getX(), this.getY() - Consts.TILE_HEIGHT - 9, Consts.TILE_WIDTH * this.getVida()/this.getVidaMax(), 5);
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(this.getX() - 1, this.getY() - Consts.TILE_HEIGHT - 1, Consts.TILE_WIDTH + 2, 7);
        g2.setColor(Color.yellow);
        g2.fillRect(this.getX(), this.getY() - Consts.TILE_HEIGHT, Consts.TILE_WIDTH * this.ammo/this.maxAmmo, 5);
    }
    
    public void soca() {
        if(this.getNow() - this.getLast() > 500000000) {
            Rectangle2D hitPunch = new Rectangle2D.Float((int) MouseInfo.getPointerInfo().getLocation().getX() - this.gamePanel.getX(), 
                                                         (int) MouseInfo.getPointerInfo().getLocation().getY() - this.gamePanel.getY(), 
                                                         Consts.TILE_WIDTH, Consts.TILE_HEIGHT);

            for(Personagem i: this.gamePanel.getFaseAtual().getInimigoAtual()) {
                if(hitPunch.intersects(i.getHitbox())) {
                    i.setVida(i.getVida() - 1);
                }
            }
            
            this.setLast(System.nanoTime());
        }
    }
    
    public void atira() {
        if(this.ammo > 0 && gamePanel.getHero().getNow() - gamePanel.getHero().getLast() > 50000000) {
            this.gamePanel.addEntidade(new Projetil(
                                   "caveira.png",
                                   this.getX(),
                                  this.getY(),
                                        (int) (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2)/5,
                                        (int) (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2)/5,
                                this.gamePanel, true));
            this.setLast(System.nanoTime());
            this.ammo--;
        }
    }
    
    @Override
    public boolean update() {
        this.setLocation(Consts.MAX_WIDTH/2 - gamePanel.getX(), Consts.MAX_HEIGHT/2 - gamePanel.getY());
        this.updateHitbox((int) this.getHitbox().getWidth(), (int) this.getHitbox().getHeight());
        
        if(this.gamePanel.getFaseAtual().getInimigoAtual().isEmpty()) {
            for(Skip s: this.gamePanel.getFaseAtual().getSkipAtual()) {
                if(this.getHitbox().intersects(s.getHitbox())) {
                    this.gamePanel.proximaFase();
                }
            }
        }
        
        return this.getVida() > 0;
    }
}
