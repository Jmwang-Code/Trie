package com.cn.jmw.tree.bfs;

import com.cn.jmw.tree.bean.TreeNode;
import lombok.val;

import java.util.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月22日 13:44
 * @Version 1.
 *
 * 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，
 * 用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
 *
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2height+1 - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，
 * 右子节点放置在 res[r+1][c+2height-r-1] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 */
public class PrintTree665 {
    public static void main(String[] args) {
        TreeNode t4 = new TreeNode(4,null,null);
        TreeNode t3 = new TreeNode(3,null,null);
        TreeNode t2 = new TreeNode(2,null,t3);
        TreeNode t1 = new TreeNode(1,t2,t4);
        System.out.println(printTree(t1));
    }

    public static List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<List<String>>();
        int height = calDepth(root);
        int m = height + 1;
        int n = (1 << (height + 1)) - 1;
        for(int i = 0;i<m;i++){
            List<String> row = new ArrayList<String>();
            for (int j = 0; j < n; j++) {
                    row.add("");
            }
            res.add(row);
        }

        for (List<String> st:
             res) {
            for (String S:
            st) {
                System.out.print(S+" ");
            }
            System.out.println();
        }
        dfs(res, root, 0, (n - 1) / 2, height);
        return res;
    }

    /**
     * @Author jmw
     * @Description 深度有限找层数
     * @Date 14:50 2022/8/22
     * @Param [com.cn.jmw.tree.bean.TreeNode]
     * @return int
     **/
    public static int calDepth(TreeNode root) {
        int h = 0;
        if (root.left != null) {
            h = Math.max(h, calDepth(root.left) + 1);
        }
        if (root.right != null) {
            h = Math.max(h, calDepth(root.right) + 1);
        }
        return h;
    }

    /**
     * @Author jmw
     * @Description 深度优先搜索
     * @Date 14:50 2022/8/22
     * @Param [java.util.List<java.util.List<java.lang.String>>, com.cn.jmw.tree.bean.TreeNode, int, int, int]
     * @return void
     **/
    public static void dfs(List<List<String>> res, TreeNode root, int r, int c, int height) {
        res.get(r).set(c, Integer.toString(root.val));
        if (root.left != null) {
            dfs(res, root.left, r + 1, c - (1 << (height - r - 1)), height);
        }
        if (root.right != null) {
            dfs(res, root.right, r + 1, c + (1 << (height - r - 1)), height);
        }
    }
}
