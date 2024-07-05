package com.example.matchmissionapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.matchmissionapp.MusicManager;

import java.io.IOException;

public class HelloApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    String mediaPath = getClass().getResource("/music/background-music.mp3").toString();
    MusicManager.initialize(mediaPath);

    double currentVolume = MusicManager.getVolume();
    System.out.println("Current volume: " + currentVolume);
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("MatchMission");
    stage.setScene(scene);
    stage.show();

  }

  public static void main(String[] args) {
    launch();
  }
}