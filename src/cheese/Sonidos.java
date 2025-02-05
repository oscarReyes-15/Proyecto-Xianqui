/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cheese;

import javax.sound.sampled.Clip;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Sonidos {
    //Sounds
    public Clip clip;
    public URL soundLink[] = new URL[6];
    
    public Sonidos(){
        soundLink[0] = getClass().getResource("/SonidosMusica/Bienvenido.wav"); 
        soundLink[1] = getClass().getResource("/SonidosMusica/ButtonRegular.wav");
        soundLink[2] = getClass().getResource("/SonidosMusica/GONG.wav");
        soundLink[3] = getClass().getResource("/SonidosMusica/PieceMove.wav");

    }
        
    void setSound(int i){
        
        try{
            if (soundLink[i] == null) { 
                throw new NullPointerException("soundLink[" + i + "] is null");
            }
            AudioInputStream AIS = AudioSystem.getAudioInputStream(soundLink[i]);
            clip = AudioSystem.getClip();
            clip.open(AIS);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void Loop(){
        if (clip != null){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    void Play(){
        if (clip != null){
            clip.start();
        }
    }
    
    void Stop(){
        if (clip != null){
            clip.stop();
        }
    }
}
