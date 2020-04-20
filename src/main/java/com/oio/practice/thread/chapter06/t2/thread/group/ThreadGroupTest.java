package com.oio.practice.thread.chapter06.t2.thread.group;

/**
 * 线程组的使用
 * @author Liqc
 * @date 2020/4/20 16:18
 */
public class ThreadGroupTest {

    public static void main(String[] args) {
        ThreadGroup parentGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup group = new ThreadGroup(parentGroup,"线程组A");
        Thread thread = new Thread(group, () -> {
            System.out.println("run...");
        }, "threadA");
        thread.start();

        System.out.println("线程组的线程数：" + thread.getThreadGroup().activeCount());
    }

    /*
    线程组的线程数：1
    run...
     */
}
