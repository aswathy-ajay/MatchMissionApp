package com.example.matchmissionapp;

import java.util.Arrays;

public class trial {

  public static void main(String[] args) {
    int n = 4; // Number of men/women
    int[][] menPreferences = {
        {0, 1, 2, 3},
        {1, 0, 2, 3},
        {2, 0, 1, 3},
        {0, 1, 2, 3}
    };

    int[][] womenPreferences = {
        {0, 1, 2, 3},
        {1, 0, 2, 3},
        {2, 0, 1, 3},
        {0, 1, 2, 3}
    };

    int[] matches = galeShapley(n, menPreferences, womenPreferences);

    System.out.println("Matches: " + Arrays.toString(matches));
  }

  public static int[] galeShapley(int n, int[][] menPreferences, int[][] womenPreferences) {
    int[] womenPartner = new int[n];
    boolean[] menFree = new boolean[n];
    int[] menNextProposal = new int[n];

    Arrays.fill(womenPartner, -1);
    Arrays.fill(menFree, true);

    int freeCount = n;

    while (freeCount > 0) {
      int man;
      for (man = 0; man < n; man++) {
        if (menFree[man]) {
          break;
        }
      }

      int woman = menPreferences[man][menNextProposal[man]];
      menNextProposal[man]++;

      if (womenPartner[woman] == -1) {
        womenPartner[woman] = man;
        menFree[man] = false;
        freeCount--;
      } else {
        int currentPartner = womenPartner[woman];
        if (prefersNewPartner(womenPreferences, woman, man, currentPartner)) {
          womenPartner[woman] = man;
          menFree[man] = false;
          menFree[currentPartner] = true;
        }
      }
    }

    int[] menPartner = new int[n];
    for (int woman = 0; woman < n; woman++) {
      menPartner[womenPartner[woman]] = woman;
    }

    return menPartner;
  }

  private static boolean prefersNewPartner(int[][] preferences, int woman, int newMan, int currentMan) {
    for (int i = 0; i < preferences[woman].length; i++) {
      if (preferences[woman][i] == newMan) {
        return true;
      }
      if (preferences[woman][i] == currentMan) {
        return false;
      }
    }
    return false;
    }
}
