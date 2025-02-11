package cheese;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.*;

public class PanelBoard extends JPanel {
    JButton[][] Board = new JButton[10][9];
    Board Logica;
    
    public PanelBoard (Board Logica) {
        this.Logica = Logica;
        setLayout(new GridLayout(11, 9));
        Btns();
        refreshTab();
    }
    
    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        ImageBase i = new ImageBase("src\\Imagenes\\Tablero.PNG");
        i.draw(g, 0, 0, getWidth(), getHeight(), this);
    }
    
    
    void Btns () {
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 9; j++){
                if (i == 5) {
                    JPanel rio = new JPanel();
                    rio.setOpaque(false);
                    add(rio);
                }else{
                    int x;
                    
                    if(i > 5){
                        x = i - 1;

                    } else {x = i;}
                    Board[x][j] = Btn(x, j);

                    add(Board[x][j]);
                                                          
                }
            }
        }
    }
    
    JButton Btn (int x, int y) {
        JButton btn = new JButton();
        btn.setFocusable(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        if(Logica.tablero[x][y] != null){
            btn.setToolTipText(String.valueOf(Logica.tablero[x][y].getTipo()));
        }
        btn.addActionListener(e -> {
            Logica.Oprimir(x, y);
            iluminar(Logica.validos);
            refreshTab();
        });
                
        return btn;
    }
        
    public static void main(String[] args) {
        Board B  = new Board();

        JFrame j = new JFrame("Tablero");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(600, 600);
        j.add(new PanelBoard(B));
        
        j.setVisible(true);
    }
    
    void iluminar (boolean[][] validos) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (validos[i][j] == true) {
                    Board[i][j].setBorderPainted(true);
                }
                else {
                    Board[i][j].setBorderPainted(false);                  
                }
            }
        }
    }
    
    void refreshTab () {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if(Logica.tablero[i][j] != null){
                    
                    Board[i][j].setIcon(Logica.tablero[i][j].getImage());
                }else {
                    Board[i][j].setIcon(null);
                }
            }
        }
        revalidate();
        repaint();
    }
    
    
}



