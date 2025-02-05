/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import javax.swing.*;
import java.awt.*;

public class ImageBase {
    private Image imagen;
    
    public ImageBase (String direccion) {
        imagen = new ImageIcon(direccion).getImage();
    }
    
    public void draw (Graphics g, int x, int y, int grosor, int altura, JPanel panel) {
        g.drawImage(imagen, x, y,grosor, altura, panel);
    }
    
    public ImageIcon getIcon () {
        return new ImageIcon(imagen);
    }
}
