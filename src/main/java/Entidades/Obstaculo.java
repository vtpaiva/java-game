package Entidades;

import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa um obstáculo instransponível no painel de jogo.
public class Obstaculo extends Entidade implements Serializable{
    public Obstaculo(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
    }

    @Override
    public boolean update() {
        return true;
    }
}
