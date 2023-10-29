package com.mycompany.jogobrabo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public abstract class Entidade extends JComponent{
    public MyPanel gamePanel;
    public BufferedImage sprite;
    public Rectangle2D hitbox;
    
    
    
    Entidade(String path, int linha, int coluna, MyPanel gamePanel) {
        this.setLocation((int) linha, (int) coluna);
        updateHitbox();
        
        this.gamePanel = gamePanel;
        
        try {
            sprite = resize(ImageIO.read(new File(path)), 50, 50);
        } catch (IOException ex) {
            Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateHitbox() {
        this.hitbox = new Rectangle2D.Float(this.getX(), this.getY(), 50, 50);
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } 
    
    public void setPosicao(int x, int y){
        this.setLocation(x, y);
    }
    
    public void moveUp(Point mouse){
        this.setLocation((int)(this.getX() + (mouse.getX() - this.getX()) / 10), 
                         (int) (this.getY() + (mouse.getY() - this.getY()) / 10));
    }
    public void moveDown(Point mouse){
        this.setLocation((int)(this.getX() - (mouse.getX() - this.getX()) / 10), 
                         (int) (this.getY() - (mouse.getY() - this.getY()) /10));
    }
    
    public boolean posicaoValida(int x, int y) {
        return (x >= 0 && y >= 0 && x <= Consts.MAX_WIDTH && y <= Consts.MAX_HEIGHT);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        MyPanel.drawChar(g, this);
    }
    
    public abstract boolean update();
}
