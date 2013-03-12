package asteroids;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MediaPlayer {
	public static void playSound(String path){
	    try{
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        	Clip clip = AudioSystem.getClip();
        	clip.open(audioInputStream);
        	clip.start();
	    }catch(Exception ex){
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
}
