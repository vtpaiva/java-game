package Controle;

import Entidades.MyPanel;
import Entidades.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que performar as operações de salvar e carregar o jogo.
public class SaveLoad implements Serializable {
    private MyPanel gamePanel;

    public SaveLoad(MyPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public MyPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(MyPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
//  Método que salva o painel de jogo em um arquivo "save.dat".
    public void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")))) {
            DataStorage ds = new DataStorage(this.gamePanel);
            
            oos.writeObject(ds);
        } catch (IOException e) {
            e.getMessage();
        }
    }

//  Método que carrega o arquivo "save.dat" e implementa o estado atual do painel salvo no
//  arquivo ao painel de jogo atual.
    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")))) {
            DataStorage ds = (DataStorage) ois.readObject();
            
            gamePanel.setLocation(ds.getGamePanel().getX(), ds.getGamePanel().getY());
            gamePanel.setFaseAtual(ds.getGamePanel().getFaseAtual());
            
            for(Entidade e: gamePanel.getFaseAtual().getEntidadeAtual()) {
                if(e instanceof Drop drop) {
                    drop.setLast(System.nanoTime());
                }
            }
            
            for(Inimigo i: gamePanel.getFaseAtual().getInimigoAtual()) {
                i.setRotateSprite(i.getSprite());
            }
            
            gamePanel.setHero(ds.getGamePanel().getHero());
            gamePanel.getHero().setRotateSprite(gamePanel.getHero().getSprite());
            gamePanel.getHero().setGamePanel(gamePanel);
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
    }
}
