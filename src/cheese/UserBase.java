/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import java.util.Calendar;

public class UserBase {
    private User [] Base = new User[1];
    
    int searchNull () {
        for (int i = 0; i < Base.length; i++){
            if (Base[i] == null){
                return i;
            }
        }
        expandBase();
        return searchNull();
    }
    
    User[] expandBase () {
        User[] DummyArray = new User[Base.length + 1];
        for (int i = 0; i < Base.length; i++) {
            DummyArray[i] = Base[i];
        }
        return Base = DummyArray;
    }
    
    void addPlayer (String user, String pass) {
        if (searchUser(user) == null  && pass.length() == 5){
            int i = searchNull(); 
            Base[i] = new User(user, pass);
            System.out.println(user + " was added!\n");
        }
        else if (pass.length() > 5 || pass.length() < 5){
            System.out.println("password must be 5 characters long!\n");
        }
        else {System.out.println(user + " is taken!\n");}
    }
    
    boolean removePlayer (String user, String pass) {
        for (int i = 0; i < Base.length; i++) {
            if (validar(user, pass)) {
                Base[i] = null;
                System.out.println(user + " was deleted!\n");
                return true;
            }
        }
        System.out.println("Whoops, smth went wrong...\n");
        return false;
    }
    
    User searchUser (String user) {
        for (User player : Base) {
            if (player != null && player.getUser().equals(user)){
                return player;
            }
        }
        return null;
    }
    
    boolean validar (String user, String pass) {
        User player = searchUser(user);
        return player != null && player.getPass().equals(pass) && player.getUser().equals(user);
    }
}

class User {
    private final String user;
    private String pass;
    private int puntos;
    private final Calendar fechaRegistro;
    
    public User (String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.puntos = 0;
        fechaRegistro = Calendar.getInstance();
    }
    
    String getFecha () {
        int anno = fechaRegistro.get(Calendar.YEAR);
        int mes = fechaRegistro.get(Calendar.MONTH);
        int dia = fechaRegistro.get(Calendar.DAY_OF_MONTH);
        int hora = fechaRegistro.get(Calendar.HOUR);
        int min = fechaRegistro.get(Calendar.MINUTE);
        int sec = fechaRegistro.get(Calendar.SECOND);
        
        return dia + "/" + (mes+1) + "/" + anno + "  -  " + hora + ":" + min + ":" + sec;
    }
    
    String getUser () {
        return user;
    }
    
    String getPass () {
        return pass;
    }
    
    int getPuntos () {
        return puntos;
    }
    
    void setPass (String Pass){
        this.pass = Pass;
    }
    
    void addPuntos () {
        puntos += 3;
    }
    
}

class Player {
    private User user;
    private colorPieza color;
    
    public Player (User user, colorPieza color) {
        this.user = user;
        this.color = color;
    }
    
    User getPlayer () {
        return user;
    }
    
    colorPieza getColor () {
        return color;
    }
}

