package src.GUIpack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Myusic {
	public static String launchSound = "music/bottlerocketsound.wav";
	public static String tsunami = "music/tsunami.mp3";
	public static String wonder = "music/POL-wonder-place-short.wav";
	public static Thread t = null;
	public static loopRunnable looper = null;
	public static void main(String[] args) {
		// these two lines play the bottle rocket sound
				// playAudio("music/bottlerocketsound.wav");

				playAudio(launchSound);
				loopAudio(wonder);
				RocketF.sleep(10000);
				stopLoop();
				//loopAudio(wonder);
				System.out.println("hello");

	}

	public static boolean playAudio(String fileName) {
		try {
			MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(
					fileName).toURI().toString()));
			mediaPlayer.play();
			
			return true;
		} catch (Exception x) {
			return false;
		}
	}

	public static void loopAudio(String filename) {
	
		
		looper = new loopRunnable(filename);
		t = new Thread(looper, "music");
		t.start();
		
		
	}
	public static void stopLoop()
	{
		looper.musicPlayer.stop(looper.loop);
	}
	@SuppressWarnings("deprecation")
	public static void stopMusic()
	{
		if (t!= null)
		{
			t.stop();
		}
	}
	

	
	public static class loopRunnable implements Runnable  
	{
		String f;
		AudioPlayer musicPlayer;
		ContinuousAudioDataStream loop = null;
		public loopRunnable(String filename)
		{
			f = filename;
		}
		
		@Override
		public void run() {
			AudioStream backgroundMusic;
			AudioData musicData;
			musicPlayer = AudioPlayer.player;
			
		
				
					try {
						backgroundMusic = new AudioStream(new FileInputStream(f));
						musicData = backgroundMusic.getData();
						loop = new ContinuousAudioDataStream(musicData);
						musicPlayer.start(loop);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				
			
		}
	}
	
	
}