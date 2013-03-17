/**
 * This is not our code. We found it somewhere on the Internet, and thought it was a nice extra to the game.
 */
package asteroids;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A class of mediaplayers.	
 * 
 * @author	...
 * @version	1.0
 *
 */

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
