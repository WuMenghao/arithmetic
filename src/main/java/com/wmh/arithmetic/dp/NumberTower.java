package com.wmh.arithmetic.dp;

import java.util.Arrays;
import java.util.List;

public class NumberTower {

  public static int minimumTotal(List<List<Integer>> triangle) {
    int[][] dp = new int[triangle.size()][];
    // 初始化dp
    for (int i = 0; i < triangle.size(); i++) {
      dp[i] = new int[triangle.get(i).size()];
      for (int j = 0; j < triangle.get(i).size(); j++) {
        dp[i][j] = triangle.get(i).get(j);
      }
    }
    // 计算
    int temp_min;
    for (int i = dp.length - 2; i >= 0; i--) {
      for (int j = 0; j < dp[i].length; j++) {
        temp_min = Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
        dp[i][j] = temp_min + dp[i][j];
      }
    }
    return dp[0][0];
  }

  public static void main(String[] args) {
    // write your code here

    List<List<Integer>> data =
        Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3));
    System.out.println(minimumTotal(data));
  }
}
