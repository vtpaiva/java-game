package Controle;

import Entidades.MyPanel;
import java.io.Serializable;



//  Classe que armazena uma instância de uma painel de jogo, o qual contém todos os dados
//  do jogo no momento em que o usuário salva o jogo.
public class DataStorage implements Serializable {
    private MyPanel gamePanel;
    
    public DataStorage(MyPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public MyPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(MyPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
