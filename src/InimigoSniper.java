package com.mycompany.jogobrabo;

public class InimigoSniper extends Inimigo{

    public InimigoSniper(String path, int linha, int coluna, int vida, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, gamePanel, angle);
        
        this.width = Consts.SNIPER_WIDTH;
        this.height = Consts.SNIPER_HEIGHT;
        this.rangeRadius = Consts.SNIPER_RANGE;
    }

    @Override
    public void ataque() {
        if(this.now - this.last >= 2000000000L) {
            gamePanel.addEntidade(new Projetil("caveira.png",
                                                this.getX(),
                                                this.getY(),
                                                (int) (gamePanel.hero.getX() - this.getX())/2,
                                                (int) (gamePanel.hero.getY() - this.getY())/2,
                                                gamePanel, false));
            this.last = System.nanoTime();
        }
    }
}
