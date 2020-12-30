package com.wmh.arithmetic.sort.nlogn;

/*
 *
 * 堆排序算法
 * 时间复杂度：O(nlogn + n)
 * 稳定性：不稳定，堆顶元素与最后一个元素互换
 * 空间复杂度：O(0) 原地排序算法
 * */
public class HeapSort {

  public void sort(int[] array) {
    // 建堆
    buildHeap(array);
    // 排序
    int k = array.length - 1;
    while (k > 0) {
      // 交换堆顶和最后一个元素
      swap(array, 0, k);
      k--;
      // 再次堆化
      heapify(array, k, 0);
    }
  }

  /**
   * 建堆
   *
   * @param array
   */
  private void buildHeap(int[] array) {
    for (int i = array.length / 2 - 1; i >= 0; i--) {
      heapify(array, array.length - 1, i);
    }
  }

  /**
   * 堆化
   *
   * @param array
   * @param end
   * @param index
   */
  private void heapify(int[] array, int end, int index) {
    while (true) {
      int maxPos = index;
      // 比较右节点
      if (index * 2 + 1 <= end && array[index] < array[index * 2 + 1]) {
        maxPos = index * 2 + 1;
      }
      // 比较左节点
      if (index * 2 + 2 <= end && array[maxPos] < array[index * 2 + 2]) {
        maxPos = index * 2 + 2;
      }
      // 是否是叶子节点
      if (maxPos == index) {
        break;
      }
      // 交换数值
      swap(array, index, maxPos);
      // 定位下一轮堆化的节点位置
      index = maxPos;
    }
  }

  /**
   * 交换
   *
   * @param array
   * @param i1
   * @param i2
   */
  private void swap(int[] array, int i1, int i2) {
    int temp = array[i1];
    array[i1] = array[i2];
    array[i2] = temp;
  }

  public static void main(String[] args) {
    int[] array = {4, 5, 6, 1, 2, 3};
    HeapSort heapSort = new HeapSort();

    long start = System.currentTimeMillis();
    heapSort.sort(array);
    long end = System.currentTimeMillis();
    System.out.printf("耗时:%d sm \n", end - start);

    for (int one : array) {
      System.out.print(one + "\t");
    }
  }
}
