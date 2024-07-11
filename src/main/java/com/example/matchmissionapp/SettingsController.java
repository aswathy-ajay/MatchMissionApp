package com.example.matchmissionapp;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SettingsController {
  private static final Logger logger = Logger.getLogger(SettingsController.class.getName());

  private HelloApplication helloApplication;
  @FXML
  private BorderPane settingsBorderPane;
  @FXML
  private Slider volumeSlider;

  @FXML
  public void initialize() {
    logger.info("Initializing the settings stage");

    // Initialize slider value based on current volume
    volumeSlider.setValue(MusicManager.getVolume());

    // Listener for slider value changes
    volumeSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
      double newVolume = newValue.doubleValue();
      MusicManager.setVolume(newVolume);
      logger.info("Volume set to: " + newVolume);
    });

    // Set focus on the BorderPane to receive key events
    settingsBorderPane.setFocusTraversable(true);
    settingsBorderPane.requestFocus();


  }

  @FXML
  private void handleKeyPressed (KeyEvent event) throws IOException {
    if (event.getCode() == KeyCode.UP) {
      increaseVolume();
    } else if (event.getCode() == KeyCode.DOWN) {
      decreaseVolume();
    } else if (event.getCode() == KeyCode.ESCAPE) {
      introPage();
    }
  }

  private void increaseVolume() {
    double currentVolume =volumeSlider.getValue();
    if (currentVolume < 1.0) {
      logger.info("Increasing volume");
      double newVolume = currentVolume + 0.1;
      volumeSlider.setValue(newVolume);
      MusicManager.setVolume(newVolume);
    }
  }

  private void decreaseVolume() {
    double currentVolume =volumeSlider.getValue();
    if (currentVolume > 0.0) {
      logger.info("Decreasing volume");
      double newVolume = currentVolume - 0.1;
      volumeSlider.setValue(newVolume);
      MusicManager.setVolume(newVolume);
    }
    }

  public void introPage() throws IOException {
    logger.info("Navigating back to the intro page...");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
    Parent introParent = loader.load();

    // Get the current stage and set the new scene
    Stage stage = (Stage) settingsBorderPane.getScene().getWindow();
    stage.setScene(new Scene(introParent));
    stage.show();
  }

}
