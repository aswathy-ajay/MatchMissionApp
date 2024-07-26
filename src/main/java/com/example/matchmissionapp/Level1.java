package com.example.matchmissionapp;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class Level1 {
  private static final Logger logger = Logger.getLogger(Level1.class.getName());

  @FXML
  private BorderPane level1BorderPane;

  @FXML
  private TextFlow textFlowB;

  Font customFont;

  @FXML
  private ImageView elsaImage;
  @FXML
  private ImageView towerImage;
  @FXML
  private ImageView rapunzelImage;
  @FXML
  private ImageView castleImage;

  @FXML
  private Button replayButton;
  @FXML
  private Button continueButton;
  IntroController introController;

  @FXML
  public void initialize() {
    logger.info("Initializing the game");

    // Ensure the pane can receive key events
    level1BorderPane.setFocusTraversable(true);
    level1BorderPane.requestFocus();

    introController = new IntroController();
    customFont = Font.loadFont(getClass().getResourceAsStream("/style/NotoSerif-Regular.ttf"), 20);
    displayTextWordByWord("In any matching algorithm, participants have preferences, and our goal is to create a stable one-to-one match. " + "\n"
        + "Let's dive into a fun example: Elsa from Frozen and Rapunzel from Tangled each have two choices—the Frozen Castle and the Tangled Tower. Elsa dreams of the castle, while Rapunzel is enchanted by the tower. Let's match them to their preferred locations! "
    );


  }

  @FXML
  private void handleKeyPressed(KeyEvent event) throws IOException {
    logger.info("HANDLE IS WORKING");
    if (event.getCode() == KeyCode.ESCAPE) {
      logger.info("ESC key pressed, navigating back to the menu...");
      introController.startMenu(level1BorderPane);
    }
  }

  private void displayTextWordByWord(String message) {
    String[] words = message.split(" ");
    Timeline timeline = new Timeline();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      Text text = new Text(word + " ");
      text.setStyle("-fx-fill: black;");  // Set the text color to black
      text.setFont(customFont);
      KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.3), e -> textFlowB.getChildren().add(text));
      timeline.getKeyFrames().add(keyFrame);
    }
    // Add a final key frame to start the animation after text display is complete
    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(words.length * 0.3), e -> animateImages()));
    timeline.play();
  }

  private void animateImages() {
    logger.info("Starting animation");
    // Ensure layout is properly updated before accessing positions
    level1BorderPane.applyCss();
    level1BorderPane.layout();

    // Convert local coordinates to scene coordinates
    Bounds elsaBounds = elsaImage.localToScene(elsaImage.getBoundsInLocal());
    Bounds rapunzelBounds = rapunzelImage.localToScene(rapunzelImage.getBoundsInLocal());
    Bounds towerBounds = towerImage.localToScene(towerImage.getBoundsInLocal());
    Bounds castleBounds = castleImage.localToScene(castleImage.getBoundsInLocal());

    // Get current positions of the images
    double elsaX = elsaBounds.getMinX();
    double elsaY = elsaBounds.getMinY();
    double rapunzelX = rapunzelBounds.getMinX();
    double rapunzelY = rapunzelBounds.getMinY();
    double towerX = towerBounds.getMinX();
    double towerY = towerBounds.getMinY();
    double castleX = castleBounds.getMinX();
    double castleY = castleBounds.getMinY();

    // Calculate target positions
    double towerTargetX = rapunzelX;
    double towerTargetY = rapunzelY;
    double castleTargetX = elsaX;
    double castleTargetY = elsaY;

    // Define animations for moving the images
    TranslateTransition towerTransition = new TranslateTransition(Duration.seconds(2), towerImage);
    towerTransition.setToX(towerTargetX - towerX); // x direction movement
    towerTransition.setToY(towerTargetY - towerY); // y direction movement

    TranslateTransition castleTransition = new TranslateTransition(Duration.seconds(2), castleImage);
    castleTransition.setToX(castleTargetX - castleX); // x direction movement
    castleTransition.setToY(castleTargetY - castleY); // y direction movement

    // Play the animations
    towerTransition.play();
    castleTransition.play();
  }

  @FXML
  public void nextPage() throws IOException {
    logger.info("Starting the harry potter question");
    introController.nextScreen(level1BorderPane,"harry-potter.fxml");
  }

  @FXML
  public void replay() {
    logger.info("Replaying the animation");
    animateImages();
  }


}
