package com.cn.jmw.model;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月31日 13:13
 * @Version 1.0
 */
public class Stack {

    /**
     * @Author jmw
     * @Description 946. 验证栈序列 判断栈 是否出入栈是否相同，是否符合栈的行为逻辑
     * @Date 13:14 2022/8/31
     * @Param [int[], int[]]
     * @return boolean
     **/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int n = pushed.length;
        for(int i=0,j=0;i<n;i++){
            stack.push(pushed[i]);
            while(!stack.isEmpty() && stack.peek()==popped[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
