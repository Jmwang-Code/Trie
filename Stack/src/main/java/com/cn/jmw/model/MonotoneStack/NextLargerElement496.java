package com.cn.jmw.model.MonotoneStack;

import java.util.*;

/**
 * @author jmw
 * @Description 单调栈类型 专门寻找下一个更大的元素
 *                          或者下一个更小的元素
 *                          单调递增栈
 *                          单调递减栈
 * @date 2022年08月25日 16:48
 * @Version 1.0
 */
public class NextLargerElement496 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4,1,2},new int[]{1,3,4,2})));
    }

    /**
     * @Author jmw
     * @Description stack类似功能 集合，进行出站入栈，并且最后只保留 全增，或者全减。
     * 同时可以记录 每个元素对应的下一个是是否为更大的元素
     * @Date 16:59 2022/8/25
     * @Param [int[], int[]]
     * @return int[]
     **/
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = nums2.length-1; i >= 0; i--) {
            int temp = nums2[i];
            while (!stack.isEmpty() && temp>=stack.peek()){
                stack.pop();
            }
            map.put(temp,stack.isEmpty()?-1:stack.peek());
            stack.push(temp);
        }
        int[] arr = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            arr[i]=map.get(nums1[i]);
        }
        return arr;
    }
}
