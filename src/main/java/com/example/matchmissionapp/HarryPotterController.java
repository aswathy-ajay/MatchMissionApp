package com.example.matchmissionapp;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class HarryPotterController {

  @FXML
  private BorderPane hpBorderPane;
  @FXML
  private ImageView harry;
  @FXML
  private ImageView ron;
  @FXML
  private ImageView neville;
  @FXML
  private ImageView cho;
  @FXML
  private ImageView hermione;
  @FXML
  private ImageView luna;

  private final GaleShapley galeShapley = new GaleShapley();

  private final Map<Integer, ImageView> leaderImages = new HashMap<>();
  private final Map<Integer, ImageView> followerImages = new HashMap<>();

  // To sequence the animations
  private SequentialTransition sequentialTransition = new SequentialTransition();

  @FXML
  public void initialize() {
    System.out.println("HarryPotterController initialize() called");

    // Log the ImageViews to ensure they are correctly injected
    System.out.println("Harry ImageView: " + harry);
    System.out.println("Ron ImageView: " + ron);
    System.out.println("Neville ImageView: " + neville);
    System.out.println("Cho ImageView: " + cho);
    System.out.println("Hermione ImageView: " + hermione);
    System.out.println("Luna ImageView: " + luna);

    // Setup the leader and follower maps
    leaderImages.put(0, harry);
    leaderImages.put(1, ron);
    leaderImages.put(2, neville);

    followerImages.put(0, cho);
    followerImages.put(1, hermione);
    followerImages.put(2, luna);

    // Print maps to verify setup
    System.out.println("Leader Images Map: " + leaderImages);
    System.out.println("Follower Images Map: " + followerImages);

    resolvePreferences();
  }

  public void resolvePreferences() {
    List<List<Integer>> leadersList = new ArrayList<>();
    leadersList.add(Arrays.asList(1, 0, 2)); // Harry's preferences
    leadersList.add(Arrays.asList(0, 1, 2)); // Ron's preferences
    leadersList.add(Arrays.asList(0, 1, 2)); // Neville's preferences

    List<List<Integer>> followersList = new ArrayList<>();
    followersList.add(Arrays.asList(2, 1, 0)); // Cho's preferences
    followersList.add(Arrays.asList(1, 0, 2)); // Hermione's preferences
    followersList.add(Arrays.asList(0, 2, 1)); // Luna's preferences

    // Set up the listener to handle match updates
    galeShapley.setMatchUpdateListener((leader, follower) -> {
      System.out.println("Animating match: Leader " + leader + ", Follower " + follower);
      animateImages(leader, follower);
    });

    // Start the Gale-Shapley algorithm
    int[] result = galeShapley.initialize(leadersList, followersList);

    // Print final matches
    System.out.println("Final matches:");
    for (int followerIndex = 0; followerIndex < result.length; followerIndex++) {
      int leaderIndex = result[followerIndex];
      System.out.println("Follower " + followerIndex + " -> Leader " + leaderIndex);
    }

    // Play the sequential transition
    sequentialTransition.play();
  }

  private void animateImages(Integer leaderIndex, Integer followerIndex) {
    // Retrieve the ImageViews from the maps
    ImageView leaderImage = leaderImages.get(leaderIndex);
    ImageView followerImage = followerImages.get(followerIndex);

    // Log positions if images are valid
    if (leaderImage == null) {
      System.err.println("Error: Leader ImageView is null for leaderIndex " + leaderIndex);
    }
    if (followerImage == null) {
      System.err.println("Error: Follower ImageView is null for followerIndex " + followerIndex);
    }

    // Skip animation if either image is null
    if (leaderImage == null || followerImage == null) {
      return;
    }

    hpBorderPane.applyCss();
    hpBorderPane.layout();

    Bounds leaderBounds = leaderImage.localToScene(leaderImage.getBoundsInLocal());
    Bounds followerBounds = followerImage.localToScene(followerImage.getBoundsInLocal());

    System.out.println("Leader Position: " + leaderBounds);
    System.out.println("Follower Position: " + followerBounds);

    double leaderX = leaderBounds.getMinX();
    double leaderY = leaderBounds.getMinY();
    double followerX = followerBounds.getMinX();
    double followerY = followerBounds.getMinY();

    TranslateTransition transition = new TranslateTransition(Duration.seconds(2), leaderImage);
    transition.setToX(followerX - leaderX);
    transition.setToY(followerY - leaderY);

    sequentialTransition.getChildren().add(transition);
  }
}
