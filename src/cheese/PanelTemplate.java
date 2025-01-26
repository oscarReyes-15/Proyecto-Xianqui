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
    String[] Btns;
    abstract void initComp();
    abstract JButton initBtn(String name);
    abstract void clean();
}

class MenuPanel extends PanelTemplate{
    String[] Btns = {"- JUGAR XIANQUI -", "- Mi cuenta -", "- Reportes -", "- Log out -"};
    
    public MenuPanel(){
        setSize(1000, 700);
        setLayout(new GridLayout(Btns.length, 1));
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
    } 
    
    @Override
    public void initComp(){
        for (String name : Btns){
            add(initBtn(name));
        }
        setVisible(true);
    }
    
    @Override
    public JButton initBtn(String name){
        JButton butt = new JButton(name);        
        return butt;
    }
    
    @Override
    public void clean(){
        removeAll();
        setVisible(false);
    }
}

class LogPanel extends PanelTemplate{
    String[] Btns = {"Log in", "Crear cuenta", "Salir"};
    
    public LogPanel(){
        setSize(1000, 700);
        setLayout(new GridLayout(Btns.length + 1, 1));
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
    }
    
    @Override
    public void initComp(){
        add(new JLabel("XIANQUI - 象棋"));
        for (String name : Btns){
            add(initBtn(name));
        }
        setVisible(true);
    }
    
    @Override
    public JButton initBtn(String name){
        JButton butt = new JButton(name);  
        return butt;
    }
    
    @Override
    public void clean(){
        removeAll();
        setVisible(false);
    }
    
}
 
class miCuenta extends PanelTemplate{
    String[] Btns = {"- Cambiar Password -", "- Cerrar mi Cuenta -"};
    
    public miCuenta(){
        setSize(1000, 700);
        setLayout(new GridLayout(Btns.length + 1, 1));
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
    }
    
    @Override
    public void initComp(){
        for (String name : Btns){
            add(initBtn(name));
        }
        setVisible(true);
    }
    
    @Override
    public JButton initBtn(String name){
        JButton butt = new JButton(name);  
        butt.setAlignmentX(JButton.CENTER);
        return butt;
    }
    
    @Override
    public void clean(){
        removeAll();
        setVisible(false);
    }
}

class Reportes extends PanelTemplate{
    String[] Btns = {"- Cambiar Password -", "- Cerrar mi Cuenta -"};
    
    public Reportes(){
        setSize(1000, 700);
        setLayout(new GridLayout(Btns.length + 1, 1));
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
    }
    
    @Override
    public void initComp(){
        for (String name : Btns){
            add(initBtn(name));
        }
        setVisible(true);
    }
    
    @Override
    public JButton initBtn(String name){
        JButton butt = new JButton(name);  
        butt.setAlignmentX(JButton.CENTER);
        return butt;
    }
    
    @Override
    public void clean(){
        removeAll();
        setVisible(false);
    }
}