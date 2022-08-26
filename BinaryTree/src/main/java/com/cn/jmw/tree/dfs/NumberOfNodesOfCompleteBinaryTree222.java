package com.cn.jmw.tree.dfs;

import com.cn.jmw.tree.bean.TreeNode;

/**
 * @author jmw
 * @Description 完全二叉树的节点数
 * 首先可以用全节点遍历的方式 比如DFS BFS但是时间复杂度O（n）属于是拉满了
 *
 * 没有意义了
 * 这个利用了 完全二叉树的特性 如图
 * @date 2022年08月26日 16:15
 * @Version 1.0
 */
public class NumberOfNodesOfCompleteBinaryTree222 {

    public static void main(String[] args) {
        TreeNode t6 = new TreeNode(1,null,null);
        TreeNode t5 = new TreeNode(1,null,null);
        TreeNode t4 = new TreeNode(1,null,null);
        TreeNode t3 = new TreeNode(1,t6,null);
        TreeNode t2 = new TreeNode(1,t4,t5);
        TreeNode t1 = new TreeNode(1,t2,t3);
        System.out.println(countNodes(t1));
    }

    public static int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }
        int left = numberOfLayers(root.left);
        int right = numberOfLayers(root.right);
        if (left==right){
            return countNodes(root.right) + (1<<left);
        }else {
            return countNodes(root.left) + (1<<right);
        }
    }

    /**
     * @Author jmw
     * @Description 层数判断只适合完全二叉树
     * @Date 16:18 2022/8/26
     * @Param []
     * @return int
     **/
    public static int numberOfLayers(TreeNode root){
        int len = 0;
        while (root!=null){
            len++;
            root = root.left;
        }
        return len;
    }
}
