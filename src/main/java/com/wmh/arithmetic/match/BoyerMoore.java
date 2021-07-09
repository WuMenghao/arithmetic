package com.wmh.arithmetic.match;

import java.io.UnsupportedEncodingException;

/**
 * 字符串匹配BM算法
 *
 * @author menghaowu
 */
public class BoyerMoore {
  /*
  1.坏字符规则
  我们从模式串的末尾往前倒着匹配，当我们发现某个字符没法匹配的时候。我们把这个没有匹配的字符叫作坏字符。
  当发生不匹配的时候，我们把坏字符对应的模式串中的字符下标记作si。如果坏字符在模式串中存在，我们把这个坏字符在模式串中的下标记作xi。如果不存在，我们把xi记作-1。那模式串往后移动的位数就等于si-xi。
  2.好后缀规则
  我们从模式串的末尾往前倒着匹配，出现坏字符之前的可以匹配的主串中的字符串就是好后缀，记作{u}。
  我们{u}在模式串中查找，如果找到了另一个跟{u}相匹配的子串{u*}，那我们就将模式串滑动到子串{u*}与主串中{u}对齐的位置。
  如果模式串中不存在与{u}匹配的子串，我们从好后缀的后缀子串中，找一个最长的并且能跟模式串的前缀子串匹配的，假设是{v},并将模式串滑动到与 {v}平齐的位置（本步骤防止过度滑动）
  3.最后
  我们可以分别计算好后缀和坏字符往后滑动的位数，然后取两个数中最大的，作为模式串往后滑动的位数。这种处理方法还可以避免我们前面提到的，根据坏字符规则，计算得到的往后滑动的位数，有可能是负数的情况。
   */

  private static final Integer SIZE = 256;

  /**
   * 字符串匹配BF算法
   *
   * @return
   */
  public static int BMMatch(String str, String pattern) throws UnsupportedEncodingException {
    // 转字节数组
    char[] a = str.toCharArray();
    char[] b = pattern.toCharArray();
    // 长度
    int n = str.length();
    int m = pattern.length();
    int[] bc = new int[SIZE]; // 坏字符哈希表
    generateBC(b, b.length, bc); // 构建坏字符哈希表
    // 构建好后缀规则
    int[] suffix = new int[m];
    boolean[] prefix = new boolean[m];
    generateGS(b, m, suffix, prefix);

    int i = 0;
    while (i <= n - m) {
      int j;
      // 模式串从后往前匹配
      for (j = m - 1; j >= 0; --j) {
        if (a[i + j] != b[j]) break; // 出现j为坏字符
      }
      // 匹配成功，返回第一个匹配字符的位置
      if (j < 0) return i;
      // 使用坏字符规则滑动
      int x = (j - bc[(int) a[i + j]]);
      // 好后缀规则滑动量
      int y = 0;
      if (j < m - 1) {
        y = moveByGS(j, m, suffix, prefix);
      }
      i = i + Math.max(x, y);
    }
    // 无任何字串匹配
    return -1;
  }

  // 构建坏字符哈希表
  private static void generateBC(char[] b, int m, int[] bc) {
    // 初始化 bad char 映射表
    for (int i = 0; i < SIZE; i++) {
      bc[i] = -1;
    }
    // 计算模式串各字符ascii码值 对应设置到 bar char 映射表中
    for (int i = 0; i < m; i++) {
      int ascii = (int) b[i];
      bc[ascii] = i;
    }
  }

  // 构建好后缀索引表 与 最长好后缀前缀字串
  private static void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
    // 初始化好后缀 和 最长好后缀前缀
    for (int i = 0; i < m; i++) {
      suffix[i] = -1;
      prefix[i] = false;
    }

    for (int k = 1; k < m - 1; k++) {
      int kIdxMin = Integer.MAX_VALUE;
      for (int i = k; i < m; i++) {
        int j = i - 1;
        int ki = m - 1;
        while (j >= i - k) {
          if (b[j] != b[ki]) break;
          if (j == i - k && j < kIdxMin) kIdxMin = j;
          j--;
          ki--;
        }
      }
      suffix[k] = kIdxMin == Integer.MAX_VALUE ? -1 : kIdxMin;
      prefix[k] = kIdxMin == 0;
    }
  }

  private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
    int k = m - 1 - j; //
    // 好后缀长度
    if (suffix[k] != -1) return j - suffix[k] + 1;
    for (int r = j + 2; r <= m - 1; ++r) {
      if (prefix[m - r] == true) {
        return r;
      }
    }
    return m;
  }

  //  String pattern = "23232323";
  //  int[] suffix = new int[pattern.length()];
  //  boolean[] prefix = new boolean[pattern.length()];
  //  generateGS(pattern.toCharArray(), pattern.length(), suffix, prefix);
  //    System.out.println("");

  public static void main(String[] args) throws UnsupportedEncodingException {
    long start = System.nanoTime();
    String main =
        "Prometheus is an open-source systems monitoring and alerting toolkit originally built at SoundCloud. Since its inception in 2012, many companies and organizations have adopted Prometheus, and the project has a very active developer and user community. It is now a standalone open source project and maintained independently of any company. To emphasize this, and to clarify the project's governance structure, Prometheus joined the Cloud Native Computing Foundation in 2016 as the second hosted project, after Kubernetes.\n"
            + "\n"
            + "For more elaborate overviews of Prometheus, see the resources linked from the media section.\n"
            + "\n"
            + "Features\n"
            + "Prometheus's main features are:\n"
            + "\n"
            + "a multi-dimensional data model with time series data identified by metric name and key/value pairs\n"
            + "PromQL, a flexible query language to leverage this dimensionality\n"
            + "no reliance on distributed storage; single server nodes are autonomous\n"
            + "time series collection happens via a pull model over HTTP\n"
            + "pushing time series is supported via an intermediary gateway\n"
            + "targets are discovered via service discovery or static configuration\n"
            + "multiple modes of graphing and dashboarding support\n"
            + "Components\n"
            + "The Prometheus ecosystem consists of multiple components, many of which are optional:\n"
            + "\n"
            + "the main Prometheus server which scrapes and stores time series data\n"
            + "client libraries for instrumenting application code\n"
            + "a push gateway for supporting short-lived jobs\n"
            + "special-purpose exporters for services like HAProxy, StatsD, Graphite, etc.\n"
            + "an alertmanager to handle alerts\n"
            + "various support tools\n"
            + "Most Prometheus components are written in Go, making them easy to build and deploy as static binaries.";
    String pattern = "dashboarding";
    int index = BoyerMoore.BMMatch(main, pattern);
    long end = System.nanoTime();
    System.out.println(index);
    System.out.println(main.substring(index, index + pattern.length()));
    System.out.println("Use time, " + (end - start) + "ns");
  }
}
