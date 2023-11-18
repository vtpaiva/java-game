package Entidades;

public class Corpo extends Drop{

    public Corpo(String path, int linha, int coluna, int entityWidth, int entityHeight, MyPanel gamePanel) {
        super(path, linha, coluna, entityWidth, entityHeight, gamePanel);
        this.setColetavel(false);
        this.rotate();
    }

    @Override
    public void efeitoColetavel() {}
}
