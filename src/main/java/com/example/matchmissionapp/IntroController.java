package com.example.matchmissionapp;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class IntroController {

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

//  @FXML
//  protected void onHelloButtonClick() {
//    welcomeText.setText("Test 1");
//  }

  @FXML
  public void initialize() {
//    circleA.setRadius(150.0);
//    introAnchorPane.setStyle("-fx-background-color: #f0f0f0;");
  }

  @FXML
  public void gamePage() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
    Parent gameParent = loader.load();

    // Access the GameController and its methods
    GameController gameController = loader.getController();

    // Get the current stage and set the new scene
    Stage stage = (Stage) mainBorderPane.getScene().getWindow();
    stage.setScene(new Scene(gameParent));
    stage.show();
  }

}