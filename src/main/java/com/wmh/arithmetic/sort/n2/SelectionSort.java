package com.wmh.arithmetic.sort.n2;

public class SelectionSort {

  public void sort(int[] array) {
    if (array == null) {
      return;
    }
    if (array.length <= 1) {
      return;
    }
    int min;
    int index;
    for (int i = 0; i < array.length - 1; i++) {
      min = array[i];
      index = i;
      for (int j = i + 1; j < array.length - 1; j++) {
        if (array[j + 1] < min) {
          min = array[j + 1];
          index = j + 1; // 记录最小值 和 最小值索引
        }
      }
      array[index] = array[i];
      array[i] = min; // 交换最小值
    }
  }

  public static void main(String[] args) {
    int[] array = {4, 5, 6, 1, 2, 3};
    SelectionSort selectionSort = new SelectionSort();
    selectionSort.sort(array);

    for (int one : array) {
      System.out.print(one + "\t");
    }
    //
  }
}
