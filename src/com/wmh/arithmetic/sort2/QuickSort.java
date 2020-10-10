package com.wmh.arithmetic.sort2;

public class QuickSort {
  public void sort(int[] array) {
    if (array == null) {
      return;
    }
    if (array.length <= 1) {
      return;
    }
    partition(array, 0, array.length - 1);
  }

  // 分区
  private void partition(int[] array, int start, int end) {
    if (start >= end) return;
    int pivot = array[end];
    // (1)通过j,i进行值交换，保证小的值都在大值的前面
    // j最终记录的是第一个大于pivot的位置
    int j = start;
    for (int i = start; i < end; i++) {
      if (array[i] < pivot) {
        swap(array, i, j);
        j++;
      }
    }
    // (2)最终交换j与pivot的值
    swap(array, j, end);
    partition(array, start, j - 1);
    partition(array, j + 1, end);
  }

  private void swap(int[] array, int idx1, int idx2) {
    int temp;
    temp = array[idx1];
    array[idx1] = array[idx2];
    array[idx2] = temp;
  }

  public static void main(String[] args) {
    int[] array = {4, 5, 6, 1, 2, 3};
    QuickSort quickSort = new QuickSort();
    quickSort.sort(array);

    for (int one : array) {
      System.out.print(one + "\t");
    }
    //
  }
}
