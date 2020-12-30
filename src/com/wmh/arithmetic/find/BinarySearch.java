package com.wmh.arithmetic.find;

public class BinarySearch {

  public int search(int[] array, int value) {
    if (array == null) {
      return -1;
    }
    if (array.length == 0) {
      return -1;
    }
    if (array.length == 1) {
      return 0;
    }
    return binarySearch(array, value, 0, array.length - 1);
  }

  private int binarySearch(int[] array, int value, int start, int end) {
    // 终止条件
    if (start > end) {
      return -1;
    }
    int midIdx = (end - start) >> 1 + start;
    int midValue = array[midIdx];
    if (midValue == value) {
      return midIdx;
    } else if (midValue > value) {
      return binarySearch(array, value, start, midIdx - 1);
    } else {
      return binarySearch(array, value, midIdx + 1, end);
    }
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6};
    BinarySearch binarySearch = new BinarySearch();
    int search = binarySearch.search(array, 1);
    System.out.printf("所在index:%d,值:%d \n\t", search, array[search]);
    //
  }
}
