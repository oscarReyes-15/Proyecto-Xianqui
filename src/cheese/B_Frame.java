/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import java.util.Scanner;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.NoSuchElementException;


public class B_Frame extends JFrame {
    
    protected UserBase BaseCentral  = new UserBase();
    Sonidos sonidos = new Sonidos();
    contentPanel C = new contentPanel(BaseCentral, this);

    public B_Frame() {
        setTitle("- Proyecto - XIANQUI - 象棋 -");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        C.initInicio();
        add(C);
        sonidos.Play(0);
        C.revalidate();
        C.repaint();
        
    }
}

class contentPanel extends JPanel {
    private final Image imagen;
    private final GridBagConstraints G = new GridBagConstraints();
    private JTextField User; JPasswordField Pass; User user;
    protected UserBase BaseCentral;
    Sonidos sonidos = new Sonidos();
    GUIComp Comp = new GUIComp();
    B_Frame B; 
    
    public contentPanel(UserBase BaseCentral, B_Frame B) {
        setVisible(true);
        setLayout(new GridBagLayout());
        imagen = new ImageIcon("src\\Imagenes\\TituloScreen.PNG").getImage();
        this.BaseCentral = BaseCentral;
        this.B = B;
        setSize(B.getWidth(), B.getHeight());
    }
    
    @Override 
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
    
