/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.util.Scanner;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Frame extends JFrame {
    
    Sonidos sonidos = new Sonidos();
    contentPanel C = new contentPanel();

    public Frame() {
        setTitle("- Proyecto - XIANQUI - 象棋 -");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        C.initInicio();
        add(C);
        C.revalidate();
        C.repaint();
        sonidos.setSound(0);
        sonidos.Loop();
    }
}

class contentPanel extends JPanel {
    private Image imagen;
    private GridBagConstraints G = new GridBagConstraints();
    JTextField User; JPasswordField Pass; User user;
    UserBase BaseCentral = new UserBase();
    Sonidos sonidos = new Sonidos();

    public contentPanel() {
        setVisible(true);
        setLayout(new GridBagLayout());
        imagen = new ImageIcon("src\\Imagenes\\TituloScreen.PNG").getImage();
    }
    
    @Override 
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
    
    void initInicio() {
        System.out.println("User entro a: Inicio");

        G = new GridBagConstraints(); // Reset constraints

        G.gridwidth = 10;
        G.fill = GridBagConstraints.BOTH;

        G.insets = new Insets(70, 5, 5, 5);

        // Login
        JButton login = new JButton("- Login -");
        login.setBackground(Color.red);
        login.setForeground(Color.WHITE);
        G.gridy = 1;
        login.addActionListener(e -> {
            sonidos.setSound(1);
            sonidos.Play();
            clean();
            System.out.println("User entro a: Login");
            initUser();
            loginBtns();
        });
        add(login, G);

        // Crear Cuenta
        G.insets = new Insets(5, 5, 5, 5);
        JButton cuenta = new JButton("- Crear una cuenta -");
        cuenta.setBackground(Color.red);
        cuenta.setForeground(Color.WHITE);
        G.gridy = 2;
        cuenta.addActionListener(e -> {
            sonidos.setSound(1);
            sonidos.Play();
            clean();
            System.out.println("User entro a: Register");
            initUser();
            crearBtns();
        });
        add(cuenta, G);

        //  Salir
        JButton salir = new JButton("- Salir -");
        salir.setBackground(Color.red);
        salir.setForeground(Color.WHITE);
        G.gridy = 3;
        salir.addActionListener(e -> {
            sonidos.setSound(2);
            sonidos.Play();
            refresh();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(contentPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Cerrando Programa...");
            System.exit(0);
        });
        add(salir, G);

        // Refresh
        refresh();
    }

    void initUser() {
        G = new GridBagConstraints(); //
        
        User = new JTextField(15);
        Pass = new JPasswordField(5);

        G.insets = new Insets(70, 5, 5, 5);
        
        // Username Label
        JLabel U = new JLabel("Username: ");
        U.setForeground(Color.WHITE);
        G.gridx = 0;
        G.gridy = 1;
        G.gridwidth = 1;
        G.anchor = GridBagConstraints.WEST; // Align left
        add(U, G);

        // Username Text Field
        G.gridx = 1;
        G.fill = GridBagConstraints.HORIZONTAL;
        add(User, G);

        // Password Label
        JLabel P = new JLabel("Password: ");
        P.setForeground(Color.WHITE);
        G.insets = new Insets(5, 5, 5, 5);
        G.gridx = 0;
        G.gridy = 2;
        G.fill = GridBagConstraints.NONE;
        add(P, G);

        // Password Field
        G.gridx = 1;
        G.fill = GridBagConstraints.HORIZONTAL;
        add(Pass, G);
    }

    void loginBtns() {
        // Login Button
        JButton login = new JButton("* Login *");
        login.setBackground(Color.red);
        login.setForeground(Color.WHITE);
        G.gridx = 0;
        G.gridy = 3;
        G.gridwidth = 3; // Center button
        G.weightx = 0;
        G.fill = GridBagConstraints.HORIZONTAL;
        login.addActionListener(e -> {      
            sonidos.setSound(1);
            sonidos.Play();
            
            String userText = User.getText();
            Scanner s = new Scanner(userText);
            s.useDelimiter("[ \\s ]+");
            if (!userText.matches("[a-zA-Z0-9]*")){
                JOptionPane.showMessageDialog(login, "Advertencia! Todo texto despues de un espacio sera ignorado...", "Cuidado!",JOptionPane.WARNING_MESSAGE);
            }
            userText = s.next();
            char[] arreglo = Pass.getPassword();
            String PassText = new String (arreglo);
            
            if (BaseCentral.validar(userText, PassText) == true){
                user = BaseCentral.searchUser(userText);
                clean();
                System.out.println(userText + ": logged in!");
                initMenu();
                return;
            }
            JOptionPane.showMessageDialog(null, "User o Password equivocados...");
        });
        add(login, G);

        // Regresar
        JButton regresar = new JButton("* Regresar *");
        regresar.setBackground(Color.red);
        regresar.setForeground(Color.WHITE);
        G.gridx = 1;
        G.gridy = 4;
        G.fill = GridBagConstraints.CENTER;
        regresar.addActionListener(e -> {
            sonidos.setSound(1);
            sonidos.Play();
            clean();
            initInicio();
        });
        add(regresar, G);

        // Refresh UI
        refresh();
    }

    void crearBtns() {
        // crear user Button
        JButton crear = new JButton("* Crear User *");
        crear.setBackground(Color.red);
        crear.setForeground(Color.WHITE);
        G.gridx = 0;
        G.gridy = 3;
        G.gridwidth = 3; // Center button
        G.weightx = 0;
        G.fill = GridBagConstraints.HORIZONTAL;
        crear.addActionListener(e -> {
            sonidos.setSound(1);
            sonidos.Play();
            
            String userText = User.getText();
            Scanner s = new Scanner(userText);
            s.useDelimiter("[ \\s ]+");
            if (!userText.matches("[a-zA-Z0-9]*")){
                JOptionPane.showMessageDialog(crear, "Advertencia! Todo texto despues de un espacio sera ignorado...", "Cuidado!",JOptionPane.WARNING_MESSAGE);
            }
            userText = s.next();
            char[] arreglo = Pass.getPassword();
            String PassText = new String (arreglo);
            
            
            if (PassText.length() != 5){
                JOptionPane.showMessageDialog(null, "Contrasena debe ser 5 caracteres de largo");
                return;
            }
            if (BaseCentral.searchUser(userText) == null){
                BaseCentral.addPlayer(userText, PassText);
                user = BaseCentral.searchUser(userText);
                JOptionPane.showMessageDialog(null, "Bienvenido " + userText);
                clean();
                initMenu();
                return;
            }
            JOptionPane.showMessageDialog(null, "Usuario ya existe...");
            
        });
        add(crear, G);

        // Regresar
        JButton regresar = new JButton("* Regresar *");
        regresar.setBackground(Color.red);
        regresar.setForeground(Color.WHITE);
        G.gridx = 1;
        G.gridy = 4;
        G.fill = GridBagConstraints.CENTER;
        regresar.addActionListener(e -> {
            sonidos.setSound(1);
            sonidos.Play();
            clean();
            initInicio();
        });
        add(regresar, G);

        // Refresh UI
        refresh();
    }

    void initMenu() {
        System.out.println("User entro a: Menu");

        G = new GridBagConstraints(); // Reset constraints

        G.gridwidth = 10;
        G.fill = GridBagConstraints.CENTER;

        G.insets = new Insets(70, 5, 5, 5);
        
        //
        JLabel U = new JLabel();
        U.setText("Bienvenido " + user.getUser() + "!!");
        U.setBackground(Color.RED);
        U.setForeground(Color.WHITE);
        G.gridx = 0;
        G.gridy = 0;
        G.gridwidth = 1;
        G.anchor = GridBagConstraints.CENTER; // 
        add(U, G);        
        
        //
        G.insets = new Insets(5, 5, 5, 5);
        JButton jugar = new JButton("Jugar 象棋");
        jugar.setBackground(Color.RED);
        jugar.setForeground(Color.WHITE);
        G.gridx = 0;
        G.gridy = 1;
        add(jugar, G);
        
        //  Salir
        JButton salir = new JButton("- Log Out -");
        salir.setBackground(Color.red);
        salir.setForeground(Color.WHITE);
        G.gridy = 2;
        salir.addActionListener(e -> {
            sonidos.setSound(1);
            sonidos.Play();
            System.out.println(user.getUser() + " logged out!");
            clean();
            user = null;
            initInicio();
        });
        add(salir, G);
        
        //revalidate
        revalidate();
        repaint();
    }

    void miCuenta() {
    }

    void Reportes() {
    }

    void clean () {
        removeAll();
    }
    
    void refresh () {
        revalidate();
        repaint();
    }

}

class LoginADMIN {
    
    

}