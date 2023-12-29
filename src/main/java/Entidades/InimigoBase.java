package Entidades;

import Niveis.*;
import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa uma instância de inimigo base no jogo.
public final class InimigoBase extends Inimigo implements Serializable{
    public InimigoBase(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, entityWidth, entityHeight, gamePanel, angle);
        
        this.setDeadSprite("dead.png");
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
            this.atira();
        }
    }
}
