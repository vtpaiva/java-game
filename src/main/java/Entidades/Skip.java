package Entidades;

import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa uma entidade de progressão de fase interior ao painel.
public class Skip extends Obstaculo implements Serializable{
    public Skip(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
    }
}
