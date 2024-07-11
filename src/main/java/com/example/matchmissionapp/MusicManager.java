package com.example.matchmissionapp;

import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
  private static MediaPlayer mediaPlayer;
  private static double volume = 0.5;
  private static final Logger logger = Logger.getLogger(MusicManager.class.getName());

  public static void initialize(String mediaPath) {

    Media media = new Media(mediaPath);
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setVolume(volume);
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
    logger.info("Playing music from path: " + mediaPath);
    mediaPlayer.play();

  }

  public static void setVolume(double newVolume) {
    volume = newVolume; // Update the volume variable
    if (mediaPlayer != null) {
      mediaPlayer.setVolume(newVolume);
    }
  }

  public static double getVolume() {
    logger.info("Retrieving the volume");
    if (mediaPlayer != null) {
      return mediaPlayer.getVolume();
    }
    return 0.0;
  }
}
