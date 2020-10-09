package com.wmh.arithmetic.sort;

public class BubbleSort {

  public void sort(int[] array) {
    if (array == null) {
      return;
    }
    if (array.length <= 1) {
      return;
    }
    int temp;
    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array.length - i; j++) {
        if (array[j + 1] < array[j]) {
          temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] array = {4, 5, 6, 1, 2, 3};
    BubbleSort bubbleSort = new BubbleSort();
    bubbleSort.sort(array);

    for (int one : array) {
      System.out.print(one + "\t");
    }
    //
  }
}
