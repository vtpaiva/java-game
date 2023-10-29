package com.mycompany.jogobrabo;

public class Projetil extends Entidade{
    public int xDeslocamento, yDeslocamento;
    public boolean heroFire;
    
    public Projetil(String path, int linha, int coluna, int xDeslocamento, int yDeslocamento, MyPanel gamePanel, boolean heroFire) {
        super(path, linha, coluna, gamePanel);
        this.heroFire = heroFire;
        
        if(xDeslocamento == 0 && yDeslocamento == 0){
            this.xDeslocamento = this.yDeslocamento = -1;
        }
        else {
            this.xDeslocamento = xDeslocamento;
            this.yDeslocamento = yDeslocamento;
        }
    }
    
    @Override
    public boolean update(){
        if(!posicaoValida(this.getX() + xDeslocamento, this.getY() + yDeslocamento)) {return false;}
        
        this.setLocation((int) (this.getX() + xDeslocamento), (int) (this.getY() + yDeslocamento));
        this.updateHitbox();
        
        if(this.heroFire) {
            for(Personagem e: this.gamePanel.inimigoAtual) {
                if(e instanceof Inimigo && this.hitbox.intersects(e.hitbox)) {
                    e.vida--;
                    return false;
                }
            }
        }
        else {
            if(this.hitbox.intersects(this.gamePanel.hero.hitbox)) {
                this.gamePanel.hero.vida--;
                return false;
            }
        }
        
        return true;
    }
}
