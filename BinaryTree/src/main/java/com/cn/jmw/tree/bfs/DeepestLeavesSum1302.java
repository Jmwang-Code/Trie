package com.cn.jmw.tree.bfs;

import com.cn.jmw.tree.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月24日 16:23
 * @Version 1.0
 */
public class DeepestLeavesSum1302 {

    /**
     * @Author jmw
     * @Description bfs广度树做法
     * @Date 16:42 2022/8/24
     * @Param [com.cn.jmw.tree.bean.TreeNode]
     * @return int
     **/
    public static int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> treeNodes = new ArrayDeque<>();
        treeNodes.offer(root);
        int max = 0;
        while(!treeNodes.isEmpty()){
            int length = treeNodes.size();
            int ans = 0;
            while (length>0){
                TreeNode poll = treeNodes.poll();
                ans+=poll.val;
                if (poll.left!=null)treeNodes.offer(poll.left);
                if (poll.right!=null)treeNodes.offer(poll.right);
                length--;
            }
            max =ans;
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(34,null,null);
        TreeNode t4 = new TreeNode(6,null,t5);
        TreeNode t3 = new TreeNode(98,null,null);
        TreeNode t2 = new TreeNode(54,t3,t4);
        TreeNode t1 = new TreeNode(50,null,t2);
        System.out.println(deepestLeavesSum(t1));
    }


}
