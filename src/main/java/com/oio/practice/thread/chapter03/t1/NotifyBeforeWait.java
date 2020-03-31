package com.oio.practice.thread.chapter03.t1;

/**
 * 如果Notify()在wait()方法之前执行会导致wait()一直在等待，所以程序要保证wait()要有线程Notify()
 * @author Liqc
 * @date 2020/3/31 12:38
 */
public class NotifyBeforeWait {
    private Boolean isFirstB = false;
    private String lock = "lock";

    Thread thread1 = new Thread(() -> {
        try {
            synchronized (lock) {
                if (isFirstB == false) {
                    System.out.println("wait begin");
                    lock.wait();
                    System.out.println("wait end");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    Thread thread2 = new Thread(() -> {
        synchronized (lock) {
            System.out.println("notify begin");
            lock.notify();
            System.out.println("notify end");
            isFirstB = true;
        }
    });

    public static void main(String[] args) throws InterruptedException {
        NotifyBeforeWait notifyBeforeWait = new NotifyBeforeWait();
        notifyBeforeWait.thread1.start();
        Thread.sleep(100);
        notifyBeforeWait.thread2.start();
    }
}
