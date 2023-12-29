package Niveis;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

import Entidades.MyPanel;
import Entidades.*;
import java.io.Serializable;
import java.util.ArrayList;

//  Classe que representa uma fase no jogo, com uma matriz que representa o mapa.
public class Level implements Serializable{
        private MyPanel panel;
        private int xSpawn, ySpawn;
        private int[][] levelMatrix;
    
        private ArrayList <Inimigo> inimigoAtual;
        private ArrayList <Obstaculo> obstaculoAtual;
        private ArrayList <Entidade> entidadeAtual;
        private ArrayList <Skip> skipAtual;
        
        public Level(MyPanel panel, int[][] levelMatrix, int xSpawn, int ySpawn) {
            this.panel = panel;
            this.levelMatrix = levelMatrix;
            this.xSpawn = xSpawn;
            this.ySpawn = ySpawn;
            
            inimigoAtual = new ArrayList<>();
            obstaculoAtual = new ArrayList<>();
            entidadeAtual = new ArrayList<>();
            skipAtual = new ArrayList<>();
        }

    public MyPanel getPanel() {
        return panel;
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }

    public int getxSpawn() {
        return xSpawn;
    }

    public void setxSpawn(int xSpawn) {
        this.xSpawn = xSpawn;
    }

    public int getySpawn() {
        return ySpawn;
    }

    public void setySpawn(int ySpawn) {
        this.ySpawn = ySpawn;
    }

    public int[][] getLevelMatrix() {
        return levelMatrix;
    }

    public void setLevelMatrix(int[][] levelMatrix) {
        this.levelMatrix = levelMatrix;
    }

    public ArrayList<Inimigo> getInimigoAtual() {
        return inimigoAtual;
    }

    public void setInimigoAtual(ArrayList<Inimigo> inimigoAtual) {
        this.inimigoAtual = inimigoAtual;
    }

    public ArrayList<Obstaculo> getObstaculoAtual() {
        return obstaculoAtual;
    }

    public void setObstaculoAtual(ArrayList<Obstaculo> obstaculoAtual) {
        this.obstaculoAtual = obstaculoAtual;
    }

    public ArrayList<Entidade> getEntidadeAtual() {
        return entidadeAtual;
    }

    public void setEntidadeAtual(ArrayList<Entidade> entidadeAtual) {
        this.entidadeAtual = entidadeAtual;
    }

    public ArrayList<Skip> getSkipAtual() {
        return skipAtual;
    }

    public void setSkipAtual(ArrayList<Skip> skipAtual) {
        this.skipAtual = skipAtual;
    }
        
