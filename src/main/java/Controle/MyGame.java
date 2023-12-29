package Controle;

import Niveis.*;
import Entidades.MyPanel;
import java.io.Serializable;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa o jogo, unindo a tela ao painel e executando uma Thread
//  de execução do jogo.
public final class MyGame implements Runnable, Serializable{
    private MyFrame gameFrame;
    private MyPanel gamePanel;
    private Thread gameThread;
    
    public MyGame() {
        gamePanel = new MyPanel();
        gameFrame = new MyFrame(gamePanel);
        startGameThread();
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
    }

    public MyFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(MyFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public MyPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(MyPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000 / Consts.MAX_FPS;
        double lastCheck = System.nanoTime();
        
        while(gameThread != null){
            if(System.nanoTime() - lastCheck >= timePerFrame) {
                gamePanel.repaint();
                
                lastCheck = System.nanoTime();
            }
        }
    }
}
