package com.wmh.arithmetic.sort;

public class InsertionSort {

  public void sort(int[] array) {
    if (array == null) {
      return;
    }
    if (array.length <= 1) {
      return;
    }
    int temp;
    for (int i = 1; i < array.length; i++) {
      temp = array[i];
      int j = i - 1;
      for (; j >= 0; j--) {
        if (temp < array[j]) {
          array[j + 1] = array[j]; // 移动数据
        } else {
          break;
        }
      }
      array[j + 1] = temp; // 插入
    }
  }

  public static void main(String[] args) {
    int[] array = {4, 5, 6, 1, 2, 3};
    InsertionSort insertionSort = new InsertionSort();
    insertionSort.sort(array);

    for (int one : array) {
      System.out.print(one + "\t");
    }
    //
  }
}
