package com.oio.practice.thread.chapter01.t14.stop.in.sleeping;

/**
 * 测试在沉睡之前停止线程，看是否还能停止线程
 * @author Liqc
 * @date 2019/11/25 13:01
 */
public class InterruptBeforeSleepTest {

    public static void main(String[] args) {
        StopBeforeSleeping stopBeforeSleeping = new StopBeforeSleeping();
        stopBeforeSleeping.start();
        stopBeforeSleeping.interrupt();
        System.out.println("main end!");
    }
    /*
    i = 9999
    i = 10000
    run begin
    线程先interrupt再进入sleep，进行catch!
    java.lang.InterruptedException: sleep interrupted
        at java.lang.Thread.sleep(Native Method)
        at com.oio.practice.thread.chapter01.t14.stop.in.sleeping.StopBeforeSleeping.run(InterruptBeforeSleepTest.java:29)
    */
}

class StopBeforeSleeping extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println("i = " + (i + 1));
            }
            System.out.println("run begin");
            Thread.sleep(20000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("线程先interrupt再进入sleep，进行catch! ");
            e.printStackTrace();
        }
    }
}
