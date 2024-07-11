package com.example.matchmissionapp;


import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MenuController {
  private static final Logger logger = Logger.getLogger(MenuController.class.getName());
//  @FXML
//  private Label welcomeText;

  @FXML
  private Button mainButton;
  //  @FXML
//  private BorderPane introBorderPane;
  @FXML
  private StackPane mainStackPane;
  @FXML
  private Circle circleA;
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
    Parent gameParent = loader.load();

    // Access the GameController and its methods
    SettingsController settingsController = loader.getController();

    // Get the current stage and set the new scene
    Stage stage = (Stage) mainBorderPane.getScene().getWindow();
    stage.setScene(new Scene(gameParent));
    stage.show();

  }

}