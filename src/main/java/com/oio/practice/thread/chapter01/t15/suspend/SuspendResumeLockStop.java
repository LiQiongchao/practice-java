package com.oio.practice.thread.chapter01.t15.suspend;

/**
 * 测试使用suspend()时独占资源的现象
 * @author Liqc
 * @date 2019/11/27 18:30
 */
public class SuspendResumeLockStop {
    public static void main(String[] args) {
        try {
            SynchronizedObject2 object = new SynchronizedObject2();
            Thread thread = new Thread(()->{
                object.printString();
            });
            thread.setName("a");
            thread.start();
            Thread.sleep(2000);

            new Thread(()->{
                System.out.println("无法进入printString()方法，只打印了一个'synchronized start'，因为方法被永远的暂停了。");
                object.printString();
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    synchronized start
    a 线程永远suspend
    无法进入printString()方法，只打印了一个'synchronized start'，因为方法被永远的暂停了。
     */
}

class SynchronizedObject2 {
    synchronized public void printString() {
        System.out.println("synchronized start");
        if ("a".equals(Thread.currentThread().getName())) {
            System.out.println("a 线程永远suspend");
            Thread.currentThread().suspend();
        }
        System.out.println("synchronized end");
    }
}
