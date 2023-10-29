package com.mycompany.jogobrabo;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MyFrame extends JFrame{
    public JFrame frame;
    
    MyFrame(MyPanel gamePanel) {
        frame = new JFrame();
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(Consts.MAX_WIDTH, Consts.MAX_HEIGHT));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
