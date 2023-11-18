package Controle;

import Niveis.*;
import Entidades.MyPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements Serializable{
    public MyFrame(MyPanel gamePanel) {
        this.setLayout(new FlowLayout());
        this.getContentPane().add(gamePanel);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(Consts.MAX_RES_WIDTH, Consts.MAX_RES_HEIGHT));
        this.pack();
        this.getContentPane().setBackground(new Color(0, 102, 0) );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
