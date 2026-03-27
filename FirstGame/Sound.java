import java.io.IOException;
import java.io.File;
import javax.sound.sampled.*;

class Sound{
	public static void playSound(String fileName){
		try{
			File file = new File(fileName);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		}
		catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
			e.printStackTrace(); // or sop(e)
		}
	}  
}