package com.cn.jmw.tree.dfs;

import com.cn.jmw.tree.bean.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jmw
 * @Description 二叉树的最大宽度
 * @date 2022年08月27日 18:11
 * @Version 1.0
 */
public class MaxWidthOfBinaryTree662 {

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(1,null,null);
        TreeNode t4 = new TreeNode(1,null,null);
        TreeNode t3 = new TreeNode(1,t4,null);
        TreeNode t2 = new TreeNode(1,null,t5);
        TreeNode t1 = new TreeNode(1,t3,t2);
        System.out.println(widthOfBinaryTree(t1));
    }

    static Map<Integer, Integer> map = new HashMap<>();
    static int ans;
    public static int widthOfBinaryTree(TreeNode root) {
        dfs(root, 1, 0);
        return ans;
    }
    static void dfs(TreeNode root, int u, int depth) {
        if (root == null) return ;
        if (!map.containsKey(depth)) map.put(depth, u);
        ans = Math.max(ans, u - map.get(depth) + 1);
        u = u - map.get(depth) + 1;
        dfs(root.left, u << 1, depth + 1);
        dfs(root.right, u << 1 | 1, depth + 1);
    }

}
