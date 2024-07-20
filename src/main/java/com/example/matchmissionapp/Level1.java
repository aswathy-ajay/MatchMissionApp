package com.example.matchmissionapp;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
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
  public void initialize() {
    logger.info("Initializing the game");

    // Ensure the pane can receive key events
    level1BorderPane.setFocusTraversable(true);
    level1BorderPane.requestFocus();

    customFont = Font.loadFont(getClass().getResourceAsStream("/style/NotoSerif-Regular.ttf"), 25);
//    displayTextWordByWord("In any matching algorithm, we have participants with their own preferences, and our goal is to create a stable one-to-one match. " + "\n"
//        + "Let’s dive into a fun example: " + "\n"
//        + "Imagine Elsa from Frozen and Rapunzel from Tangled. They each have two choices: the magical Frozen Castle or the vibrant tower from Tangled." + "\n" + "The challenge? To pair them with their preferred location. " + "\n"
//        + "Elsa dreams of the castle, while Rapunzel is enchanted by the tower. Let's match them to their choices! "
//    );
    displayTextWordByWord("Blah blah");
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
    // Ensure layout is properly updated before accessing positions
    level1BorderPane.layout();

    // Get current positions of the images
    double elsaX = elsaImage.getLayoutX();
    double elsaY = elsaImage.getLayoutY();
    double rapunzelX = rapunzelImage.getLayoutX();
    double rapunzelY = rapunzelImage.getLayoutY();

    double castleX = castleImage.getLayoutX();
    double castleY = castleImage.getLayoutY();

    // Calculate target positions
    double towerTargetX = rapunzelX;
    double towerTargetY = rapunzelY;

    double elsaTargetX = elsaX;
    double elsaTargetY = elsaY;

    // Define animations for moving the images
    TranslateTransition towerTransition = new TranslateTransition(Duration.seconds(2), towerImage);
    towerTransition.setToX(towerTargetX - towerImage.getLayoutX());
    towerTransition.setToY(towerTargetY - towerImage.getLayoutY());

    TranslateTransition castleTransition = new TranslateTransition(Duration.seconds(2), castleImage);
    castleTransition.setToX(elsaTargetX - castleImage.getLayoutX());
    castleTransition.setToY(elsaTargetY - castleImage.getLayoutY());

    // Play the animations
    towerTransition.play();
    castleTransition.play();
  }



//  @FXML
//  private void handleKeyPressed(KeyEvent event) throws IOException {
//    logger.info("HANDLE IS WORKING");
//    if (event.getCode() == KeyCode.ESCAPE) {
//      logger.info("ESC key pressed, navigating back to the menu...");
//      introController.startMenu();
//    }
//  }
}
