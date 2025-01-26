/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public abstract class PanelTemplate extends JPanel {
    abstract void initComp();
    abstract JButton initBtn(String name);
    abstract void openClose(int opcion);
}

class MenuPanel extends PanelTemplate{
    String[] Btns = {"- JUGAR XIANQUI -", "- Mi cuenta -", "- Reportes -", "- Log out -"};
    
    public MenuPanel(){
        setVisible(true);
        setSize(1000, 700);
        setLayout(new GridLayout(Btns.length, 1));
        initComp();
    } 
    
    @Override
    public void initComp(){
        for (String name : Btns){
            add(initBtn(name));
        }
    }
    
    @Override
    public JButton initBtn(String name){
        JButton butt = new JButton(name);        
        return butt;
    }
    
    @Override
    public void openClose(int opcion){
        switch(opcion){
            case 1 -> {}
            case 2 -> {}
            case 3 -> {
                System.out.println("Cerrando sesion...");
                
            }        
        }
    }
}

class LogPanel extends PanelTemplate{
    String[] Btns = {"Log in", "Crear cuenta", "Salir"};
    
    public LogPanel(){
        setVisible(true);
        setSize(1000, 700);
        setLayout(new GridLayout(Btns.length + 1, 1));
        initComp();
    }
    
    @Override
    public void initComp(){
        add(new JLabel("XIANQUI - 象棋"));
        for (String name : Btns){
            add(initBtn(name));
        }
    }
    
    @Override
    public JButton initBtn(String name){
        JButton butt = new JButton(name);  
        
        return butt;
    }
    
    @Override
    public void openClose(int opcion){
        switch(opcion){
            case 1 -> {}
            case 2 -> {}
            case 3 -> {
                System.out.println("Cerrando programa...");
                System.exit(0);
            }        
        }
    }
}
 