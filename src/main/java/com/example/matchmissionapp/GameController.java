package com.example.matchmissionapp;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {
  private static final Logger logger = Logger.getLogger(GameController.class.getName());

  @FXML
  private BorderPane gameBorderPane;

  @FXML
  private TextFlow textFlowA;

  private IntroController introController;


  Font customFont;
  @FXML
  public void initialize() {
    logger.info("Initializing the game");

    // Ensure the pane can receive key events
    gameBorderPane.setFocusTraversable(true);
    gameBorderPane.requestFocus();

    customFont = Font.loadFont(getClass().getResourceAsStream("/style/NotoSerif-Regular.ttf"), 25);
    // Display the text word by word
    displayTextWordByWord("Ever wondered how schools find the right students or how dating sites match people? " + "\n"
        + "The secret lies in the Matching Algorithm! " + "\n"
        + "Today, we’ll explore how matching algorithms work and discover their powerful impact. " + "\n"
        + "Let's start with a quick introduction to matching problem. "
        );
  }

  @FXML
  private void handleKeyPressed(KeyEvent event) throws IOException {
    logger.info("HANDLE IS WORKING");
    if (event.getCode() == KeyCode.ESCAPE) {
      logger.info("ESC key pressed, navigating back to the menu...");
      introController.startMenu();
    }
  }

//  @FXML
//  public void startMenu() throws IOException {
//    logger.info("Navigating back to the menu page...");
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//    Parent menuParent = loader.load();
//
//    // Get the current stage and set the new scene
//    Stage stage = (Stage) gameBorderPane.getScene().getWindow();
//    stage.setScene(new Scene(menuParent));
//    stage.show();
//  }

  private void displayTextWordByWord(String message) {
    String[] words = message.split(" ");
    Timeline timeline = new Timeline();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      Text text = new Text(word + " ");
      text.setStyle("-fx-fill: black;");  // Set the text color to black
      text.setFont(customFont);
      KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.3), e -> textFlowA.getChildren().add(text));
      timeline.getKeyFrames().add(keyFrame);
    }
    timeline.play();
  }

  @FXML
  private void nextScreen() throws IOException {
    logger.info("Navigating to the next game screen...");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
    Parent menuParent = loader.load();

    // Get the current stage and set the new scene
    Stage stage = (Stage) gameBorderPane.getScene().getWindow();
    stage.setScene(new Scene(menuParent));
    stage.show();
  }
}