        public void apagaFase() {
            inimigoAtual.clear();
            obstaculoAtual.clear();
            entidadeAtual.clear();
            skipAtual.clear();
        }
        
//  Método que altera o painel com que as entidades do jogo têm contato.
        public void resetLevel(MyPanel gamePanel) {
            for(Entidade e: this.inimigoAtual) {
                e.setGamePanel(gamePanel);
            }
            
            for(Entidade e: this.entidadeAtual) {
                e.setGamePanel(gamePanel);
            }
            
            for(Entidade e: this.obstaculoAtual) {
                e.setGamePanel(gamePanel);
            }
            
            for(Entidade e: this.skipAtual) {
                e.setGamePanel(gamePanel);
            }
        }
        
//  Método que adiciona os elementos ao mapa de acordo com a matriz de mapa.
        public void makeLevel() {
            for(int i = 0; i < Consts.LEVEL_SCALE * Consts.TILES_Y; i++) {
                for(int j = 0; j < Consts.LEVEL_SCALE * Consts.TILES_X; j++) {
                    entityInt(this.levelMatrix[i][j], j, i);
                }
            }
        }
        
//  Função que relaciona um inteiro na matriz do mapa com uma entidade.
        public void entityInt(int i, int linha, int coluna) {
            switch(i){
                case 1:
                    obstaculoAtual.add(new Obstaculo("paredeH.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 2:
                    obstaculoAtual.add(new Obstaculo("paredeV.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                                                 break;
                case 3:
                    obstaculoAtual.add(new Obstaculo("cantoOS.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 4:
                    obstaculoAtual.add(new Obstaculo("cantoNO.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 5:
                    obstaculoAtual.add(new Obstaculo("cantoLN.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 6:
                    obstaculoAtual.add(new Obstaculo("cantoLS.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 7:
                    obstaculoAtual.add(new Obstaculo("beiraD.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 8:
                    obstaculoAtual.add(new Obstaculo("beiraE.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 9:
                    obstaculoAtual.add(new Obstaculo("beiraB.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 10:
                    obstaculoAtual.add(new Obstaculo("beiraC.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 11:
                    inimigoAtual.add(new InimigoBase("base.png", 
                                                    linha * Consts.TILE_HEIGHT, 
                                                    coluna * Consts.TILE_WIDTH, 
                                                    3, Consts.TILE_HEIGHT, Consts.TILE_WIDTH, panel, Math.PI));
                    break;
                case 12:
                    inimigoAtual.add(new Boss("boss.png", 
                                                  linha * Consts.TILE_HEIGHT, 
                                                  coluna * Consts.TILE_WIDTH, 
                                                  50, Consts.TILE_WIDTH, Consts.TILE_HEIGHT, panel, Math.PI));
                    break;
                case 13:
                    inimigoAtual.add(new InimigoSniper("sniper.png", 
                                                    linha * Consts.TILE_HEIGHT, 
                                                    coluna * Consts.TILE_WIDTH, 
                                                    3, Consts.TILE_HEIGHT, Consts.TILE_WIDTH, panel, Math.PI));
                    break;
                case 14:
                    obstaculoAtual.add(new Obstaculo("bush.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_HEIGHT, 
                                               Consts.TILE_WIDTH, 
                                                 panel));
                    break;
                case 15:
                    obstaculoAtual.add(new Obstaculo("tree.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                100, 
                                               100, 
                                                 panel));
                    break;
                case 16:
                    obstaculoAtual.add(new Obstaculo("tend.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                74, 
                                               50, 
                                                 panel));
                    break;
                case 17:
                    obstaculoAtual.add(new Obstaculo("car.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                112, 
                                               55, 
                                                 panel));
                    break;
                case 18:
                    obstaculoAtual.add(new Obstaculo("heli.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                205, 
                                               67, 
                                                 panel));
                    break;
                case 19:
                    obstaculoAtual.add(new Obstaculo("bush2.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                36, 
                                               24, 
                                                 panel));
                    break;
                case 20:
                    obstaculoAtual.add(new Obstaculo("roxa.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                69, 
                                               64, 
                                                 panel));
                    break;
                case 21:
                    obstaculoAtual.add(new Obstaculo("caveLN.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 22:
                    obstaculoAtual.add(new Obstaculo("caveLS.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 23:
                    obstaculoAtual.add(new Obstaculo("caveNO.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT,  
                                                 panel));
                    break;
                case 24:
                    obstaculoAtual.add(new Obstaculo("caveOS.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 25:
                    entidadeAtual.add(new Corpo("fire.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                15, 
                                               15, 
                                                 panel));
                    break;
                case 26:
                    entidadeAtual.add(new Corpo("wood.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                15, 
                                               15, 
                                                 panel));
                    break;
                case 27:
                    entidadeAtual.add(new Corpo("blood.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                15, 
                                               15, 
                                                 panel));
                    break;
                case 28:
                    obstaculoAtual.add(new Obstaculo("caveH.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 29:
                    obstaculoAtual.add(new Obstaculo("caveV.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 30:
                    obstaculoAtual.add(new Obstaculo("caveBeiraD.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 31:
                    obstaculoAtual.add(new Obstaculo("caveBeiraE.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;
                case 32:
                    skipAtual.add(new Skip("skip.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                Consts.TILE_WIDTH, 
                                               Consts.TILE_HEIGHT, 
                                                 panel));
                    break;    
                case 33:
                    obstaculoAtual.add(new Obstaculo("log.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                36, 
                                               102, 
                                                 panel));
                    break;   
                case 34:
                    obstaculoAtual.add(new Obstaculo("box.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                49, 
                                               22, 
                                                 panel));
                    break;   
                case 35:
                    obstaculoAtual.add(new Obstaculo("box2.png", 
                                                         linha * Consts.TILE_HEIGHT, 
                                                         coluna * Consts.TILE_WIDTH,
                                                22, 
                                               49, 
                                                 panel));
                    break; 
                case 36:
                    inimigoAtual.add(new InimigoBase("enemy.png", 
                                                    linha * Consts.TILE_HEIGHT, 
                                                    coluna * Consts.TILE_WIDTH, 
                                                    3, Consts.TILE_HEIGHT, Consts.TILE_WIDTH, panel, 0));
                    break;
                default:
            }
        }
}
