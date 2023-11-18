package Entidades;

import Niveis.*;
import java.io.Serializable;

public class Obstaculo extends Entidade implements Serializable{
    public Obstaculo(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
    }

    public boolean update() {
        return true;
    }
}
