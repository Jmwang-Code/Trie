package com.cn.jmw.tree.bfs;

import com.cn.jmw.tree.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author jmw
 * @Description 二叉树的最大宽度
 * @date 2022年08月27日 18:11
 * @Version 1.0
 */
public class MaxWidthOfBinaryTree662 {

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(1, null, null);
        TreeNode t4 = new TreeNode(1, null, null);
        TreeNode t3 = new TreeNode(1, t4, null);
        TreeNode t2 = new TreeNode(1, null, t5);
        TreeNode t1 = new TreeNode(1, t3, t2);
        System.out.println(widthOfBinaryTree(t1));
    }

    public static int widthOfBinaryTree(TreeNode root) {
        int result = 0;
        Deque<TreeNode> deque = new ArrayDeque();
        deque.addLast(new TreeNode(1, root.left, root.right));
        while (!deque.isEmpty()) {
            int count = deque.size(), startIndex = -1, endIndex = -1;
            for (int i = 0; i < count; i++) {
                TreeNode node = deque.pop();
                endIndex = node.val;
                if (startIndex == -1) startIndex = node.val;
                if (node.left != null) deque.addLast(new TreeNode(node.val * 2, node.left.left, node.left.right));
                if (node.right != null) deque.addLast(new TreeNode(node.val * 2 + 1, node.right.left, node.right.right));
            }
            result = Math.max(result, endIndex - startIndex + 1);
        }
        return result;

    }
}
