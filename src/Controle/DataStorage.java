package Controle;

import Entidades.MyPanel;
import java.io.Serializable;

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
