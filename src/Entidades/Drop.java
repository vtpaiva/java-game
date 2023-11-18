package Entidades;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa uma instância de objeto que pode ser coletado no painel do jogo,
//  representa power-ups ou decorações do mapa.
public abstract class Drop extends Entidade implements Serializable {
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
    
//  Função que rotaciona o Drop em direção ao heroi do jogo.
    public final void rotate() {
        BufferedImage rotatedImage = new BufferedImage((int) this.getHitbox().getWidth() * 2, (int) this.getHitbox().getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotatedImage.createGraphics();

        g2.translate(-rotatedImage.getWidth()/4, 0);
        g2.rotate(Math.atan2((this.getY() - this.gamePanel.getHero().getY()), this.getX() - this.gamePanel.getHero().getX()), rotatedImage.getWidth()/2, rotatedImage.getHeight()/2);
        
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(this.getSprite(), null,rotatedImage.getWidth()/4, rotatedImage.getHeight()/4);

        g2.dispose();
        
        this.setSprite(rotatedImage);
    }
    
//  Método que representa o efeito de coleta em um Drop coletável.
    public abstract void efeitoColetavel();
    
//  Método de update, executando o efeito de coleta para Drops coletáveis.
    @Override
    public boolean update() {
        if(this.coletavel && this.getHitbox().intersects(this.gamePanel.getHero().getHitbox())) {
            this.efeitoColetavel();
            return false;
        }
        return true;
    }
}
