package com.wmh.arithmetic.sort2;

public class MergeSort {
  public void sort(int[] array) {
    if (array == null) {
      return;
    }
    if (array.length <= 1) {
      return;
    }
    margeSort(array, 0, array.length - 1);
  }

  public void margeSort(int[] array, int start, int end) {
    if (end <= start) return;
    int se = start + (end - start) / 2;
    int es = se + 1;
    margeSort(array, start, se);
    margeSort(array, es, end);
    marge(array, start, se, es, end);
  }

  public void marge(int[] array, int s, int se, int es, int e) {
    // 比较元素拷贝
    int[] temp = new int[e - s];
    int i = 0, j = s, k = es;
    while (j <= se && k <= e) {
      if (array[j] < array[k]) {
        temp[i++] = array[j++];
      } else {
        temp[i++] = array[k++];
      }
    }

    // 剩余元素拷贝
    int start = j, end = se;
    if (j < k) {
      start = k;
      end = e;
    }
    while (start <= end) {
      temp[i++] = array[start++];
    }

    // 拷贝数组
    for (i = 0; i < temp.length; i++) {
      array[s + i] = temp[i];
    }
  }

  public static void main(String[] args) {
    int[] array = {4, 5, 6, 1, 2, 3};
    MergeSort mergeSort = new MergeSort();
    mergeSort.sort(array);

    for (int one : array) {
      System.out.print(one + "\t");
    }
    //
  }
}
