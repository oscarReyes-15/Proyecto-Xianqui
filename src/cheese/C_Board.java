/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;



public class C_Board {
    // Atributos
    protected Pieza[][] tablero = new Pieza[10][9];
    protected Pieza[] rojoCapturado = new Pieza[14], negroCapturado = new Pieza[14];
    public Pieza currentPieza;
    protected boolean[][] validos = new boolean[10][9];
    protected boolean turno = true, jaque = false;
    private int x = -1, y = -1;
    Sonidos s = new Sonidos();
    String registro;
    
    // constructor
    public C_Board () {
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

        tablero[9][1] = new Caballo(colorPieza.ROJO, 9, 1);
        tablero[9][7] = new Caballo(colorPieza.ROJO, 9, 7);
        tablero[0][1] = new Caballo(colorPieza.NEGRO, 0, 1);
        tablero[0][7] = new Caballo(colorPieza.NEGRO, 0, 7);
        
        tablero[9][0] = new carroGuerra(colorPieza.ROJO, 9, 0);
        tablero[9][8] = new carroGuerra(colorPieza.ROJO, 9, 8);
        tablero[0][0] = new carroGuerra(colorPieza.NEGRO, 0, 0);
        tablero[0][8] = new carroGuerra(colorPieza.NEGRO, 0, 8);
        
        tablero[3][0] = new Soldado(colorPieza.NEGRO, 3, 0);
        tablero[3][2] = new Soldado(colorPieza.NEGRO, 3, 2);
        tablero[3][4] = new Soldado(colorPieza.NEGRO, 3, 4);
        tablero[3][6] = new Soldado(colorPieza.NEGRO, 3, 6);
        tablero[3][8] = new Soldado(colorPieza.NEGRO, 3, 8);
        tablero[6][0] = new Soldado(colorPieza.ROJO, 6, 0);
        tablero[6][2] = new Soldado(colorPieza.ROJO, 6, 2);
        tablero[6][4] = new Soldado(colorPieza.ROJO, 6, 4);
        tablero[6][6] = new Soldado(colorPieza.ROJO, 6, 6);
        tablero[6][8] = new Soldado(colorPieza.ROJO, 6, 8); 
              

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
        registro +=  "\n=" +currentPieza.pieza + " " + currentPieza.color + " se ha movido a " + newx + ", "  + newy;
        currentPieza.move(newx, newy);
        tablero[newx][newy] = currentPieza;
        tablero[x][y] = null;
        currentPieza = null;
        s.Play(3);
        cambiarTurno();        
    }
    
    //maneja registrar comer otras piezas 
    void comerPieza (int newx, int newy) {
        if (isRed(tablero[newx][newy]) == true){
            for (int i = 0; i < 10; i++) {
                if (rojoCapturado[i] == null){
                    rojoCapturado[i] = tablero[newx][newy];
                    registro = "\n" + String.valueOf(tablero[newx][newy].getTipo()) + " " + String.valueOf(tablero[newx][newy].getColor()) + " fue comido por " + String.valueOf(currentPieza.getTipo()) + " " + String.valueOf(currentPieza.getColor());
                    return;
                }
            }
                
        } else {
            for (int i = 0; i < 10; i++) {
                if (rojoCapturado[i] == null){
                    negroCapturado[i] = tablero[newx][newy];}
                    registro = String.valueOf(tablero[newx][newy].getTipo()) + " " + String.valueOf(tablero[newx][newy].getColor()) + " fue comido por " + String.valueOf(currentPieza.getTipo()) + " " + String.valueOf(currentPieza.getColor());
                    return;
                }
            }
            
    }
    
    // chequea movimientos validos a travez de direcciones
    boolean [][] MovCaballo (boolean[][] update) {
        // Horse (Knight) movement rules in Xiangqi
        int[][] coordCaballo = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, // Vertical L-moves
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}  // Horizontal L-moves
        };
        
        for (int[] move : coordCaballo) {
            
            int i = x + move[0];
            int j = y + move[1];

            // Check board limits
            if (i >= 0 && i < 10 && j >= 0 && j < 9) {
                int bloqueX = x + (move[0] / 2); // Pivote X
                int bloqueY = y + (move[1] / 2); // Pivote Y

                // If the middle step is blocked, the horse cannot move there
                if (tablero[bloqueX][bloqueY] == null) {
                    // The final position must be either empty or an enemy piece
                    if (tablero[i][j] == null || isRed(tablero[i][j]) != turno) {
                        update[i][j] = true;
                    }
                }
            }
        }
        return update;
    }
    
    boolean[][] MovValidos () {
        boolean[][] update = new boolean[10][9];
        
        // 
        int[][] Direc = {
            {0, 1}, {0, -1}, // derecha e izquierda - destra e sinestra en italiano
            {1, 0}, {-1, 0}, // arriba y abajo
            {1, 1}, {-1, 1}, // diagonal abajo (derecha e izquierda)
            {-1, 1}, {-1, -1} // diagonal arriba (destra e sinestra)
        };
        
        if (currentPieza.getTipo() == tipoPieza.CABALLO){
            return MovCaballo(update);
        }
        for (int[] dir : Direc) {
        int i = x + dir[0], j = y + dir[1];
        boolean encontroPieza = false;  // 
        
        while (i >= 0 && i < 10 && j >= 0 && j < 9) { // Stay within board bounds
            if (tablero[i][j] != null) {
                if (!encontroPieza) { 
                    
                    if (isRed(tablero[i][j]) != turno) {
                        update[i][j] = currentPieza.isValido(i, j);
                    }
                    encontroPieza = true; // Stop further movement
                } 
                break; // Stop checking this direction after hitting a piece
            } 
            // If no piece encountered yet, mark it as valid
            update[i][j] = currentPieza.isValido(i, j);
            
            // Move further in the same direction
            i += dir[0];
            j += dir[1];
        }
    }       
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
    
    // Maneja input de botones
    void Oprimir (int newx, int newy ) {
        registro = "";
        jaque = false;
        
        if (x == -1  &&  y == -1) {
            if (tablero[newx][newy] != null) {
                if (isRed(tablero[newx][newy]) == turno) {
                    selectPieza(tablero[newx][newy], newx, newy);
                    validos = MovValidos();
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
            }
        }
    }   
    
    void MiradasQueMatan () {
        
        for (Pieza[] p : tablero) {
            for (Pieza p2 : p) {
                if (p2 != null && p2.getTipo() == tipoPieza.GENERAL){
                    for (int i = 0; i < 10; i++){
                        if (tablero[i][p2.y] != null){
                            return;
                        }
                        
                    }
                    
                }
            }
        }
    }
    
    int Fin () {
        boolean rojoVivo = false, negroVivo = false;
                
        
        for (Pieza[] p : tablero) {
            for (Pieza p2 : p) {
                if (p2 != null && p2.getTipo() == tipoPieza.GENERAL && p2.getColor() == colorPieza.ROJO){
                    rojoVivo = true;
                } else if (p2 != null && p2.getTipo() == tipoPieza.GENERAL && p2.getColor() == colorPieza.NEGRO){
                    negroVivo = true;
                } 
            }
        }
        
        if (rojoVivo == false && negroVivo == true){
            return 1; // gana negro
        }else if (rojoVivo == true && negroVivo == false){
            return 2; // gana rojo
            
        } else {return 3;} // normal
    }
}
