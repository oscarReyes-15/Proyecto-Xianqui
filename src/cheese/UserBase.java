/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

/**
 *
 * @author LENOVO
 */

public class UserBase {
    private User [] Base = new User[3];
    
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
        User[] DummyArray = new User[Base.length + 2];
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
            return;
        }
        if (pass.length() > 5 || pass.length() < 5){
            System.out.println("password must be 5 characters long!\n");
            return;
        }
        else {System.out.println(user + " is taken!\n"); return;}
    }
    
    void removePlayer (String user, String pass) {
        for (int i = 0; i < Base.length; i++) {
            if (Base[i] != null && Base[i].getPass().equals(pass) && Base[i].getUser().equals(user)) {
                Base[i] = null;
                System.out.println(user + " was deleted!\n");
                return;
            }
        }
        System.out.println("Whoops, smth went wrong...\n");
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
        if (searchUser(user) != null && searchUser(user).getPass().equals(pass)){
            return true;
        }
        return false;
    }
}

class User {
    private String user, pass;
    private int puntos;
    
    public User (String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.puntos = 0;
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

