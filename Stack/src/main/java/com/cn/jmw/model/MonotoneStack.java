package com.cn.jmw.model;

import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年09月01日 13:04
 * @Version 1.0
 */
public class MonotoneStack {

    /**
     * @Author jmw
     * @Description 503. 下一个更大元素 II
     * 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的 下一个更大元素 。
     * 数字 x的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     * @Date 17:50 2022/9/1
     * @Param [int[]]
     * @return int[]
     **/
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        //将所有节点变为-1
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        //最多要循环 n*2-1次
        for (int i = 0; i < n * 2 - 1; i++) {
            //首先判断当前队列中数据是否小于nums
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ans[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ans;
    }

    /**
     * @Author jmw
     * @Description 1475.
     * 给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
     * 商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。
     * 请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
     * @Date 13:04 2022/9/1
     * @Param [int[]]
     * @return int[]
     **/
    public static int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = n - 1; i >= 0; i--) {
                                        //具体出栈条件在这里
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            //不断入栈 保证 栈内资源是符合出栈逻辑 比如 5，4，3，2，1单调递减
            stack.push(prices[i]);
        }
        return ans;
    }

}
