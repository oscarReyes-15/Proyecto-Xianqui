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
    E_PanelBoard tablero;
    Info info;
    Sonidos sonidos = new Sonidos();
    contentPanel P; B_Frame B;
    
    public D_PanelJuego (Player player1, Player player2, contentPanel P, B_Frame B) {
        setLayout(new BorderLayout());
        this.player1 = player1;
        this.player2 = player2;
        this.P = P;
        this.B = B;

        tablero = new E_PanelBoard(Logica, this);
        info = new Info(Logica, player1, player2);
        info.setComp();
        add(tablero, BorderLayout.CENTER);
        add(info, BorderLayout.EAST);   
        
    }
    
    void refreshAll () {
        switch (Logica.Fin()){
            case 1 -> {
                sonidos.Play(0);
                JOptionPane.showMessageDialog(null, player2.getPlayer().getUser() + " ha vencido a " + player1.getPlayer().getUser() + " en glorioso combate! +3 pts");
                player2.getPlayer().addPuntos();
                player2.getPlayer().setLog(player2.getPlayer().getUser() + " vencio a " + player1.getPlayer().getUser() + " en glorioso combate!");
                player1.getPlayer().setLog(player1.getPlayer().getUser() + " fue vencido por " + player1.getPlayer().getUser() + " - :( ");
                
                B.setSize(500, 500);
                B.setLocationRelativeTo(null);
                P.clean();
                P.initMenu();
            }
            case 2 -> {
                sonidos.Play(0);
                JOptionPane.showMessageDialog(null, player1.getPlayer().getUser() + " ha vencido a " + player2.getPlayer().getUser() + " en glorioso combate! +3 pts");
                player1.getPlayer().addPuntos();
                player1.getPlayer().setLog(player1.getPlayer().getUser() + " vencio a " + player1.getPlayer().getUser() + " en glorioso combate!");
                player2.getPlayer().setLog(player2.getPlayer().getUser() + " fue vencido por " + player1.getPlayer().getUser() + " :( ");
                
                B.setSize(500, 500);
                B.setLocationRelativeTo(null);
                P.clean();
                P.initMenu();
            }
            case 3 -> {}
        }
        
        tablero.refreshTab();
        info.refresh();
        revalidate();
        repaint();
    }
}

class Info extends JPanel {
    C_Board Logica;
    GUIComp Comp = new GUIComp();
    GridBagConstraints GBC = new GridBagConstraints();
    Player player1, player2;
    ImageIcon DefaultIcon = new ImageIcon("src\\Imagenes\\Pieza2.png");

    
    //
    JLabel turno, User, GIF, Pieza; 
    JTextArea LogMov,Descr;
    JScrollPane LOG;
    
    
    public Info (C_Board Logica, Player player1, Player player2) {
        setPreferredSize(new Dimension (200, 600));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        
        LogMov = new JTextArea();
        LogMov.setEditable(false);
        LogMov.setSize(new Dimension(20, 20));
        LogMov.setEditable(false);
        LogMov.setWrapStyleWord(true);
        LogMov.setLineWrap(true);
        LOG = new JScrollPane(LogMov);
        LOG.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        LOG.setSize(20, 20);
        
        this.Logica = Logica;
        this.player1 = player1;
        this.player2 = player2;
        
        
    }
    
    void setComp () {
        // user y turno
        turno = Comp.makeLBL("Turno de: " + (Logica.turno == true? "Rojo" : "Negro"));
        add(turno, Comp.setGBC(GBC, 0, 0, 2, 5, 0, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER));
        
        User = Comp.makeLBL("Turno de: " + (Logica.turno == true? player1.getPlayer().getUser() : player2.getPlayer().getUser()));
        add(User, Comp.setGBC(GBC, 0, 1, 2, 5, 0, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER));
        
        //
        GIF = Comp.makeLBL("");
        GIF.setSize(new Dimension(20, 20));
        add(GIF, Comp.setGBC(GBC, 0, 2, 2, 1, 1, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER));
        
        //
        Pieza = Comp.makeLBL(Logica.currentPieza == null? "- //// -" : "- " + String.valueOf(Logica.currentPieza.getTipo()) + " -");
        Pieza.setSize(new Dimension(20, 20));
        add(Pieza, Comp.setGBC(GBC, 0, 3, 2, 5, 0, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER));
        
        //
        Descr = new JTextArea("Esperando pieza... Zzz");
        Descr.setEditable(false);
        Descr.setWrapStyleWord(true);
        Descr.setLineWrap(true);
        Descr.setSize(20, 20);
        add(Descr, Comp.setGBC(GBC, 0, 4, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5), GridBagConstraints.CENTER));
        
        //
        add(LOG, Comp.setGBC(GBC, 0, 5, 0, 1, 1, GridBagConstraints.NONE, new Insets(5,5,5,5), GridBagConstraints.CENTER));
        
        //
        JButton salir = Comp.makeBtn("-= Retirar Partida =-");
        salir.addActionListener(e -> {});
        add(salir, Comp.setGBC(GBC, 0, 6, 2, 5, 0, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER));
    }
    
    void refresh() {
        turno.setText("Turno de: " + (Logica.turno == true? "Rojo" : "Negro"));
        User.setText("Turno de: " + (Logica.turno == true? player1.getPlayer().getUser() : player2.getPlayer().getUser()));
        escogerPieza();
        Pieza.setText(Logica.currentPieza == null? "- //// -" : "- " + String.valueOf(Logica.currentPieza.getTipo()) + " -");
        addMov();
        Descr();
    }
    
    void addMov () {
        LogMov.append(Logica.registro);
    }
    
    void Descr () {
        if (Logica.currentPieza == null){
            Descr.setText("Esperando una pieza... Zzz");
        } else {
            Descr.setText(Logica.currentPieza.getMov());
        }
    }
    
    void escogerPieza () {
        GIF.setIcon(null);
        remove(GIF);
        add(GIF, Comp.setGBC(GBC, 0, 2, 2, 1, 1, GridBagConstraints.CENTER, new Insets(5,5,5,5), GridBagConstraints.CENTER));
        if (Logica.currentPieza != null){            
            if(Logica.currentPieza.getGIF() != null){
                GIF.setIcon(Logica.currentPieza.getGIF());
            }else {
                GIF.setIcon(Logica.currentPieza.getImage());
            }
        }
    }
}