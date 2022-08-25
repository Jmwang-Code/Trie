package com.cn.jmw.tree.dfs;

import com.cn.jmw.tree.bean.TreeNode;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月24日 16:23
 * @Version 1.0
 */
public class DeepestLeavesSum1302 {

    public static int deepestLeavesSum(TreeNode root) {
        int[] i = new int[1];
        dfs(root,0,i);
        int[] sum = new int[1];
        dfsSum(root,0,i[0],sum);
        return sum[0];
    }

    /**
     * @Author jmw
     * @Description 这个找最大层数方法可以抽离出来
     * @Date 16:40 2022/8/24
     * @Param [com.cn.jmw.tree.bean.TreeNode, int, int[]]
     * @return void
     **/
    public static void dfs(TreeNode treeNode,int index,int[] max){
        if (treeNode==null)return;
        max[0] = Math.max(max[0],index);
        if (treeNode.left!=null)dfs(treeNode.left,index+1,max);
        if (treeNode.right!=null)dfs(treeNode.right,index+1,max);
    }

    public static void dfsSum(TreeNode treeNode,int index,int target,int[] sum){
        if (treeNode==null)return;
        if (target==index)sum[0] += treeNode.val;
        if (treeNode.left!=null)dfsSum(treeNode.left,index+1,target,sum);
        if (treeNode.right!=null)dfsSum(treeNode.right,index+1,target,sum);
        return;
    }

    public static void main(String[] args) {
        TreeNode t4 = new TreeNode(4,null,null);
        TreeNode t3 = new TreeNode(3,null,null);
        TreeNode t2 = new TreeNode(2,null,t3);
        TreeNode t1 = new TreeNode(1,t2,t4);
        int i = deepestLeavesSum(t1);
        System.out.println(i);
    }
}
