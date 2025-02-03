/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Frame extends JFrame {
    contentPanel C = new contentPanel();
    
    public Frame() {
        setTitle("- Proyecto - XIANQUI - 象棋 -");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        
        C.initInicio();
        add(C);        
    }
}

class contentPanel extends JPanel {
    private GridBagConstraints G = new GridBagConstraints(); 
    
    public contentPanel () {
        setVisible(true);
        setLayout(new GridBagLayout());
    }
    
    void initInicio () {
        System.out.println("User entro a: Inicio");
        
        G = new GridBagConstraints(); // Reset constraints
        G.insets = new Insets(20, 20, 20, 20);
        
        // Title Label
        G.gridx = 0; G.gridy = 0;
        G.gridwidth = 10;
        G.fill = GridBagConstraints.BOTH;
        add(new JLabel("XIANQUI - BIENVENIDO"), G);
        G.insets = new Insets(5, 5, 5, 5);
        
        // Login
        JButton login = new JButton("- Login -");
        G.gridy = 1;
        login.addActionListener(e -> {
            clean();
            System.out.println("User entro a: Login");
            initUser();
            loginBtns();
        });
        add(login, G);
        
        // Crear Cuenta   
        JButton cuenta = new JButton("- Crear una cuenta -");
        G.gridy = 2;
        cuenta.addActionListener(e -> {
            clean();
            System.out.println("User entro a: Register");
            initUser();
            crearBtns();
        });
        add(cuenta, G);        
        
        //  Salir
        JButton salir = new JButton("- Salir -");
        G.gridy = 3;
        salir.addActionListener(e -> {
            System.out.println("Cerrando Programa...");
            System.exit(0);
        });
        add(salir, G);
        
        // Refresh
        revalidate();
        repaint();
    }
    
    void initUser () {        
        G = new GridBagConstraints(); // Reset constraints
        G.insets = new Insets(20, 20, 20, 20);

        // Title Label
        G.gridx = 0;
        G.gridy = 0;
        G.gridwidth = 2; // Adjust width for better alignment
        G.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("XIANQUI - USER", SwingConstants.CENTER), G);

        G.insets = new Insets(5, 5, 5, 5); // Reduce spacing for other components

        // Username Label
        G.gridx = 0;
        G.gridy = 1;
        G.gridwidth = 1; 
        G.anchor = GridBagConstraints.WEST; // Align left
        add(new JLabel("Username: "), G);

        // Username Text Field
        G.gridx = 1;
        G.fill = GridBagConstraints.HORIZONTAL;
        add(new JTextField(15), G);

        // Password Label
        G.gridx = 0;
        G.gridy = 2;
        G.fill = GridBagConstraints.NONE;
        add(new JLabel("Password: "), G);

        // Password Field
        G.gridx = 1;
        G.fill = GridBagConstraints.HORIZONTAL;
        add(new JPasswordField(15), G);
    }

    void loginBtns () {
        // Login Button
        G.gridx = 0;
        G.gridy = 3;
        G.gridwidth = 3; // Center button
        G.weightx = 0;
        G.fill = GridBagConstraints.HORIZONTAL;
        add(new JButton("* Login *"), G);

        // Regresar
        JButton regresar = new JButton("* Regresar *");
        G.gridx = 1;
        G.gridy = 4;
        G.fill = GridBagConstraints.CENTER;
        regresar.addActionListener(e -> {
            clean();
            initInicio();
        });
        add(regresar, G);
        
        // Refresh UI
        revalidate();
        repaint();
    }
    
    void crearBtns() {
        // crear user Button
        JButton crear = new JButton("* Crear User *");
        G.gridx = 0;
        G.gridy = 3;
        G.gridwidth = 3; // Center button
        G.weightx = 0;
        G.fill = GridBagConstraints.HORIZONTAL;
        add(crear, G);

        // Regresar
        JButton regresar = new JButton("* Regresar *");
        G.gridx = 1;
        G.gridy = 4;
        G.fill = GridBagConstraints.CENTER;
        regresar.addActionListener(e -> {
            clean();
            initInicio();
        });
        add(regresar, G);
        
        // Refresh UI
        revalidate();
        repaint();
    }
    
    void  initMenu () {}
    
    void miCuenta () {}
    
    void Reportes() {}
    
    void clean () {
        removeAll();
    }
    
}