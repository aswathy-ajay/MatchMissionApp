package com.example.matchmissionapp;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class SettingsController {
  @FXML
  private BorderPane settingsBorderPane;
  @FXML
  private Slider volumeSlider;

  @FXML
  public void initialize() {
    volumeSlider.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
      double newVolume = newValue.doubleValue();
      MusicManager.setVolume(newVolume);
    }));

  }

}
