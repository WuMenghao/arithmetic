package com.wmh.arithmetic.backtracking;

public class Queen8 {

  private static int[] resutl = new int[8];

  private static boolean finish = false;

  private static void calculatePerRow(int row) {
    // 结束条件
    if (row == 8) {
      finish = true;
      return;
    }
    for (int column = 0; column < 8; column++) { // 每行有八种方法
      if (check(row, column)) { // 该行符合要求
        if (finish) return; // 标记为结束后不再递归，直接结束
        resutl[row] = column;
        calculatePerRow(row + 1); // 下一行
      }
    }
  }

  private static boolean check(int row, int column) {
    int leftUp = column - 1, rigtUp = column + 1; // 对角线初始值
    for (int i = row - 1; i >= 0; i--) { // 逐行往上考察每一行
      if (resutl[i] == column) return false; // 列
      if (leftUp >= 0 && resutl[i] == leftUp) return false; // 左对角线
      if (rigtUp < 8 && resutl[i] == rigtUp) return false; // 右对角线
      --leftUp;
      ++rigtUp;
    }
    return true;
  }

  private static void printQueen(int[] resutl) {
    for (int row = 0; row < 8; row++) {
      for (int colum = 0; colum < 8; colum++) {
        if (resutl[row] == colum) {
          System.out.print("Q  ");
        } else {
          System.out.print("*  ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    calculatePerRow(0);
    printQueen(resutl);
  }
}
