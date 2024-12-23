package Entidades;

import Niveis.*;
import java.io.Serializable;



//  Classe que representa um projétil no jogo, tendo velocidades iniciais e uma
//  variável inidicativa da origem do tiro, pelo herói ou por inimigos.
public class Projetil extends Entidade implements Serializable{
    private int xDeslocamento, yDeslocamento;
    private boolean heroFire;
    
    public Projetil(String path, int linha, int coluna, int xDeslocamento, int yDeslocamento, MyPanel gamePanel, boolean heroFire) {
        super(path, linha, coluna, Consts.PROJ_BASE, Consts.PROJ_BASE, gamePanel);
        this.heroFire = heroFire;
        
        if(xDeslocamento == 0 && yDeslocamento == 0){
            this.xDeslocamento = this.yDeslocamento = -1;
        }
        else {
            this.xDeslocamento = xDeslocamento;
            this.yDeslocamento = yDeslocamento;
        }
    }

    public int getxDeslocamento() {
        return xDeslocamento;
    }

    public void setxDeslocamento(int xDeslocamento) {
        this.xDeslocamento = xDeslocamento;
    }

    public int getyDeslocamento() {
        return yDeslocamento;
    }

    public void setyDeslocamento(int yDeslocamento) {
        this.yDeslocamento = yDeslocamento;
    }

    public boolean isHeroFire() {
        return heroFire;
    }

    public void setHeroFire(boolean heroFire) {
        this.heroFire = heroFire;
    }
    
//  Atualiza a posição do projétil a cada frame caso não colida com alguma entidade.
    @Override
    public boolean update(){
        if(!posicaoValida(this.getX() + xDeslocamento, this.getY() + yDeslocamento)) {return false;}
        
        this.setLocation((int) (this.getX() + xDeslocamento), (int) (this.getY() + yDeslocamento));
        this.updateHitbox(Consts.PROJ_BASE, Consts.PROJ_BASE);
        
        for(Obstaculo o: this.gamePanel.getFaseAtual().getObstaculoAtual()) {
            if(this.getHitbox().intersects(o.getHitbox())) {
                return false;
            }
        }
        
        if(this.heroFire) {
            for(Inimigo i: this.gamePanel.getFaseAtual().getInimigoAtual()) {
                if(this.getHitbox().intersects(i.getHitbox())) {
                    i.setVida(i.getVida() - 1);
                    return false;
                }
            }
        }
        else {
            if(this.getHitbox().intersects(this.gamePanel.getHero().getHitbox())) {
                this.gamePanel.getHero().setVida(this.gamePanel.getHero().getVida() - 1);
                return false;
            }
        }
        
        return true;
    }
}
