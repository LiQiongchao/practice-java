package com.oio.practice.thread.chapter06.t2.thread.group;

/**
 * 根线程组的system
 * @author Liqc
 * @date 2020/4/20 16:29
 */
public class RootThreadGroup {

    public static void main(String[] args) {

        System.out.println("main group: " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("main parent group: " + Thread.currentThread().getThreadGroup().getParent().getName());
        System.out.println("main parent parent group: " + Thread.currentThread().getThreadGroup().getParent().getParent().getName());
    }
/*
main group: main
main parent group: system
Exception in thread "main" java.lang.NullPointerException
	at com.oio.practice.thread.chapter06.t2.thread.group.RootThreadGroup.main(RootThreadGroup.java:14)
 */
}