    void initInicio() {

        // Login
        JButton login = Comp.makeBtn("- Login -");
        login.addActionListener(e -> {
            sonidos.Play(1);
            clean();
            System.out.println("User entro a: Login");
            initUser();
            loginBtns();
        });
        add(login, Comp.setGBC(G, 0, 1, 10, 0, 0, GridBagConstraints.BOTH, new Insets(70, 5, 5, 5), GridBagConstraints.CENTER));

        // Crear Cuenta
        JButton cuenta = Comp.makeBtn("- Crear una cuenta -");
        cuenta.addActionListener(e -> {
            sonidos.Play(1);
            clean();
            initUser();
            crearBtns();
        });
        add(cuenta, Comp.setGBC(G, 0, 2, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        //  Salir
        JButton salir = Comp.makeBtn("- Salir -");
        salir.addActionListener(e -> {
            sonidos.Play(1);
            if (JOptionPane.showConfirmDialog(null, "Seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });
        add(salir, Comp.setGBC(G, 0, 3, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        // Refresh
        refresh();
    }

    void initUser() {
        
        User = new JTextField(15);
        Pass = new JPasswordField(5);

        // Username Label
        JLabel U = Comp.makeLBL("Username: ");
        add(U, Comp.setGBC(G, 0, 1, 1, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(70, 5, 5, 5), GridBagConstraints.WEST));

        // Username Text Field
        add(User, Comp.setGBC(G, 1, 1, 1, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(70, 5, 5, 5), GridBagConstraints.EAST));

        // Password Label
        JLabel P = Comp.makeLBL("Password: ");
        add(P, Comp.setGBC(G, 0, 2, 1, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST));

        // Password Field
        add(Pass, Comp.setGBC(G, 1, 2, 1, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.EAST));
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
            sonidos.Play(1);
            try{
                if(User.getText() != null && Pass.getPassword() != null){
                    String userText = User.getText();
                    Scanner s = new Scanner(userText);
                    s.useDelimiter("[ \\s ]+");
                    if (!userText.matches("[a-zA-Z0-9]*")){
                        JOptionPane.showMessageDialog(login, "Advertencia! Todo texto despues de un espacio sera ignorado...", "Cuidado!",JOptionPane.WARNING_MESSAGE);
                    }
                    userText = s.next();
                    String PassText = new String (Pass.getPassword());

                    if (BaseCentral.validar(userText, PassText) == true){
                        user = BaseCentral.searchUser(userText);
                        clean();
                        System.out.println(userText + ": logged in!");
                        initMenu();
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "User o Password equivocados...");
            }
        } catch (NoSuchElementException f){JOptionPane.showMessageDialog(null, "No deje eso vacio");}
        });
        add(login, G);

        // Regresar
        JButton regresar = Comp.makeBtn("* Regresar *");
        regresar.addActionListener(e -> {
            sonidos.Play(1);
            clean();
            initInicio();
        });
        add(regresar, Comp.setGBC(G, 0, 4, 2, 0, 0, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        // Refresh UI
        refresh();
    }

    void crearBtns() {
        // crear user Button
        JButton crear = Comp.makeBtn("* Crear User *");
        crear.addActionListener(e -> {
            sonidos.Play(1);
            try {
                String userText = User.getText();
                Scanner s = new Scanner(userText);
                s.useDelimiter("[ \\s ]+");
                if (!userText.matches("[a-zA-Z0-9]*")){
                    JOptionPane.showMessageDialog(crear, "Advertencia! Todo texto despues de un espacio sera ignorado...", "Cuidado!",JOptionPane.WARNING_MESSAGE);
                }
                userText = s.next();
                String PassText = new String (Pass.getPassword());
            
            
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
            } catch (NoSuchElementException f){JOptionPane.showMessageDialog(null, "No deje eso vacio");}
            
        });
        add(crear, Comp.setGBC(G, 0, 3, 3, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        // Regresar
        JButton regresar = Comp.makeBtn("* Regresar *");
        regresar.addActionListener(e -> {
            sonidos.Play(1);
            clean();
            initInicio();
        });
        add(regresar, Comp.setGBC(G, 0, 4, 2, 0, 0, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        // Refresh UI
        refresh();
    }

    void initMenu() {
        System.out.println("User entro a: Menu");
        //
        JLabel U = Comp.makeLBL("Bienvenido " + user.getUser() + "!!"); 
        add(U, Comp.setGBC(G, 0, 0, 10, 0, 0, GridBagConstraints.BOTH, new Insets(70, 5, 5, 5), GridBagConstraints.CENTER));        
        
        //
        JButton jugar = Comp.makeBtn("Jugar 象棋");
        jugar.addActionListener(e -> { 
            String userInput = JOptionPane.showInputDialog("Ingrese otro usuario");
            User user2 = BaseCentral.searchUser(userInput);
            if (user2 == null){
                JOptionPane.showMessageDialog(null, "User no existe!", "Soldedad", JOptionPane.ERROR_MESSAGE);
            } else if (user2 == user){
                JOptionPane.showMessageDialog(null, "No se puede ingresar a usted mismo!" , "Narcicismo", JOptionPane.ERROR_MESSAGE);
            } else {
                clean();
                B.setSize(825, 650);
                B.setLocationRelativeTo(null);
                add(new D_PanelJuego(new Player(user, colorPieza.ROJO), new Player(user2, colorPieza.NEGRO), this, B));
                refresh();
            }
            
                       
        });
        add(jugar, Comp.setGBC(G, 0, 1, 10, 0, 0, GridBagConstraints.BOTH,new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        //
        JButton cuenta = Comp.makeBtn(" mi Cuenta ");
        cuenta.addActionListener(e -> { 
            clean();
            miCuenta();
        });
        add(cuenta, Comp.setGBC(G, 0, 2, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        //  reportes
        JButton reporte = Comp.makeBtn("- Reportes -");
        reporte.addActionListener(e -> {
            sonidos.Play(1);
            clean();
            Reportes();
        });
        add(reporte, Comp.setGBC(G, 0, 4, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        
        //  Salir
        JButton salir = Comp.makeBtn("- Log Out -");
        salir.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                sonidos.Play(1);
                System.out.println(user.getUser() + " logged out!");
                clean();
                user = null;
                initInicio();
            }
        });
        add(salir, Comp.setGBC(G, 0, 5, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));
        
        //revalidate
        refresh();
    }

    void miCuenta() {

        // Ver mi info
        JButton login = Comp.makeBtn("- Mi Cuenta -");
        login.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Username: " + user.getUser() + "\nPuntos totales: " + user.getPuntos() + "\nFecha de registro: "
                    + user.getFecha() + "\nPartidas VICTORIOSAS: " + (user.getPuntos() / 3), "Mi Informacion", JOptionPane.INFORMATION_MESSAGE);
        });
        add(login, Comp.setGBC(G, 0, 1, 10, 0, 0, GridBagConstraints.BOTH, new Insets(70, 5, 5, 5), GridBagConstraints.CENTER));

        // Cambiar password
        JButton cuenta = Comp.makeBtn("- Cambiar Paaword -");
        cuenta.addActionListener(e -> {
            String userInput = JOptionPane.showInputDialog("Ingrese su usuario");
            String Pass = JOptionPane.showInputDialog("Ingrese su Password");
            
            if (BaseCentral.searchUser(userInput) == user && BaseCentral.validar(userInput, Pass) == true){
                String PassNuevo = JOptionPane.showInputDialog("Ingrese su nuevo Password");
                user.setPass(PassNuevo);
                JOptionPane.showMessageDialog(null, "Exito!!");
            } else {
                JOptionPane.showMessageDialog(null, "User o password equivocados");
            }
        });
        add(cuenta, Comp.setGBC(G, 0, 2, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        //  remover cuenta
        JButton remov = Comp.makeBtn("- Remover Cuenta -");
        remov.addActionListener(e -> {
            String userDel = JOptionPane.showInputDialog("Ingrese su usuario");
            String PassDel = JOptionPane.showInputDialog("Ingrese su Password");
            
            if (JOptionPane.showConfirmDialog(null, "Seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                if (BaseCentral.searchUser(userDel) == user && BaseCentral.removePlayer(userDel, PassDel) == true){
                    JOptionPane.showMessageDialog(null, "Borrado Exisitosamente");
                    clean();
                    user = null;
                    initInicio();
                }else {
                    JOptionPane.showMessageDialog(null, "User o password no fue escrito correctamente");
                }
            }
        });
        add(remov, Comp.setGBC(G, 0, 3, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));
        
        //  Salir
        JButton salir = Comp.makeBtn("- Regresar -");
        salir.addActionListener(e -> {
            sonidos.Play(1);
            clean();
            initMenu();
        });
        add(salir, Comp.setGBC(G, 0, 4, 10, 0, 0, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        // Refresh
        refresh();
    }

    void Reportes() {
        // 
        JButton personal = Comp.makeBtn("- Mis partidas -");
        personal.addActionListener(e -> {
            String info = String.join("\n", user.getLogs());
            JOptionPane.showMessageDialog(null, info);
        });
        add(personal, Comp.setGBC(G, 0, 1, 10, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(70, 5, 5, 5), GridBagConstraints.CENTER));

        // Ver mi info
        JButton ranked = Comp.makeBtn("- Top Ranking -");
        ranked.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, BaseCentral.losMejores());
        });
        add(ranked, Comp.setGBC(G, 0, 2, 10, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));

        //  Salir
        JButton salir = Comp.makeBtn("- Regresar -");
        salir.addActionListener(e -> {
            sonidos.Play(1);
            clean();
            initMenu();
        });
        add(salir, Comp.setGBC(G, 0, 3, 10, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER));
        
        
        //
        refresh();
    }

    void clean () {
        removeAll();
    }
    
    void refresh () {
        revalidate();
        repaint();
    }

}

class GUIComp {
    
    JButton makeBtn (String name) {
        JButton btn = new JButton(name);
        
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.red);
        
        return btn;
    }
    
    JLabel makeLBL (String text) {
        JLabel lbl = new JLabel(text);        
        lbl.setForeground(Color.WHITE);     
        return lbl;
    }
    

    GridBagConstraints setGBC (GridBagConstraints G, int x, int y, int GrW, int WX, int WY, int fill, Insets I, int Anch) {
        G.gridx = x;    // 
        G.gridy = y;    //
        G.gridwidth = GrW; // columnas de espacio
        G.weightx = WX; 
        G.weighty = WY; // 
        G.fill = fill; // relleno alrededor
        G.insets = I;
        G.anchor = Anch;
        return G;
    }
}