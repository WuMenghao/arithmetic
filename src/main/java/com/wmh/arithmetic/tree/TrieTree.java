package com.wmh.arithmetic.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrieTree {

  private transient TrieNode root = new TrieNode('/');
  /**
   * Trie 树， 也叫 “ 字典树 ” 。 顾名思义， 它是一个树形结构。 它是一种专门处理字符串匹配的数据结构， 用来解决在一组字符串集合中快速查找某个字符串的问题。
   *
   * <p>如果要在一组字符串中， 频繁地查询某些字符串， 用 Trie 树会非常高效。 构建 Trie 树的过程， 需要扫描所有的字符串， 时间复杂度是 O(n) （ n
   * 表示所有字符串的长度和） 。 但是 一旦构建成功之后， 后续的查询操作会非常高效
   *
   * <p>每次查询时， 如果要查询的字符串长度是 k ， 那我们只需要比对大约 k 个节点， 就能完成查询操作。 跟原本那组字符串的长度和个数没有任何关系。 所以说， 构建好 Trie 树后，
   * 在其中查找字符串的时间复杂度是 O(k) ， k 表示要查找的字符串的长度。
   */
  public class TrieNode {
    public char data;
    private List<TrieNode> child = new ArrayList<>();
    private boolean isLeaf = false;

    public TrieNode(char data) {
      this.data = data;
    }

    public char getData() {
      return data;
    }

    public List<TrieNode> getChild() {
      return child;
    }

    public boolean isLeaf() {
      return isLeaf;
    }
  }

  /**
   * TrieTree查找
   *
   * @param partern
   * @return
   */
  public boolean find(char[] pattern) {
    return findRecursion(pattern, root, 0);
  }

  private boolean findRecursion(char[] pattern, TrieNode p, int i) {
    if (p.child == null || p.child.size() == 0) return false;
    List<TrieNode> child = p.getChild();
    for (TrieNode trieNode : child) {
      if (trieNode.getData() == pattern[i]) {
        // 直到最后一个模式串字符匹配 返回true
        if (i == pattern.length - 1) return true;
        // 匹配蛋不是最后一个字符 进行下一个字符的匹配
        else return findRecursion(pattern, trieNode, ++i);
      }
    }
    // 存在不匹配返回false
    return false;
  }

  /**
   * 向TrieTree里添加字符串
   *
   * @param text
   */
  public void insert(char[] text) {
    addLayerNode(text, root);
  }

  private boolean addLayerNode(char[] text, TrieNode p) {
    for (int i = 0; i < text.length; i++) {
      char value = text[i];
      List<Character> childChars =
          p.child.stream().map(TrieNode::getData).collect(Collectors.toList());
      // 填充元素
      TrieNode layerNode;
      if (!childChars.contains(value)) {
        layerNode = new TrieNode(value);
        p.child.add(layerNode);
        if (p.isLeaf) p.isLeaf = false;
      } else {
        layerNode = p.child.stream().filter(e -> e.getData() == value).findFirst().orElse(null);
      }
      // 叶子节点
      if (i == text.length - 1) {
        if (layerNode.child == null || layerNode.child.size() == 0) {
          layerNode.isLeaf = true;
        }
        return true;
      }
      // 递归返回
      if (addLayerNode(Arrays.copyOfRange(text, i + 1, text.length), layerNode)) {
        return true;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(root.getData()).append("\n");
    toStringRecursion(root.getChild(), sb);
    return sb.toString();
  }

  private void toStringRecursion(List<TrieNode> level, StringBuilder sb) {
    List<TrieNode> next = new ArrayList<>();
    for (TrieNode trieNode : level) {
      sb.append(trieNode.getData()).append("\t");
      if (trieNode.getChild() != null && trieNode.getChild().size() > 0) {
        next.addAll(trieNode.getChild());
      }
    }
    sb.append("\n");
    if (next.size() == 0) return;
    toStringRecursion(next, sb);
  }

  public static void main(String[] args) {
    TrieTree trieTree = new TrieTree();
    trieTree.insert("你好，你知道我是谁么".toCharArray());
    trieTree.insert("你好，今天天气真好".toCharArray());
    trieTree.insert("你好，你可真漂亮".toCharArray());
    System.out.println(trieTree);

    boolean b = trieTree.find("你好".toCharArray());
    System.out.println(b);
  }
}
