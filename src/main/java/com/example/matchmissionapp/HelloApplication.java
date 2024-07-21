package com.example.matchmissionapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.matchmissionapp.MusicManager;
import java.util.logging.Logger;

import java.io.IOException;

public class
HelloApplication extends Application {
  private static final Logger logger = Logger.getLogger(HelloApplication.class.getName());
  private IntroController introController;
  @Override
  public void start(Stage stage) throws IOException {

//    double currentVolume = MusicManager.getVolume();
//    logger.info("Current volume is: " + currentVolume);
    logger.info("Loading Introduction stage");
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("intro.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("MatchMission");
    stage.setScene(scene);
    stage.show();
  }


  public static void main(String[] args) {
    launch();
  }
}