package com.mycompany.jogobrabo;

public final class MyGame implements Runnable{
    public MyFrame gameFrame;
    public MyPanel gamePanel;
    public Thread gameThread;
    
    MyGame() {
        gamePanel = new MyPanel();
        gameFrame = new MyFrame(gamePanel);
        startGameThread();
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
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
