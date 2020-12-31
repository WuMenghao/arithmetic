package com.wmh.arithmetic.tree;

/*
 * 平衡二叉树 插入，查找，toString */
public class BalanceTree {

  /*
   *根节点
   */
  private Node root;

  public BalanceTree(int[] data) {
    super();
    for (int one : data) {
      this.insert(one);
    }
  }

  /**
   * 插入
   *
   * @param data 新数据
   */
  public void insert(int data) {
    // 根节点为Null
    if (root == null) {
      root = new Node(data);
      return;
    }

    // 根节点不为Null 从上至下比较确定插入位置插入
    Node p = root;
    while (p != null) {
      if (data > p.data) {
        if (p.right == null) {
          p.right = new Node(data);
          return;
        }
        p = p.right;
      } else {
        if (p.left == null) {
          p.left = new Node(data);
          return;
        }
        p = p.left;
      }
    }
  }

  /**
   * @param data 查找的数据
   * @return 返回查找数据所在的节点
   */
  public Node find(int data) {
    if (root == null) {
      return null;
    }

    Node p = root;
    while (p != null) {
      if (data == p.data) {
        return p;
      } else if (data > p.data) {
        p = p.right;
      } else {
        p = p.left;
      }
    }
    return null;
  }

  /**
   * toString 中序遍历
   *
   * @return
   */
  public String toString() {
    StringBuilder sb = new StringBuilder(super.toString()).append("[");
    appendNode(sb, root);
    return sb.append("]").toString();
  }

  private void appendNode(StringBuilder sb, Node node) {
    if (node == null) return;
    appendNode(sb, node.left);
    sb.append("\t").append(node.data);
    appendNode(sb, node.right);
  }

  /*
   * 节点
   * */
  class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {
    int[] arr = {3, 1, 4, 5, 2, 6, 0, 9, 11, 33, 22, 58, 98};
    BalanceTree balanceTree = new BalanceTree(arr);
    System.out.println(balanceTree);
  }
}
