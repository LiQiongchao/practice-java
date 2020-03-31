package com.oio.practice.thread.chapter03.t1;

/**
 * 处于wait()阻塞的方法使用interrupt()方法后会抛出InterruptedException异常
 * @author Liqc
 * @date 2020/3/25 13:24
 */
public class WaitAndInterruptTest {
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            Thread ta = new Thread(() -> {
                new WaitAndInterruptService().testMethod(lock);

            });
            ta.start();
            Thread.sleep(1000);
            ta.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    method start
    java.lang.InterruptedException
        at java.lang.Object.wait(Native Method)
        at java.lang.Object.wait(Object.java:502)
        at com.oio.practice.thread.chapter03.WaitAndInterruptService.testMethod(WaitAndInterruptTest.java:31)
        at com.oio.practice.thread.chapter03.WaitAndInterruptTest.lambda$main$0(WaitAndInterruptTest.java:13)
        at java.lang.Thread.run(Thread.java:745)
    wait状态下执行interrupt了，导致异常！
     */
}

class WaitAndInterruptService {

    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("method start");
                lock.wait();
                System.out.println("method end");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("wait状态下执行interrupt了，导致异常！");
        }

    }

}