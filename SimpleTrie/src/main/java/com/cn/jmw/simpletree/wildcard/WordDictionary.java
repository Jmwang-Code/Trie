package com.cn.jmw.simpletree.wildcard;


/**
 * @author jmw
 * @Description 通配符树 允许输入 .和a-z
 * @date 2022年08月19日 17:57
 * @Version 1.0
 */
public class WordDictionary {
    private Trie root;

    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    /**
     * @return boolean
     * @Author jmw
     * @Description 深度优先搜索 主要分为通配符和非通配符（字母哥）,成功条件标志位isEnd true并且长度符合字符串长度。
     * @Date 18:19 2022/8/19
     * @Param [java.lang.String, int, com.cn.jmw.simpletree.wildcard.Trie]
     **/
    private boolean dfs(String word, int index, Trie node) {
        if (index == word.length()) {
            return node.isEnd;
        }
        char ch = word.charAt(index);
        int childIndex = ch - 'a';
        if (childIndex >= 0) {
            Trie child = node.children[childIndex];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else {
            //深度优先搜索树做通配符 查询
            for (int i = 0; i < 26; i++) {
                Trie child = node.children[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Trie {
    public Trie[] children;
    public boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

}
