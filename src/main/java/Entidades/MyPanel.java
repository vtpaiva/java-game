package Entidades;

import Controle.Movimento;
import Controle.SaveLoad;
import Niveis.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;



//  Classe que representa o painel do jogo, no qual as entidades são
//  atualizadas e mostradas.
public class MyPanel extends JPanel implements Serializable{
        private Hero hero;
        private Level faseAtual;
        private ArrayList <Level> fases;
        
        private SaveLoad saveLoad;
        private Movimento observer;
        private int gameState;
        
        public MyPanel() {
            fases = new ArrayList<>();
            this.makeLevels();
            this.faseAtual = fases.get(0);
            hero = new Hero("main.png", this.faseAtual.getxSpawn(), this.faseAtual.getySpawn(), 50, Consts.TILE_WIDTH, Consts.TILE_HEIGHT, this, Math.PI / 2, 200);
            this.faseAtual.makeLevel();
            saveLoad = new SaveLoad(this);
            this.setPreferredSize(new Dimension(Consts.MAX_WIDTH * Consts.LEVEL_SCALE, Consts.MAX_HEIGHT * Consts.LEVEL_SCALE));
            this.gameState = 3;
            
            this.observer = new Movimento(this);
            this.getObserver().setListenActive(false);
            this.addKeyListener(observer);
            this.addMouseListener(observer);
            this.addMouseMotionListener(observer);
            this.setBackground(new Color(0, 102, 0));
        }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Level getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(Level faseAtual) {
        this.faseAtual = faseAtual;
    }

    public ArrayList<Level> getFases() {
        return fases;
    }

    public void setFases(ArrayList<Level> fases) {
        this.fases = fases;
    }

    public SaveLoad getSaveLoad() {
        return saveLoad;
    }

    public void setSaveLoad(SaveLoad saveLoad) {
        this.saveLoad = saveLoad;
    }

    public Movimento getObserver() {
        return observer;
    }

    public void setObserver(Movimento observer) {
        this.observer = observer;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
        
//  Método que inicializa um novo jogo ao reiniciar.
        public final void setGame() {
            fases = new ArrayList<>();
            this.makeLevels();
            this.faseAtual = fases.get(0);
            hero = new Hero("main.png", this.faseAtual.getxSpawn(), this.faseAtual.getySpawn(), 50, Consts.TILE_WIDTH, Consts.TILE_HEIGHT, this, Math.PI / 2, 200);
            this.faseAtual.makeLevel();
            saveLoad = new SaveLoad(this);
            this.setPreferredSize(new Dimension(Consts.MAX_WIDTH * Consts.LEVEL_SCALE, Consts.MAX_HEIGHT * Consts.LEVEL_SCALE));
            this.gameState = 0;
            
            this.removeKeyListener(observer);
            this.removeMouseListener(observer);
            this.removeMouseMotionListener(observer);    
            
            this.observer = new Movimento(this);
            this.addKeyListener(observer);
            this.addMouseListener(observer);
            this.addMouseMotionListener(observer);
            this.setBackground(new Color(0, 102, 0));
        }
        
//  Método que inicializa os níveis na ArrayList de níveis.
        public final void makeLevels() {
            for(int[][] i: Fases.Fases) {
                fases.add(new Level(this, i, 1, 1));
            }
        }
        
//  Métodos que validam a posição futura do herói.
        
        public int posicaoXValidaForward() {
            if((Math.abs(this.getX() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) > Consts.MAX_WIDTH * Consts.LEVEL_SCALE)) {
                return this.getX();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX()- (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10, o.getY(), o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getX();
                }
            }
            
            return (int) (this.getX() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10);
        }
        
        public int posicaoYValidaForward() {
            if((Math.abs(this.getY() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) > Consts.MAX_HEIGHT * Consts.LEVEL_SCALE)) {
                return this.getY();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX(), o.getY() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10, o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getY();
                }
            }
            
            return (int) (this.getY() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10);
        }
        
        public int posicaoXValidaBackward() {
            if((Math.abs(this.getX() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) > Consts.MAX_WIDTH * Consts.LEVEL_SCALE)) {
                return this.getX();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10, o.getY(), o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getX();
                }
            }
            
            return (int) (this.getX() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10);
        }
        
        public int posicaoYValidaBackward() {
            if((Math.abs(this.getY() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) > Consts.MAX_HEIGHT * Consts.LEVEL_SCALE)) {
                return this.getY();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX(), o.getY() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10, o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getY();
                }
            }
            
            return (int) (this.getY() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10);
        }
        
        public int posicaoXValidaRight() {
            if((Math.abs(this.getX() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) > Consts.MAX_WIDTH * Consts.LEVEL_SCALE)) {
                return this.getX();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10, o.getY(), o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getX();
                }
            }
            
            return (int) (this.getX() - (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10);
        }
        
        public int posicaoYValidaRight() {
            if((Math.abs(this.getY() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) > Consts.MAX_HEIGHT * Consts.LEVEL_SCALE)) {
                return this.getY();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX(), o.getY() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10, o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getY();
                }
            }
            
            return (int) (this.getY() + (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10);
        }
        
        public int posicaoXValidaLeft() {
            if((Math.abs(this.getX() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10) > Consts.MAX_WIDTH * Consts.LEVEL_SCALE)) {
                return this.getX();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10, o.getY(), o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getX();
                }
            }
            
            return (int) (this.getX() + (MouseInfo.getPointerInfo().getLocation().getY() - Consts.MAX_HEIGHT/2) / 10);
        }
        
        public int posicaoYValidaLeft() {
            if((Math.abs(this.getY() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10) > Consts.MAX_HEIGHT * Consts.LEVEL_SCALE)) {
                return this.getY();
            }
            
            for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                if(hero.getHitbox().intersects(new Rectangle2D.Double(o.getX(), o.getY() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10, o.getHitbox().getWidth(), o.getHitbox().getHeight()))) {
                    return this.getY();
                }
            }
            
            return (int) (this.getY() - (MouseInfo.getPointerInfo().getLocation().getX() - Consts.MAX_WIDTH/2) / 10);
        }
        
