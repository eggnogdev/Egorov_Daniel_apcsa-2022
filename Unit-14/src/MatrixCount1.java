//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

import java.io.*;
import java.util.*;

public class MatrixCount1 {

  private static int[][] m = {
    { 1, 2, 3, 4, 5 },
    { 6, 7, 8, 9, 0 },
    { 6, 7, 1, 2, 5 },
    { 6, 7, 8, 9, 0 },
    { 5, 4, 3, 2, 1 }
  };

  public static int count(int val) {
    //add code
    int count = 0;
    for (int i = 0; i < MatrixCount1.m.length; i++) {
      for (int j = 0; j < MatrixCount1.m[i].length; j++) {
        if (MatrixCount1.m[i][j] == val) count++;
      }
    }
    return count;
  }
}
