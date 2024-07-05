package com.example.matchmissionapp;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
  private static MediaPlayer mediaPlayer;
  private static double volume = 0.5;

  public static void initialize(String mediaPath){
    if (mediaPath != null) {
      Media media = new Media(mediaPath);
      mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setVolume(volume);
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
      mediaPlayer.play();
    } else {
      System.err.println("Media file not found: /path/to/your/media/file.mp3");
      // Handle error or fallback to a default media path
    }

  }

  public static void setVolume(double newVolume) {
    volume = newVolume;
    if (mediaPlayer != null) {
      mediaPlayer.setVolume(volume);
    }
  }

  public static double getVolume() {
    return volume;
  }

}
