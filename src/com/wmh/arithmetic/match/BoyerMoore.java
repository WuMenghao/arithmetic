package com.wmh.arithmetic.match;

/**
 * 字符串匹配BM算法
 *
 * @author menghaowu
 */
public class BoyerMoore {

  /**
   * 字符串匹配BF算法
   *
   * @return
   */
  public static int BMMatch(String str, String pattern) {

    return -1;
  }

  public static void main(String[] args) {
    String main = "255378";
    String pattern = "37";
    int index = BoyerMoore.BMMatch(main, pattern);
    System.out.println(index);
    System.out.println(main.substring(index, index + pattern.length()));
  }
}
