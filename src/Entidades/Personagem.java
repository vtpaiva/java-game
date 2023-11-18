package Entidades;

import Niveis.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

public abstract class Personagem extends Entidade implements Serializable{
    private transient BufferedImage rotateSprite;
    
    private int vida, vidaMax;
    private double angle, last, now;

    public Personagem(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
        this.angle = angle;
        this.setRotateSprite(this.getSprite());
        this.vida = this.vidaMax = vida;
        this.last = this.now = System.nanoTime();
    }

    public BufferedImage getRotateSprite() {
        return rotateSprite;
    }

    public void setRotateSprite(BufferedImage rotateSprite) {
        this.rotateSprite = rotateSprite;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public double getNow() {
        return now;
    }

    public void setNow(double now) {
        this.now = now;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.rotateSprite, this.getX(), this.getY(), null);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(this.getX() - 1, this.getY() - Consts.TILE_HEIGHT - 1, Consts.TILE_WIDTH + 2, 7);
        g2.setColor(Color.red);
        g2.fillRect(this.getX(), this.getY() - Consts.TILE_HEIGHT, Consts.TILE_WIDTH * this.vida/this.vidaMax, 5);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (this.getSprite() != null) {
            ImageIO.write(this.getSprite(), "png", out); // Você pode usar outro formato de imagem se preferir
        }
        if (this.rotateSprite != null) {
            ImageIO.write(rotateSprite, "png", out); // Você pode usar outro formato de imagem se preferir
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.setRotateSprite(ImageIO.read(in));
        this.rotateSprite = ImageIO.read(in);
    }
    
    @Override
    public boolean update() {
        return vida > 0;
    }
}
