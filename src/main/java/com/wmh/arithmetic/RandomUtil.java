package com.wmh.arithmetic;

import java.util.Random;

public class RandomUtil {
  // 从 m个整数中随机抽取 n个整数，条件: m > n
  public static int[] genRandomArray(int m, int n) {
    if (m <= 0) return null;
    if (n <= 0) return null;
    if (m < n) return null;

    int[] intRet = new int[n];
    int intRd = 0; // 存放随机数
    int count = 0; // 记录生成的随机数个数
    boolean flag = false; // 是否已经生成过标志
    while (count < n) {
      Random rdm = new Random(System.currentTimeMillis());
      intRd = Math.abs(rdm.nextInt()) % m + 1;
      for (int i = 0; i < count; i++) {
        if (intRet[i] == intRd) {
          flag = true;
          break;
        } else {
          flag = false;
        }
      }
      if (flag == false) {
        intRet[count] = intRd;
        count++;
      }
    }
    return intRet;
  }
}
