package com.wmh.arithmetic.backtracking;

public class Backpack {
  // 限定重量
  private int wight;
  // 限定容积
  private int volume;
  // 装入物品到排列
  private int[] items;
  // 背包最大容量
  private int wightMax;
  // 装入物品到排列 零时量
  private int[] itemsTemp;
  // 当前重量
  private int currentWight;
  // 当前物品放入到位置index
  private int itemsTempIndex;

  public int[] getItems() {
    return items;
  }

  public Backpack(int wight, int volume) {
    this.wight = wight; // 限定重量
    this.volume = volume; // 限定容积
    this.items = new int[volume]; // 装入物品到排列
    this.itemsTemp = new int[volume]; // 装入物品到排列 零时量
    this.wightMax = Integer.MIN_VALUE; // 背包最大容量
    this.currentWight = 0; // 当前重量
    this.itemsTempIndex = 0; // 当前物品放入到位置index
  }

  public void full(int[] itemsArr) {
    fullOnce(0, itemsArr);
  }

  private void fullOnce(int itemSort, int[] itemsArr) {
    int currentItemIdx = itemsTempIndex;
    int thisCurrentWight = currentWight;
    //  已装满到情况 已装把最后一个物品装入 已达到重量
    if (itemsTempIndex == volume - 1
        || itemSort == itemsArr.length - 1
        || currentWight + itemsArr[itemSort] >= wight) {
      if (currentWight > wightMax) {
        wightMax = currentWight;
        System.arraycopy(itemsTemp, 0, items, 0, itemsTemp.length);
      }
      return;
    }
    // 未达到结束条件 装入当前item
    itemsTemp[itemsTempIndex] = itemSort;
    currentWight += itemsArr[itemSort];
    itemsTempIndex++;
    fullOnce(itemSort + 1, itemsArr);

    // 达到条件回溯 跳过当前item
    itemsTempIndex = currentItemIdx;
    currentWight = thisCurrentWight;
    for (int i = currentItemIdx; i < itemsTemp.length; i++) {
      itemsTemp[i] = 0;
    }
    fullOnce(itemSort + 1, itemsArr);
  }

  public static void main(String[] args) {
    Backpack backpack = new Backpack(100, 10);
    int[] item = {23, 19, 4, 3, 9, 10, 34, 27};
    backpack.full(item);
    System.out.println(backpack.wightMax);
    for (int i : backpack.items) {
      System.out.print(i + "\t");
    }
  }
}
