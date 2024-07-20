package com.example.matchmissionapp;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController {
  private static final Logger logger = Logger.getLogger(MenuController.class.getName());

  @FXML
  private Button mainButton;

  @FXML
  private StackPane mainStackPane;

  @FXML
  private BorderPane mainBorderPane;

  @FXML
  private ImageView imageA;

  @FXML
  private ImageView imageB;

  @FXML
  private ImageView imageC;

  @FXML
  private ImageView imageD;

  @FXML
  private ImageView imageE;

  @FXML
  private ImageView imageF;

  @FXML
  private ImageView imageG;

  @FXML
  private ImageView imageH;

  @FXML
  private ImageView imageSettings;

  @FXML
  public void initialize() {
    logger.info("Initialising the Introduction stage");
    // Start rotate animations for the images
    startRotateTransition(imageA);
    startRotateTransition(imageB);
    startRotateTransition(imageC);
    startRotateTransition(imageD);
    startRotateTransition(imageE);
    startRotateTransition(imageF);
    startRotateTransition(imageG);
    startRotateTransition(imageH);
  }

  private void startRotateTransition(ImageView imageView) {
    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), imageView);
    rotateTransition.setByAngle(10); // Rotate 10 degrees
    rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
    rotateTransition.setAutoReverse(true); // Reverse the rotation
    rotateTransition.play();
  }

  @FXML
  public void gamePage() throws IOException {
    logger.info("Loading Game stage");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
    Parent gameParent = loader.load();

    // Access the GameController and its methods
    GameController gameController = loader.getController();

    // Get the current stage and set the new scene
    Stage stage = (Stage) mainBorderPane.getScene().getWindow();
    stage.setScene(new Scene(gameParent));
    stage.show();
  }

  @FXML
  public void settingsPage() throws IOException {
    logger.info("Loading Settings stage");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
    Parent settingsParent = loader.load();

    // Access the SettingsController and its methods
    SettingsController settingsController = loader.getController();

    // Get the current stage and set the new scene
    Stage stage = (Stage) mainBorderPane.getScene().getWindow();
    stage.setScene(new Scene(settingsParent));
    stage.show();
  }
}
