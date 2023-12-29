package Entidades;

import Niveis.*;
import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa um instância de inimigo sniper do jogo, possuindo 
//  alcance e tempo de recarregamento maiorss.
public class InimigoSniper extends Inimigo implements Serializable{
 
    public InimigoSniper(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, entityWidth, entityHeight, gamePanel, angle);
        
        this.setDeadSprite("swatDead.png");
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
            this.atira();
        }
    }
}
