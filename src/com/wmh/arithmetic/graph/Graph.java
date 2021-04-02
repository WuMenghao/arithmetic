package com.wmh.arithmetic.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
  private int v; // 顶点个数
  private LinkedList<Integer> adj[]; // 邻接表

  public Graph(int v) {
    this.v = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int source, int target) {
    adj[source].add(target);
    adj[target].add(source);
  }

  // BFS search
  public int bfs(int s, int t) {
    if (s == t) return s;
    // 已被访问过的节点
    boolean[] visited = new boolean[v];
    // 用于存储已被访问过，但相邻但节点未被访问过但节点
    Queue<Integer> queue = new LinkedList<>();
    // 用来记录搜索路径
    int[] prev = new int[v];
    for (int i = 0; i < v; i++) {
      prev[i] = -1;
    }
    // 将source元素放入队列
    queue.add(s);
    // 搜索逻辑
    while (queue.size() != 0) {
      int w = queue.poll();
      // 遍历边
      for (int i = 0; i < adj[w].size(); i++) {
        int q = adj[w].get(i);
        // 未搜索过但进行比较并标为已搜索
        if (!visited[q]) {
          prev[q] = w;
          // 相同打印路径返回元素
          if (q == t) {
            printPath(prev, s, t);
            return t;
          }
          // 不相同标为已访问 将相邻元素放入搜索队列
          visited[q] = true;
          queue.add(q);
        }
      }
    }

    return 0;
  }

  public int dfs(int s, int t) {
    // 已被访问过的节点
    boolean[] visited = new boolean[v];
    // 用来记录搜索路径
    int[] prev = new int[v];
    for (int i = 0; i < v; i++) {
      prev[i] = -1;
    }
    // 递归进行广度优先搜索
    int rs = doDfs(s, t, visited, prev);
    // 打印搜索路径
    printPath(prev, s, t);
    return rs;
  }

  private int doDfs(int w, int t, boolean[] visited, int[] prev) {
    // 找到元素结束搜索
    if (w == t) return t;
    // 未找到标为已访问元素 dfs相邻节点
    visited[w] = true;
    for (int i = 0; i < adj[w].size(); i++) {
      int q = adj[w].get(i);
      if (!visited[q]) {
        prev[q] = w;
        doDfs(q, t, visited, prev);
      }
    }
    return 0;
  }

  private void printPath(int[] prev, int s, int t) {
    if (prev[t] != -1 && t != s) {
      printPath(prev, s, prev[t]);
    }
    System.out.println(t + "");
  }

  public static void main(String[] args) {
    Graph graph = new Graph(10);
    graph.addEdge(1, 3);
    graph.addEdge(3, 5);
    graph.addEdge(3, 8);
    graph.addEdge(5, 4);
    graph.addEdge(5, 8);
    graph.addEdge(4, 9);
    System.out.println("BFS SEARCH");
    graph.bfs(1, 9);
    System.out.println("DFS SEARCH");
    graph.dfs(1, 9);
  }
}
