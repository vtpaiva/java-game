package Entidades;

import Niveis.*;
import java.io.Serializable;

public class Skip extends Obstaculo implements Serializable{
    public Skip(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
    }
}
