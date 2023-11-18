package Entidades;

import Niveis.*;
import java.io.Serializable;

public final class InimigoBase extends Inimigo implements Serializable{
    public InimigoBase(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, entityWidth, entityHeight, gamePanel, angle);
        this.setWidth(Consts.BASE_WIDTH);
        this.setHeight(Consts.BASE_HEIGHT);
        this.setRangeRadius(Consts.BASE_RANGE);
        this.setVisionRadius(Consts.BASE_VISION);
        
        this.updateRange();
        this.updateVision();
    }

    @Override
    public void ataque() {
        if(this.getNow() - this.getLast() >= 500000000) {
            gamePanel.addEntidade(new Projetil("caveira.png",
                                                this.getX(),
                                                this.getY(),
                                                (int) (gamePanel.getHero().getX() - this.getX())/5,
                                                (int) (gamePanel.getHero().getY() - this.getY())/5,
                                                gamePanel, false));
            this.setLast(System.nanoTime());
        }
    }
}
