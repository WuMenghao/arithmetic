package com.wmh.arithmetic.match;

/**
 * 字符串匹配BF算法
 *
 * @author menghaowu
 */
public class BruteForce {

  /**
   * 字符串匹配BF算法
   *
   * @return
   */
  public static int BFMatch(String str, String pattern) {
    char[] pattenChars = pattern.toCharArray();
    char[] strChars = str.toCharArray();
    for (int i = 0; i < strChars.length - pattenChars.length; i++) {
      for (int j = 0; j < pattenChars.length; j++) {
        // 主串字符下标
        int k = i + j;
        // 不匹配进入下一轮
        if (strChars[k] != pattenChars[j]) break;
        // 匹配返回字串首字母下标
        if (j == pattenChars.length - 1) return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    String main = "255378";
    String pattern = "37";
    int index = BruteForce.BFMatch(main, pattern);
    System.out.println(index);
    System.out.println(main.substring(index, index + pattern.length()));
  }
}
