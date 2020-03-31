package com.oio.practice.thread.chapter03.t1;

/**
 * notify()一次只能唤醒一个线程。想唤醒多个线程时，需要多次调用。
 * notifyAll()一次唤醒所有线程。
 * @author Liqc
 * @date 2020/3/30 13:34
 */
public class NotifyThreadTest {
    public static void main(String[] args) {
        try {
            String lock = "lock";
            new Thread(() -> new NotifyThreadService().waitMethod(lock)).start();
            new Thread(() -> new NotifyThreadService().waitMethod(lock)).start();
            new Thread(() -> new NotifyThreadService().waitMethod(lock)).start();
            Thread.sleep(1000);
            new Thread(() -> {
                synchronized (lock) {
                    // 调用几次会唤醒几个线程
                    lock.notify();
                    lock.notify();
                    // 唤醒所有等待的线程。
//                    lock.notifyAll();
                }
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * begin wait() Threadname:Thread-1
     * begin wait() Threadname:Thread-0
     * begin wait() Threadname:Thread-2
     * end wait() Threadname:Thread-1
     * end wait() Threadname:Thread-0
     */
}

class NotifyThreadService{
    public void waitMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait() Threadname:" + Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait() Threadname:" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
