/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class D_PanelJuego extends JPanel {
    C_Board Logica = new C_Board();
    Player player1, player2;
    E_PanelBoard tablero = new E_PanelBoard(Logica);
    Info info = new Info(Logica, player1, player2);
    
    public D_PanelJuego (Player player1, Player player2) {
        setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);
        add(info, BorderLayout.EAST);   
        
    }
    
    void refreshAll () {
        tablero.refreshTab();
        info.refresh();
        revalidate();
        repaint();
    }
}

class Info extends JPanel {
    C_Board Logica;
    JPanel Pieza = new JPanel(), Descr = new JPanel();
    GUIComp Comp = new GUIComp();
    GridBagConstraints GBC = new GridBagConstraints();
    Player player1, player2;
    
    //
    JLabel turno, User;
    JPanel display;
    
    
    public Info (C_Board Logica, Player player1, Player player2) {
        setPreferredSize(new Dimension (200, 600));
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        this.Logica = Logica;
        
        this.player1 = player1;
        this.player2 = player2;
    }
    
    void setComp () {
        Comp.setGBC(GBC, 0, 0, 2, 5, 0, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER);
        turno = Comp.makeLBL("Turno de: " + (Logica.turno == true? "Rojo" : "Negro"));
        Comp.setGBC(GBC, 0, 1, 2, 5, 0, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER);
        User = Comp.makeLBL("Turno de: " + (Logica.turno == true? player1.getPlayer() : player2.getPlayer()));
    }
    
    void refresh() {
        
    }
    
    void escogerPieza () {
        while (true) {
            if (Logica.currentPieza != null){
                
            }
        }
    }
}