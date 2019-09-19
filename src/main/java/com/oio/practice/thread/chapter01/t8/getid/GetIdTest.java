package com.oio.practice.thread.chapter01.t8.getid;

/**
 * @author Liqc
 * @date 2019/9/18 13:11
 */
public class GetIdTest {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " - " + thread.getId());
        // main - 1
    }
}
