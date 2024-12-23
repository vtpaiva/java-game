package Entidades;

import Niveis.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.Serializable;



//  Classe que representa um inimigo do painel de jogo, o qual possui uma alcance de
//  de tiro, um alcance de visão, o qual de tecta o herói e uma variável que
//  representa o estado de caça do herói.
public abstract class Inimigo extends Personagem implements Serializable{
    private Ellipse2D range, vision;
    private String deadSprite;
    private int width, height, rangeRadius, visionRadius;
    private boolean hunt;
    
    public Inimigo(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, entityWidth, entityHeight, gamePanel, angle);
       
        this.updateRange();
        this.updateVision();
    }

    public Ellipse2D getRange() {
        return range;
    }

    public void setRange(Ellipse2D range) {
        this.range = range;
    }

    public Ellipse2D getVision() {
        return vision;
    }

    public void setVision(Ellipse2D vision) {
        this.vision = vision;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRangeRadius() {
        return rangeRadius;
    }

    public void setRangeRadius(int rangeRadius) {
        this.rangeRadius = rangeRadius;
    }

    public int getVisionRadius() {
        return visionRadius;
    }

    public void setVisionRadius(int visionRadius) {
        this.visionRadius = visionRadius;
    }

    public boolean isHunt() {
        return hunt;
    }

    public void setHunt(boolean hunt) {
        this.hunt = hunt;
    }

    public String getDeadSprite() {
        return deadSprite;
    }

    public final void setDeadSprite(String deadSprite) {
        this.deadSprite = deadSprite;
    }
    
//  Métodos que atualizam a posição das hitbox dos alcances de visão e de tiro.
    
    public final void updateRange() {
        this.range = new Ellipse2D.Double(this.getX() - rangeRadius/2 + width/2, this.getY() - rangeRadius/2 + height/2, rangeRadius, rangeRadius);
    }
    
    public final void updateVision() {
        this.vision = new Ellipse2D.Double(this.getX() - visionRadius/2 + width/2, this.getY() - visionRadius/2 + height/2, visionRadius, visionRadius);
    }
    
//  Método que rotaciona o inimigo em relação ao herói do painel de jogo.
    public void rotate() {
        BufferedImage rotatedImage = new BufferedImage(this.getSprite().getWidth(), this.getSprite().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotatedImage.createGraphics();

        g2.rotate(Math.atan2((this.getY() - this.gamePanel.getHero().getY()), this.getX() - this.gamePanel.getHero().getX()) + this.getAngle(), rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(this.getSprite(), null, 0, 0);

        g2.dispose();
        
        this.setRotateSprite(rotatedImage);
    }
    
//  Métodos que validam a posição futura do inimigo.
    
    public int posicaoXValida() {
            Rectangle2D novaPos = new Rectangle2D.Double(this.getX() + (gamePanel.getHero().getX() - this.getX())/40, this.getY(), this.getHitbox().getWidth(), this.getHitbox().getHeight());
        
            for(Obstaculo o: this.gamePanel.getFaseAtual().getObstaculoAtual()) {
                if(o.getHitbox().intersects(novaPos)) {
                    return this.getX();
                }
            }
            
            for(Personagem i: this.gamePanel.getFaseAtual().getInimigoAtual()) {
                if(i != this && i.getHitbox().intersects(novaPos)) {
                    return this.getX();
                }
            }
            
            return (int) (this.getX() + (gamePanel.getHero().getX() - this.getX())/40);
    }
    
    public int posicaoYValida() {
        Rectangle2D novaPos = new Rectangle2D.Double(this.getX(), this.getY() + (gamePanel.getHero().getY() - this.getY())/40, this.getHitbox().getWidth(), this.getHitbox().getHeight());
        
            for(Obstaculo o: this.gamePanel.getFaseAtual().getObstaculoAtual()) {
                if(o.getHitbox().intersects(novaPos)) {
                    return this.getY();
                }
            }
            
            for(Personagem i: this.gamePanel.getFaseAtual().getInimigoAtual()) {
                if(i != this && i.getHitbox().intersects(novaPos)) {
                    return this.getY();
                }
            }
            
            return (int) (this.getY() + (gamePanel.getHero().getY() - this.getY())/40);
    }
    
    public boolean posicaoValida() {
        return !(this.range.intersects(this.gamePanel.getHero().getHitbox()));
    }
    
//  Método que verifica se o inimigo está morto e, caso esteja, há uma probabilidade
//  de que seja gerado um power-up e é adicionado ao mapa o cadáver do inimigo.
    private boolean inimigoMorto() {
        if(this.getVida() > 0) {return true;}
        this.gamePanel.getFaseAtual().getEntidadeAtual().add(new Corpo(this.getDeadSprite(), 
                                                            this.getX(), 
                                                           this.getY(), 
                                                                 Consts.TILE_WIDTH * 2, 
                                                       Consts.TILE_HEIGHT, 
                                                         this.gamePanel));
        switch((new Random()).nextInt() % 4) {
            case 0 -> this.gamePanel.getFaseAtual().getEntidadeAtual().add(new Vida("bandage.png",
                                             this.getX(),
                                             this.getY(),
                                             Consts.TILE_WIDTH,
                                             Consts.TILE_HEIGHT,
                                             this.gamePanel));
            case 1 -> this.gamePanel.getFaseAtual().getEntidadeAtual().add(new Municao("ammo.png",
                                             this.getX(),
                                             this.getY(),
                                             Consts.TILE_WIDTH,
                                             Consts.TILE_HEIGHT,
                                             this.gamePanel));
            default -> {
            }
            
        }
        return false;
    }
    
//  Método abstrato de ataque de um inimigo.
    public abstract void ataque();
    
    public void atira() {
            gamePanel.addEntidade(new Projetil("bullet.png",
                                                this.getX(),
                                                this.getY(),
                                                (int) (gamePanel.getHero().getX() - this.getX())/2,
                                                (int) (gamePanel.getHero().getY() - this.getY())/2,
                                                gamePanel, false));
            
            this.setLast(System.nanoTime());
    }
    
    @Override
    public boolean update() {
        if(hunt) {
            this.rotate();
            if(posicaoValida()) {
                this.setLocation(posicaoXValida(), posicaoYValida());
            }
            if(this.range.intersects(this.gamePanel.getHero().getHitbox())) {
                this.ataque();
            }
            this.updateHitbox((int) this.getHitbox().getWidth(),(int) this.getHitbox().getHeight());
            this.updateRange();
            this.updateVision();
        }
        else {
            if(this.vision.intersects(this.gamePanel.getHero().getHitbox())) {
                for(Obstaculo o: this.gamePanel.getFaseAtual().getObstaculoAtual()) {
                    if((new Line2D.Double(this.getX(), this.getY(), this.gamePanel.getHero().getX(), this.gamePanel.getHero().getY())).intersects(o.getHitbox())) {
                        return this.inimigoMorto();
                    }
                }
                
                hunt = true;
            }
        }
        
        return this.inimigoMorto();
    }
}
