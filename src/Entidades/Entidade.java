package Entidades;

import Niveis.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public abstract class Entidade extends JComponent implements Serializable{
    protected MyPanel gamePanel;
    private transient BufferedImage sprite;
    private Rectangle2D hitbox;
    
    public Entidade(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        this.setLocation((int) linha, (int) coluna);
        updateHitbox(entityWidth, entityHeight);
        
        this.gamePanel = gamePanel;
        
        try {
            sprite = resize(ImageIO.read(new File(path)), entityWidth, entityHeight);
        } catch (IOException ex) {
            Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MyPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(MyPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public Rectangle2D getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D hitbox) {
        this.hitbox = hitbox;
    }
    
    public final void updateHitbox(int eneityWidth, int entityHeight) {
        this.hitbox = new Rectangle2D.Float(this.getX(), this.getY(), eneityWidth, entityHeight);
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } 
    
    public boolean posicaoValida(int x, int y) {
        return (x >= 0 && y >= 0 && x <= Consts.MAX_WIDTH * Consts.LEVEL_SCALE && y <= Consts.MAX_HEIGHT * Consts.LEVEL_SCALE);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (this.sprite != null) {
            ImageIO.write(sprite, "png", out); // Você pode usar outro formato de imagem se preferir
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.sprite = ImageIO.read(in);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.sprite, this.getX(), this.getY(), null);
    }
    
    public abstract boolean update();
}
