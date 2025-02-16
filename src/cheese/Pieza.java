/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * TEXTO TEXTO TEXTO
 */
package cheese;


import java.awt.Image;
import javax.swing.ImageIcon;


public abstract class Pieza {
    // Atributos
    int x, y;
    tipoPieza pieza;
    colorPieza color;
    protected ImageIcon imagen, gif;
    protected Sonidos sonido = new Sonidos();
    protected String Movimiento;
    
    // constructor
    public Pieza(tipoPieza pieza, colorPieza color,int x, int y){
        this.pieza = pieza;
        this.color = color;
        this.x = x;
        this.y = y;
    }
    
    // funciones
    abstract boolean isValido (int newx, int newy);// calcula los movimientos posibles
    abstract String getMov();
    
    void move (int newX, int newY) {
        x = newX; y = newY;
    }
        
    ImageIcon getGIF () {
        return gif;
    }
    
    ImageIcon getImage () {
        return imagen;
    }
    
    tipoPieza getTipo () {
        return pieza;
    }
    
    colorPieza getColor () {
        return color;
    }
}

class General extends Pieza {
    
    // constructor
    public General (colorPieza color,int x, int y){
        super(tipoPieza.GENERAL, color, x, y);
        ImageIcon i, gif2;
        if (color == colorPieza.ROJO) {
            i = new ImageIcon("src\\Imagenes\\General (rojo) frente.PNG", "General");
            gif2 = new ImageIcon("src\\Imagenes\\GeneralRojo.gif");
        }else {
            i = new ImageIcon("src\\Imagenes\\General (rojo).PNG", "General");
            gif2 = new ImageIcon("src\\Imagenes\\GeneralRojo.gif");
        }
        
        Image resize = i.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        this.imagen = new ImageIcon(resize);
        resize = gif2.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        this.gif = new ImageIcon(resize);
    }   
    
    // funciones
    @Override
    boolean isValido (int newx, int newy) {
        return (newy > 2 && newy < 6) && ((getColor().equals(colorPieza.ROJO) && newx >6 && newx < 10) ||(getColor() == colorPieza.NEGRO && newx > -1 && newx < 3)) && (((newx == x + 1 || newx == x - 1) && (newy > y-2 && newy < y+2)) || ((newy == y - 1 || newy == y + 1) && (newx > x-2 && newx < x+2)));
    }
    
    @Override
    String getMov () {
        return "Se mueve una pieza en cualquier direcccion. \n No puede salir del palacio marcado en " + String.valueOf(color);
    }
    
}

class Cannon extends Pieza {
    // constructor
    public Cannon (colorPieza color,int x, int y){
        super(tipoPieza.CANNON, color, x, y);
        ImageIcon i;
        if (color == colorPieza.ROJO) {
            i = new ImageIcon("src\\Imagenes\\Paov (rojo).PNG", "Cannon");
        } else {
            i = new ImageIcon("src\\Imagenes\\Paov (black).PNG", "Cannon");
        }
        
        Image resize = i.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        this.imagen = new ImageIcon(resize);
    }   
    
    // funciones
    @Override
    boolean isValido (int newx, int newy) {
        return  (newx == x && newy != y) || (newx != x && newy == y);
    }
    
    @Override
    String getMov () {
        return "Se mueve de manera horizontal o vertical. \nSalta sobre su oponente para comer";
    }
}

class Elefante extends Pieza {
    // atributos
    int origX;
    
     // constructor
    public Elefante (colorPieza color,int x, int y){
        super(tipoPieza.ELEFANTE, color, x, y);
        ImageIcon i;
        if (color == colorPieza.ROJO) {
            i = new ImageIcon("src\\Imagenes\\Pieza2.png");
        } else {
            i = new ImageIcon("src\\Imagenes\\Elefante (negro).PNG");
        }
        
        Image resize = i.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        this.imagen = new ImageIcon(resize);
        origX = x;
    }   
    
    // funciones
    @Override
    boolean isValido (int newx, int newy) {
        return  (newx > origX - 5 && newx < origX + 5) && ((newx == x + 2 && newy == y + 2) || (newx == x - 2 && newy == y - 2) || (newx == x - 2 && newy == y + 2) || (newx == x + 2 && newy == y - 2));
    }
    
    @Override
    String getMov () {
        return "Se mueve dos pasos diagonales en cualquier direccion\nNo cruza el rio porque no sabe nadar... ";
    }
}

class Caballo extends Pieza {

    public Caballo (colorPieza color, int x, int y) {
        super(tipoPieza.CABALLO, color, x, y);
        ImageIcon i;
        if (color == colorPieza.ROJO) {
            i = new ImageIcon("src\\Imagenes\\Mai (rojo).PNG");
        } else {
            i = new ImageIcon("src\\Imagenes\\Pieza2.png");
        }
        
        Image resize = i.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        this.imagen = new ImageIcon(resize);
    }
    
    // funciones
    @Override
    boolean isValido(int newx, int newy) {
        return ((newx == x + 2 || newx == x - 2) && (newy == y + 1 || newy == y - 1)) || ((newy == y + 2 || newy == y - 2) && (newx == x + 1 || newx == x - 1));
    }
    
        @Override
    String getMov () {
        return "Se mueve en L, dos pasos horizontal y uno vertical o vice versa. \nCon tal no tenga su pivote ocupado...";
    }
}

class carroGuerra extends Pieza {

    public carroGuerra(colorPieza color, int x, int y) {
        super(tipoPieza.CARROdeGUERRA, color, x, y);
        ImageIcon i;
        if (color == colorPieza.ROJO) {
            i = new ImageIcon("src\\Imagenes\\Pieza2.png");
        } else {
            i = new ImageIcon("src\\Imagenes\\Pieza2.png");
        }
        
        Image resize = i.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        this.imagen = new ImageIcon(resize);        
    }

    
    
    @Override
    boolean isValido(int newx, int newy) {
        return  (newx == x && newy != y) || (newx != x && newy == y);
    }
    
        @Override
    String getMov () {
        return "Se mueve horizontal...";
    }
}

class Soldado extends Pieza {

    //
    public Soldado (colorPieza color, int x, int y) {
        super(tipoPieza.SOLDADO, color, x, y);
        ImageIcon i;
        if (color == colorPieza.ROJO) {
            i = new ImageIcon("src\\Imagenes\\Pieza2.png");
        } else {
            i = new ImageIcon("src\\Imagenes\\Pieza2.png");
        }
        
        Image resize = i.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        this.imagen = new ImageIcon(resize);

    }

    @Override
    boolean isValido(int newx, int newy) {
        return ( ((color == colorPieza.NEGRO &&  newx == x + 1 && x != 9) || (color == colorPieza.ROJO &&  newx == x - 1 && x != 0)) && newy == y  ) || ((x == 0 || x == 9) && (newy == y + 1 || newy == y - 1) && newx == x);
    }
    
        @Override
    String getMov () {
        return "Marcha determinado solo hacia al frente: no mira hacia atras, no titubea o clama en temor. \nAl llegar al lado opuesto marcha solamente horizontal...";
    }
}