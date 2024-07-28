package com.example.matchmissionapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GaleShapley {

  public String[] initialize(List<List<Integer>> leadersList, List<List<Integer>> followersList) {
//    // Example for leaders and followers with preferences for each
//    List<List<Integer>> leadersList = new ArrayList<>();
//    leadersList.add(Arrays.asList(0, 1, 2, 3));
//    leadersList.add(Arrays.asList(1, 0, 2, 3));
//    leadersList.add(Arrays.asList(1, 0, 3));
//
//    List<List<Integer>> followersList = new ArrayList<>();
//    followersList.add(Arrays.asList(0, 1, 2, 3));
//    followersList.add(Arrays.asList(1, 0, 2, 3));
//    followersList.add(Arrays.asList(2, 0, 1, 3));
//    followersList.add(Arrays.asList(0, 1, 2, 3));

    int num_leaders = leadersList.size();
    int num_followers = followersList.size();

    // Creates a list of length same as followers with value -1
    int[] match = new int[num_followers];
    Arrays.fill(match, -1);

    // Creates a list of length same as leadersList with value 0. The use of this is to track the index of the followers that has been proposed to
    int[] next_proposal = new int[num_leaders];
    Arrays.fill(next_proposal, 0);

    // Indices of leaders list
    List<Integer> free_leaders = new ArrayList<>();
    for (int i = 0; i < num_leaders; i++) {
      free_leaders.add(i);
    }

    galeShapley(leadersList, followersList, num_leaders, num_followers, match, next_proposal, free_leaders);

    String[] final_matches = new String[num_followers];
    Arrays.fill(final_matches, "Not Matched");
    for (int follower = 0; follower < num_followers; follower++) {
      if (match[follower] != -1) {
        final_matches[follower] = String.valueOf(match[follower]);
      }
    }

    return final_matches;
  }

  public void galeShapley(List<List<Integer>> leadersList, List<List<Integer>> followersList, int num_leaders, int num_followers, int[] match, int[] next_proposal, List<Integer> free_leaders) {
    while (!free_leaders.isEmpty()) {
      int leader = free_leaders.remove(0);
      List<Integer> leader_prefs = leadersList.get(leader);

      if (next_proposal[leader] >= leader_prefs.size()) {
        // Check if the leader has already finished proposing to all the followers from their preference list
        continue;
      }

      int follower_index = next_proposal[leader];  // Index of the follower that needs to be proposed to is given by the next proposal list
      int follower = leader_prefs.get(follower_index); // Getting that follower which has not been proposed to
      //here add animation
      // As we completed one proposal change the index in next_proposal
      next_proposal[leader] += 1;

      if (match[follower] == -1) { // Follower is not engaged
        match[follower] = leader;
      } else { // Follower is engaged
        int current_partner = match[follower];
        List<Integer> follower_prefs = followersList.get(follower);

        if (follower_prefs.indexOf(leader) < follower_prefs.indexOf(current_partner)) {
          match[follower] = leader;
          free_leaders.add(current_partner);
        } else {
          free_leaders.add(leader);
        }
      }
    }
  }
}

