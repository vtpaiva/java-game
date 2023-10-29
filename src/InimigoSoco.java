package com.mycompany.jogobrabo;

import java.awt.geom.Ellipse2D;

public class InimigoSoco extends Inimigo{
    public InimigoSoco(String path, int linha, int coluna, int vida, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, gamePanel, angle);
        this.width = Consts.PUNCH_WIDTH;
        this.height = Consts.PUNCH_HEIGHT;
        this.rangeRadius = Consts.PUNCH_RANGE;
    }

    @Override
    public void ataque() {
        if(this.now - this.last >= 100000000) {
            punchHero();
            this.last = System.nanoTime();
        }
    }
    
    public void punchHero() {
            this.gamePanel.hero.vida--;
    }
}
