package com.cn.jmw.simpletree;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleTreeApplicationTests {

    @Test
    void contextLoads() {
    }

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    public static int hammingWeight2(int n) {
        int ret = 0;
        while (n!=0) {
            n &= n-1;
            ret++;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight2(1414124412));
    }

}
