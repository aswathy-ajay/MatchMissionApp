package com.example.matchmissionapp;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameController {
  private static final Logger logger = Logger.getLogger(GameController.class.getName());
  @FXML
  private BorderPane gameBorderPane;
  private IntroController introController;
  public void initialize() {
    logger.info("Initialising the game");

    gameBorderPane.setFocusTraversable(true);
//    gameBorderPane.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
    gameBorderPane.requestFocus();
  }

  @FXML
  private void handleKeyPressed (KeyEvent event) throws IOException {
    logger.info("HANDLE IS WORKING");
    if (event.getCode() == KeyCode.ESCAPE) {
      introController.startMenu();
    }
  }

  public void startMenu() throws IOException {
    logger.info("Navigating back to the menu page...");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
    Parent introParent = loader.load();

    // Get the current stage and set the new scene
    Stage stage = (Stage) gameBorderPane.getScene().getWindow();
    stage.setScene(new Scene(introParent));
    stage.show();
  }
}
