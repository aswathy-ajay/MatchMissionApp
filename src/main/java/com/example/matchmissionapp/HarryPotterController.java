package com.example.matchmissionapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class HarryPotterController {
  @FXML
  private BorderPane hpBorderPane;
  GaleShapley galeShapley = new GaleShapley();
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
  List<List<Integer>> leadersList;
  List<List<Integer>> followersList;


  @FXML
  public void initialize() {
    resolvePreferences();
  }

  public void resolvePreferences () {
    leadersList = new ArrayList<>();
    leadersList.add(Arrays.asList(1, 0, 2));
    leadersList.add(Arrays.asList(0, 1, 2));
    leadersList.add(Arrays.asList(0, 1, 2));

    followersList = new ArrayList<>();
    followersList.add(Arrays.asList(2, 1, 0));
    followersList.add(Arrays.asList(1, 0, 2));
    followersList.add(Arrays.asList(0, 2, 1));
    String[] results = galeShapley.initialize(leadersList, followersList);
    displayResults(results);
  }

  private void displayResults(String[] results) {
    StringBuilder resultString = new StringBuilder("Final matches (follower index -> leader index):\n");
    for (int i = 0; i < results.length; i++) {
      resultString.append("Follower ").append(i).append(" -> Leader ").append(results[i]).append("\n");
    }
  }

}