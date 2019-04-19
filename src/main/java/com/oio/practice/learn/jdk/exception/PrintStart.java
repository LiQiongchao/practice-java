package com.oio.practice.learn.jdk.exception;

/**
 * @Author: LiQiongchao
 * @Date: 2019/3/31 15:46
 */
public class PrintStart {

    public static void main(String[] args) {
        printStart(7);
    }

    private static void printStart(int i) {
        for (int j = 0; j < i/2+1; j++) {
            for (int k = i/2; k > 0; k--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void findStr() {
        String source = "source", target = "target";
        char[] sources = source.toCharArray();
        char[] targets = target.toCharArray();
        int targetIndex = 0,index = 0;
        for (int i = 0; i <sources.length; i++) {
            if (sources[i] == targets[targetIndex]) {
                index = i;
                for (int j = 1; j < targets.length; j++) {

                }
            }

        }
    }

}
