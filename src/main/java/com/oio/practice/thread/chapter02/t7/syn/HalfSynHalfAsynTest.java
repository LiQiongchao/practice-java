package com.oio.practice.thread.chapter02.t7.syn;

/**
 * 不在synchronized块中就是异步执行，在synchronized块中就是同步执行。
 * @author Liqc
 * @date 2019/12/16 12:54
 */
public class HalfSynHalfAsynTest {
    public static void main(String[] args) {
        SynTask task = new SynTask();
        new Thread(() -> task.doLongTimeTask()).start();
        new Thread(() -> task.doLongTimeTask()).start();
    }
}

class SynTask {
    public void doLongTimeTask() {
        for (int i = 0; i < 100; i++) {
            System.out.println("no synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i+1));
        }
        System.out.println("");
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i+1));
            }
        }
    }
    /*
    synchronized 为有序打印，no synchronized为无序打印
     */
}
