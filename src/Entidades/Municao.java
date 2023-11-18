package Entidades;

//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa um power-up de munição no jogo, adicionando munição ao herói.
public class Municao extends Drop {
    public Municao(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
        this.setColetavel(true);
    }

//  Método de efeito de adição de munição.
    @Override
    public void efeitoColetavel() {
        this.gamePanel.getHero().setAmmo(this.gamePanel.getHero().getAmmo() + ((this.gamePanel.getHero().getAmmo() > this.gamePanel.getHero().getMaxAmmo() - 100) ?
                                     this.gamePanel.getHero().getMaxAmmo() - this.gamePanel.getHero().getAmmo() : 100));
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
