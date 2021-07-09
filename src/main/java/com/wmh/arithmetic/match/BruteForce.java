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
    String pattern = "making them easy to build and deploy as static binaries";
    int index = BruteForce.BFMatch(main, pattern);
    long end = System.nanoTime();
    System.out.println(index);
    System.out.println(main.substring(index, index + pattern.length()));
    System.out.println("Use time, " + (end - start) + "ns");
  }
}