//  Métodos de movimentação do herói.
        
        public void moveForward() {
            this.setLocation(posicaoXValidaForward(), posicaoYValidaForward());
        }
        
        public void moveBackward() {
            this.setLocation(posicaoXValidaBackward(), posicaoYValidaBackward());
        }
        
        public void moveRight() {
            this.setLocation(posicaoXValidaRight(), posicaoYValidaRight());
        }
        
        public void moveLeft() {
            this.setLocation(posicaoXValidaLeft(), posicaoYValidaLeft());
        }
        
        public void addEntidade(Entidade e){
            faseAtual.getEntidadeAtual().add(e);
        }
        
        public void addObstaculo(Obstaculo o){
            faseAtual.getObstaculoAtual().add(o);
        }
        
        public void addInimigo(Inimigo i) {
            faseAtual.getInimigoAtual().add(i);
        }
        
//  Método que atualiza o estado do herói e verifica se está morto.
        public void updateHero(Graphics g) {
            if(!this.hero.update()) {
                this.gameOverScreen(g);
            }
            this.hero.paintComponent(g);
            this.hero.setNow(System.nanoTime());
        }
        
    //  Método de tela no início do jogo.
        public void titleScreen(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillRect(0, 0, Consts.MAX_WIDTH * Consts.LEVEL_SCALE, Consts.MAX_HEIGHT * Consts.LEVEL_SCALE);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
            g2.drawString("Press space to start", -this.getX() + Consts.MAX_WIDTH / Consts.LEVEL_SCALE - 135, Consts.MAX_HEIGHT / Consts.LEVEL_SCALE - this.getY());
        }
        
//  Método que progride a fase atual do painel.
        public void proximaFase() {
            if(this.fases.indexOf(this.faseAtual) + 1 < this.fases.size()) {
                this.faseAtual.getInimigoAtual().clear();
                this.faseAtual.getObstaculoAtual().clear();
                this.faseAtual.getEntidadeAtual().clear();
                this.faseAtual.getSkipAtual().clear();

                this.faseAtual = this.fases.get(this.fases.indexOf(this.faseAtual) + 1);

                this.faseAtual.makeLevel();
                this.hero.setLocation(this.faseAtual.getxSpawn(), this.faseAtual.getySpawn());
            }
            else {
                this.setGameState(1);
                this.getObserver().setListenActive(false);
            }
        }
        
//  Método de tela em caso de vitória.
        public void youWinScreen(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillRect(0, 0, Consts.MAX_WIDTH * Consts.LEVEL_SCALE, Consts.MAX_HEIGHT * Consts.LEVEL_SCALE);
            g2.setColor(Color.yellow);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
            g2.drawString("You win", this.hero.getX() - 125, this.hero.getY());
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));
            g2.setColor(Color.white);
        }
           
//  Método de tela em caso de morte.
        public void gameOverScreen(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            
            this.gameState = 2;
            this.observer.setListenActive(false);
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillRect(0, 0, Consts.MAX_WIDTH * Consts.LEVEL_SCALE, Consts.MAX_HEIGHT * Consts.LEVEL_SCALE);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
            g2.drawString("Game Over", this.hero.getX() - 125, this.hero.getY());
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30f));
            g2.drawString("Press R to restart", this.hero.getX() - 108, this.hero.getY() + 60);
        }
        
//  Método que desenha o plano de fundo.
        public void drawGrass(Graphics g) {
            for(int i = -20; i < 20; i++) {
                g.setColor(new Color(i + 20, 204 + i, 0 + i * 5 + 100));
                for (int x = 0; x < getWidth(); x += 50) {
                    int y = (int) (i / 10 * x);
                    g.fillOval(i*i * 10 + x - 5 / 2, i*i + y - 5 / 2, 5, 5);
                }
            }
        }
        
//  Método que atualiza o painel a cada frame.
        @Override
        public void paintComponent(Graphics g){
                super.paintComponent(g);
                
                this.drawGrass(g);
                
                for(Entidade e: faseAtual.getEntidadeAtual()) {
                    e.paintComponent(g);
                }

                for(Obstaculo o: faseAtual.getObstaculoAtual()) {
                    o.paintComponent(g);
                }

                for(Skip s: faseAtual.getSkipAtual()) {
                    s.paintComponent(g);
                }

                for(Inimigo i: faseAtual.getInimigoAtual()) {
                    i.setNow(System.nanoTime());
                    i.paintComponent(g);
                }
                
                if(this.gameState == 0) {
                    faseAtual.getInimigoAtual().removeIf(i -> !i.update());
                    faseAtual.getEntidadeAtual().removeIf(p -> !p.update());
                    updateHero(g);
               }
                else {
                    switch(this.gameState) {
                        case Consts.YOU_WIN -> this.youWinScreen(g);
                        case Consts.GAME_OVER -> this.gameOverScreen(g);
                        case Consts.TITLE_SCREEN -> this.titleScreen(g);
                        default -> {}
                    }
                }
            }
        }
