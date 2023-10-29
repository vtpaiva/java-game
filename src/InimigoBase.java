package com.mycompany.jogobrabo;

public final class InimigoBase extends Inimigo{
    public InimigoBase(String path, int linha, int coluna, int vida, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, gamePanel, angle);
        this.updateRange();
        
        this.width = Consts.BASE_WIDTH;
        this.height = Consts.BASE_HEIGHT;
        this.rangeRadius = Consts.BASE_RANGE;
    }

    @Override
    public void ataque() {
        if(this.now - this.last >= 500000000) {
            gamePanel.addEntidade(new Projetil("caveira.png",
                                                this.getX(),
                                                this.getY(),
                                                (int) (gamePanel.hero.getX() - this.getX())/5,
                                                (int) (gamePanel.hero.getY() - this.getY())/5,
                                                gamePanel, false));
            this.last = System.nanoTime();
        }
    }
}
