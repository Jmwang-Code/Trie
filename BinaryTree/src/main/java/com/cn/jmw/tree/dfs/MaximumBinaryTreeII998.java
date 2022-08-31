package com.cn.jmw.tree.dfs;

import com.cn.jmw.tree.bean.TreeNode;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月31日 13:27
 * @Version 1.0
 */
public class MaximumBinaryTreeII998 {

    /**
     * @Author jmw
     * @Description 最大二叉树
     * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。（父节点大于子节点的情况）
     * 向二叉树中，插入一个元素 使得插入的元素让 二叉树更好的排序成最大树
     * @Date 13:27 2022/8/31
     * @Param [com.cn.jmw.tree.bean.TreeNode, int]
     * @return com.cn.jmw.tree.bean.TreeNode
     **/
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if(root==null)return new TreeNode(val);
        if(val>root.val){
            return new TreeNode(val,root,null);
        }
        return new TreeNode(root.val,root.left,insertIntoMaxTree(root.right,val));
    }
}
