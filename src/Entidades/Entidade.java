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

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa uma instância de entidade no painel de jogo, podendo ser o herói,
//  inimigos, obstaculos ou Drops. Toda entidade possui uma hitbox, uma representação visual e
//  contato com o painel de jogo no qual está inserida.
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
    
//  Método que atualiza a posição da hitbox da entidade conforme a instância se movimenta
//  no painel.
    public final void updateHitbox(int eneityWidth, int entityHeight) {
        this.hitbox = new Rectangle2D.Float(this.getX(), this.getY(), eneityWidth, entityHeight);
    }
 
//  Método que redimensiona um sprite.    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } 
    
//  Método que valida a posição de um projétil no jogo.
    public boolean posicaoValida(int x, int y) {
        return (x >= 0 && y >= 0 && x <= Consts.MAX_WIDTH * Consts.LEVEL_SCALE && y <= Consts.MAX_HEIGHT * Consts.LEVEL_SCALE);
    }
    
//  Métodos de salvamento e carregamento das entidades do painel de jogo.    

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
    
//  Método de update de uma entidade, o qual representa a mudança do estado de uma entidade
//  ao longo do tempo, caso retorne 'false', a entidade é removida do painel de jogo, caso contrário,
//  performa a mudança de estado e continua na próxima atualização.
    public abstract boolean update();
}
