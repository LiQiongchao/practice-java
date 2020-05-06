package com.oio.practice.interview.base;

/**
 * @author Liqc
 * @date 2020/5/6 13:36
 */
public class SelfIncrTest {
    public static void main(String[] args) {
        int i = 1;
        // i++ 无效，i++是在操作数栈计算的，i的值已经变了。
        i = i++;
        int j = i++;
        int k = j + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
        /**
         * i=4
         * j=1
         * k=10
         */
    }
}
