package com.wmh.arithmetic.sort3;

import com.wmh.arithmetic.RandomUtil;

public class CountingSort {
  public void sort(int[] array) {
    if (array == null) {
      return;
    }
    if (array.length <= 1) {
      return;
    }

    // (1)计算分桶数 分桶数 = max + 1
    int max = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }

    // (2)分桶计数
    int[] counts = new int[max + 1];
    for (int i = 0; i < array.length; i++) {
      counts[array[i]]++;
    }

    // (3)计数累加
    for (int i = 1; i < counts.length; i++) {
      counts[i] = counts[i] + counts[i - 1];
    }

    // (4)排序到临时数组
    int[] temp = new int[array.length];
    int index;
    for (int i = array.length - 1; i >= 0; i--) {
      index = counts[array[i]] - 1;
      temp[index] = array[i];
      counts[array[i]]--;
    }

    // (5)拷贝
    for (int i = 0; i < array.length; i++) {
      array[i] = temp[i];
    }
  }

  public static void main(String[] args) {
    int[] array = RandomUtil.genRandomArray(10000, 10000);
    CountingSort countingSort = new CountingSort();
    long start = System.currentTimeMillis();
    countingSort.sort(array);
    long end = System.currentTimeMillis();
    System.out.println("耗时:" + (end - start) + "ms");

    for (int one : array) {
      System.out.print(one + "\t");
    }
    //
  }
}
