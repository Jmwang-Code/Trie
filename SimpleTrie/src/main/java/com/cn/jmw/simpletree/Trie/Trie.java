package com.cn.jmw.simpletree.Trie;

/**
 * @author jmw
 * @describe 简单前缀树原型 26个字母为例
 * @date 2022年08月17日 15:43
 * @Version 1.0
 */

public class Trie {

    private TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    /**
     * @Author jmw
     * @Description 插入树
     * @Date 15:17 2022/8/19
     * @Param [java.lang.String]
     * @return void
     **/
    public void insert(String word) {
        TrieNode node = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            //深入子节点
            node = node.children[index];
        }
        node.isEnd = true;
    }

    /**
     * @Author jmw
     * @Description 查找前缀和查询词汇都是一样的功能
     * @Date 15:16 2022/8/19
     * @Param [java.lang.String]
     * @return boolean
     **/
    public boolean search(String word) {
        TrieNode trie = searchPrefix(word);
        return trie!=null && trie.isEnd;
    }

    /**
     * @Author jmw
     * @Description
     * @Date 15:16 2022/8/19
     * @Param [java.lang.String]
     * @return boolean
     **/
    public boolean startsWith(String prefix) {
        TrieNode trie = searchPrefix(prefix);
        return trie!=null;
    }

    /**
     * @Author jmw
     * @Description 查询功能
     * @Date 15:18 2022/8/19
     * @Param [java.lang.String]
     * @return com.cn.jmw.simpletree.Trie.Trie
     **/
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = this.rootNode;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

}
