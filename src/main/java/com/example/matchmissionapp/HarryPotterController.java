package com.example.matchmissionapp;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  private final SequentialTransition sequentialTransition = new SequentialTransition();

  @FXML
  public void initialize() {
    System.out.println("HarryPotterController initialize() called");

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
    ImageView leaderImage = leaderImages.get(leaderIndex);
    ImageView followerImage = followerImages.get(followerIndex);

    if (leaderImage == null || followerImage == null) {
      return;
    }

    hpBorderPane.applyCss();
    hpBorderPane.layout();

    Bounds leaderBounds = leaderImage.localToScene(leaderImage.getBoundsInLocal());
    Bounds followerBounds = followerImage.localToScene(followerImage.getBoundsInLocal());

    double leaderX = leaderBounds.getMinX();
    double leaderY = leaderBounds.getMinY();
    double followerX = followerBounds.getMinX();
    double followerY = followerBounds.getMinY();

    // Offset to prevent overlap
    double offset = 30; // Adjust this value as needed

    // New target position for the leader image
    double targetX = followerX + followerBounds.getWidth() + offset;
    double targetY = followerY;

    TranslateTransition transition = new TranslateTransition(Duration.seconds(5), leaderImage);
    transition.setToX(targetX - leaderX);
    transition.setToY(targetY - leaderY);

    sequentialTransition.getChildren().add(transition);
  }
}
