package com.cn.jmw.simpletree.wildcard;

/**
 * @author jmw
 * @Description 通配符树 允许输入 .和a-z
 * @date 2022年08月19日 18:01
 * @Version 1.0
 */
public class WordDictionaryA {

    class Node {
        Node[] next = new Node[26];
        String str;
    }

    private Node root;
    private int maxLen;

    public WordDictionaryA() {
        this.root = new Node();
        this.maxLen = 0;
    }

    public void addWord(String word) {
        maxLen = Math.max(maxLen, word.length());
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new Node();
            }
            cur = cur.next[index];
        }
        cur.str = word;
    }

    public boolean search(String word) {
        if (word.length() > maxLen) return false;
        return dfs(root, word, 0);
    }

    private boolean dfs(Node root, String word, int index) {
        if (index == word.length()) return root.str != null;
        char ch = word.charAt(index);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.next[i] != null) {
                    if (dfs(root.next[i], word, index + 1)) return true;
                }
            }
            return false;
        } else {
            if (root.next[ch - 'a'] == null) return false;
            return dfs(root.next[ch - 'a'], word, index + 1);
        }
    }

}
