package com.cn.jmw.simpletree.letter;

/**
 * @author jmw
 * @Description 按照执行时间来说应该将叶子和树放置一体效率会提升
 *              ,面向对象编程思想： 树、叶子
 * @date 2022年08月19日 15:39
 * @Version 1.0
 */
public class TrieNode {

    public TrieNode[] children;
    public boolean isEnd;

    public TrieNode(){
        children = new TrieNode[26];
        isEnd = false;
    }
}
