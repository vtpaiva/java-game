package Entidades;

import Niveis.*;
import java.io.Serializable;

public class InimigoSniper extends Inimigo implements Serializable{
 
    public InimigoSniper(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, entityWidth, entityHeight, gamePanel, angle);
        this.setWidth(Consts.SNIPER_WIDTH);
        this.setHeight(Consts.SNIPER_HEIGHT);
        this.setRangeRadius(Consts.SNIPER_RANGE);
        this.setVisionRadius(Consts.SNIPER_VISION);
        
        this.updateRange();
        this.updateVision();
    }

    @Override
    public void ataque() {
        if(this.getNow() - this.getLast() >= 2000000000L) {
            gamePanel.addEntidade(new Projetil("caveira.png",
                                                this.getX(),
                                                this.getY(),
                                                (int) (gamePanel.getHero().getX() - this.getX())/2,
                                                (int) (gamePanel.getHero().getY() - this.getY())/2,
                                                gamePanel, false));
            this.setLast(System.nanoTime());
        }
    }
}
