/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;


public class Board {
    // Atributos
    Pieza[][] tablero = new Pieza[10][9];
    Pieza[] rojoCapturado = new Pieza[14], negroCapturado = new Pieza[14];
    Pieza currentPieza;
    boolean[][] validos = new boolean[10][9];
    boolean turno = true;
    int x = -1, y = -1;
    Sonidos s = new Sonidos();
    
    // constructor
    public Board () {
        tablero[9][4] = new General(colorPieza.ROJO, 9, 4);
        tablero[0][4] = new General(colorPieza.NEGRO, 0, 4);
        tablero[7][1] = new Cannon(colorPieza.ROJO, 7, 1);
        tablero[7][7] = new Cannon(colorPieza.ROJO, 7, 7);
        tablero[2][1] = new Cannon(colorPieza.NEGRO, 2, 1);
        tablero[2][7] = new Cannon(colorPieza.NEGRO, 2, 7);
        
        tablero[9][2] = new Elefante(colorPieza.ROJO, 9, 2);
        tablero[9][6] = new Elefante(colorPieza.ROJO, 9, 6);
        tablero[0][2] = new Elefante(colorPieza.NEGRO, 0, 2);
        tablero[0][6] = new Elefante(colorPieza.NEGRO, 0, 6);        
    }
    
    // funciones 
    boolean isRed (Pieza pieza) {
        return pieza.getColor() == colorPieza.ROJO;
    }
    
    void selectPieza (Pieza pieza, int newx, int newy ) {
        currentPieza = pieza;
        x = newx; y = newy;
    }
    
    void cambiarTurno () {
        turno = !turno;
    }
    
    void movePieza (int newx, int newy) {
        s.setSound(3);
        currentPieza.move(newx, newy);
        tablero[newx][newy] = currentPieza;
        tablero[x][y] = null;
        currentPieza = null;
        s.Play();
        cambiarTurno();        
    }
    
    void comerPieza (int newx, int newy) {
        if (isRed(tablero[newx][newy]) == true){
            for (int i = 0; i < 10; i++) {
                if (rojoCapturado[i] == null){
                    rojoCapturado[i] = tablero[newx][newy];
                    return;
                }
            }
                
        } else {
            for (int i = 0; i < 10; i++) {
                if (rojoCapturado[i] == null){
                    negroCapturado[i] = tablero[newx][newy];}
                    return;
                }
            }
            
    }
    
    //
    boolean[][] MovValidos () {
        boolean[][] update = new boolean[10][9];
        
        System.out.println(currentPieza + " " + x + " " + y);
        for (int i = 0; i < validos.length; i ++){
            for (int j = 0 ; j < update[i].length; j++){
                update[i][j] = currentPieza.isValido(i, j) == true;
                if (tablero[i][j] != null){
                    
                }
            }
        }
        printValidos(update);
        return update;
    }
    
    boolean[][] resetMov() {
        boolean[][] update = new boolean[10][9];
        
        for (int i = 0; i < validos.length; i ++){
            for (int j = 0 ; j < update[i].length; j++){
                update[i][j] = false;
            }
        }
        
        return update;
    }
    
    void Oprimir (int newx, int newy ) {
        if (x == -1  &&  y == -1) {
            if (tablero[newx][newy] != null) {
                if (isRed(tablero[newx][newy]) == turno) {
                    selectPieza(tablero[newx][newy], newx, newy);
                    validos = MovValidos();
                    System.out.println("selecciono pieza en: " + newx + newy);
                    return;
                }
            }
        } else {
            
            if(tablero[newx][newy] != null && isRed(tablero[newx][newy]) == turno){
                    x = -1; y = -1;
                    Oprimir(newx, newy);
                    return;
                }
            if (validos[newx][newy] == true) {
                
                if (tablero[newx][newy] != null && isRed(tablero[newx][newy]) != turno) {
                    comerPieza(newx, newy);
                    System.out.println("comiste una pieza");
                }
                validos = resetMov();
                movePieza(newx, newy);
                x = -1; y = -1;
                System.out.println("Movio pieza a: " + newx + newy);
                printValidos(validos);
                return;
            }
        }
        System.out.println("Nada de nada");
    }

    void printBoard(){
        for (Pieza[] p : tablero) {
            for (Pieza p2 : p) {
                if (p2 == null){
                    System.out.print("null ");
                } else {
                    System.out.print(/*p2.pieza + " " + */p2.color + " ");
                }
            }
            System.out.println("");
        }
    }
    
    void printValidos(boolean[][] validos2) {
        
        for (boolean[] v : validos2) {
            for (boolean v2 : v) {
                System.out.print(v2 + " ");
            }
            System.out.println("");
        }
    }
    
}
