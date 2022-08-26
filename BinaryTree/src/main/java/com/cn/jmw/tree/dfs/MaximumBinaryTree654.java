package com.cn.jmw.tree.dfs;

import com.cn.jmw.tree.bean.TreeNode;

/**
 * @author jmw
 * @Description 分而治之的思想
 * 每次都找到最大值当做根节点，
 * 然后进入下一层递归 以最大值为界限 [left，mid-1][mid+1,right]
 * 进行递归
 * 终止条件未左节点大于右节点
 * @date 2022年08月25日 16:31
 * @Version 1.0
 */
public class MaximumBinaryTree654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums,0,nums.length-1);
    }

    public TreeNode construct(int[] nums, int left, int right) {
        if(left>right)return null;
        int best = left;
        for(int i=left+1;i<=right;i++){
            if (nums[i] > nums[best]) {
                best = i;
            }
        }
        TreeNode node = new TreeNode(nums[best]);
        node.left = construct(nums, left, best - 1);
        node.right = construct(nums, best + 1, right);
        return node;
    }

    /**
     * @Author jmw
     * @Description  单调栈
     * @Date 16:35 2022/8/25
     * @Param
     * @return
     **/

    /**
     * @Author jmw
     * @Description  线段树
     * @Date 16:35 2022/8/25
     * @Param
     * @return
     **/
}
