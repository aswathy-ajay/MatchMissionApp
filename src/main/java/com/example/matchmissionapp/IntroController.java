package com.example.matchmissionapp;


import java.io.IOException;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class IntroController {
  private static final Logger logger = Logger.getLogger(IntroController.class.getName());
  MediaPlayer mediaPlayer;
  @FXML
  private BorderPane introBorderPane;
  @FXML
  private ImageView introImage;

  public void initialize() {
    logger.info("Playing intro background music");
    startBackgroundMusic();
  }
  private void startBackgroundMusic() {
    String mediaPath = getClass().getResource("/sounds/intro.mp3").toString();
    Media media = new Media(mediaPath);
    mediaPlayer = new MediaPlayer(media);

    mediaPlayer.setOnReady(() -> {
      Duration mediaDuration = media.getDuration();
      startTransition(mediaDuration);
      mediaPlayer.play();
    });
  }

  private void startTransition(Duration duration){
    FadeTransition transitionObject = new FadeTransition(duration,introBorderPane);
    transitionObject.setFromValue(0.0);
    transitionObject.setToValue(2.0);
    transitionObject.setFromValue(2.0);
    transitionObject.setToValue(0.0);
    transitionObject.play();

    transitionObject.setOnFinished(event -> {
      try {
        mediaPlayer.stop();
        playBackgroundMusic();
        startMenu(introBorderPane);
      } catch (IOException e) {
        logger.severe("Failed to navigate to menu page: " + e.getMessage());
        e.printStackTrace();
      }
    });
    transitionObject.play();

  }

  public void startMenu(BorderPane borderPane) throws IOException {
//    mediaPlayer.stop();
    logger.info("Navigating to the menu page...");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
    Parent introParent = loader.load();


    // Get the current stage and set the new scene
    Stage stage = (Stage) borderPane.getScene().getWindow();
    stage.setScene(new Scene(introParent));
    stage.show();
  }

  private void playBackgroundMusic() {
    logger.info("Playing background music");
    double currentVolume = MusicManager.getVolume();
    logger.info("Current volume is: " + currentVolume);
    String mediaPath = getClass().getResource("/music/background-music.mp3").toString();
    MusicManager.initialize(mediaPath);
  }

  public void nextScreen(BorderPane borderPane, String fxmlPage) throws IOException {
    logger.info("Navigating to the next game screen...");
    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPage));
    Parent menuParent = loader.load();

    // Get the current stage and set the new scene
    Stage stage = (Stage) borderPane.getScene().getWindow();
    stage.setScene(new Scene(menuParent));
    stage.show();
  }
}
