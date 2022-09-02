package com.cn.jmw.tree.dfs;

import com.cn.jmw.tree.bean.TreeNode;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月02日 10:55
 * @Version 1.0
 */
public class T786 {
    static int max = 0;
    public static int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return Math.max(0, max - 1);
    }
    public static int dfs(TreeNode root) {
        if (root == null) return 0;
        //ans为了给正常递归提供比较值
        //cur是当前路径长度
        //l长度
        //r长度
        int ans = 1, cur = 1, l = dfs(root.left), r = dfs(root.right);
        //当符合 root.val == root.left.val 时候，
        if (root.left != null && root.val == root.left.val) {
            //ans 等于 左边长度加一   当前长度等于 左长度加当前值
            ans = l + 1; cur += l;
        }
        if (root.right != null && root.val == root.right.val) {
            //ans 等于 ans和右边长度+1的比较值，当前长度等于 右长度加当前值
            ans = Math.max(ans, r + 1);
        }
        //和当前长度选出最长的
        max = Math.max(max, cur);
        return ans;
    }

    public static void main(String[] args) {
        TreeNode t6 = new TreeNode(1,null,null);
        TreeNode t5 = new TreeNode(1,null,null);
        TreeNode t4 = new TreeNode(1,null,null);
        TreeNode t3 = new TreeNode(1,t6,null);
        TreeNode t2 = new TreeNode(1,t4,t5);
        TreeNode t1 = new TreeNode(1,t2,t3);
        System.out.println(longestUnivaluePath(t1));
    }
}
