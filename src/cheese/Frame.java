/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import javax.swing.*;


public class Frame extends JFrame{ 
    LogPanel login = new LogPanel();
    MenuPanel Menu = new MenuPanel();
    miCuenta cuenta = new miCuenta();
    Reportes reportes = new Reportes();
    
    public Frame(){
        setTitle("- Proyecto - XIANQUI - 象棋 -");
        setVisible(true);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        login.initComp();
        add(login);
        add(Menu);
        add(cuenta);
        add(reportes);
    }   
}

class GUIadmin extends Frame{
    
    void switchPanel(int opcion){
        switch(opcion){
            case 1 -> { //
                login.clean();
                Menu.initComp();
            }
            case 2 -> {
                Menu.clean();
                cuenta.initComp();
            }
            case 3 -> {
                cuenta.clean();
                Menu.initComp();
            }
            case 4 -> {
                Menu.clean();
                reportes.initComp();
            }
            case 5 -> {
                reportes.clean();
                Menu.initComp();
            }
            case 6 -> {
                Menu.clean();
                login.initComp();
            }
        }
    }
}



