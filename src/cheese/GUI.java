/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import javax.swing.*;
import java.awt.CardLayout;

public class GUI implements Panel_Interface {
    JFrame frame = new JFrame("Frame");
    Arreglo[] Arreglo = new Arreglo[10];
    int cont = 0;
    
    CardLayout C;
    JPanel main;
    
    
    public GUI(){
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main = new JPanel(C);
        frame.add(main);
    }

    @Override
    public void addPanel(String name, JPanel panel) {
        Arreglo[cont] = new Arreglo(panel, name);
        cont++;
        main.add(panel, name);
    }

    @Override
    public void switchPanel(Arreglo Arreglo) {
        
    }
}

class Panel {
    public JPanel Conf(CardLayout L){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(1000, 700);
        panel.setLayout(L);
        
        return panel;
    } 
}

class Arreglo{
    private JPanel panel;
    private String name;
    
    public Arreglo(JPanel panel, String name){
        this.panel = panel;
        this.name = name;
    }

    JPanel getPanel(){
        return panel;
    }
    
    String getName(){
        return name;
    }
    
}


