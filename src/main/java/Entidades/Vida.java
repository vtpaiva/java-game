package Entidades;



//  Classe que representa um power-up de vida no jogo.
public class Vida extends Drop {
    public Vida(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
        this.setColetavel(true);
    }

    @Override
    public void efeitoColetavel() {
        this.gamePanel.getHero().setVida(this.gamePanel.getHero().getVida() + ((this.gamePanel.getHero().getVida() > this.gamePanel.getHero().getVidaMax() - 20) ?
                                     this.gamePanel.getHero().getVidaMax() - this.gamePanel.getHero().getVida() : 20)); 
    }
    
    @Override
    public boolean update() {
        if(this.isColetavel() && this.getHitbox().intersects(this.gamePanel.getHero().getHitbox())) {
            this.efeitoColetavel();
            return false;
        }
        
        return System.nanoTime() - this.getLast() <= 10000000000L;
    }
}
