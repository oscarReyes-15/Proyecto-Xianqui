/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import javax.swing.*;
import java.awt.CardLayout;


public class GUI {
    JFrame frame = new JFrame("Proyecto - Xianqui - 象棋");
    CardLayout C;
    JPanel main;
    
    
    public GUI(){
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main = new JPanel(C);
        frame.add(main);
        main.add(new LogPanel());
    }

    public void addPanel(String name, JPanel panel) {
        main.add(panel, name);
    }

    
}





