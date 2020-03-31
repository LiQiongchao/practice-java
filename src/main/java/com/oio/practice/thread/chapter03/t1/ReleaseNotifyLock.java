package com.oio.practice.thread.chapter03.t1;

/**
 * Notify()方法执行完不会立即释放锁，只有执行完方法所在的同步代码块才会释放锁。
 * @author Liqc
 * @date 2020/3/25 13:00
 */
public class ReleaseNotifyLock {
    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() -> new NotifyService().test(lock)).start();
        new Thread(() -> new NotifyService().synNotifyMethod(lock)).start();
        new Thread(() -> new NotifyService().synNotifyMethod(lock)).start();
    }
    /*
    begin notify() ThreadName=Thread-1 time=1585113530780
    end notify() ThreadName=Thread-1 time=1585113532781
    begin notify() ThreadName=Thread-2 time=1585113532781
    end notify() ThreadName=Thread-2 time=1585113534782
    begin wait() ThreadName=Thread-0
     */
}

class NotifyService {

    public void test(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait() ThreadName=" + Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait() ThreadName=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin notify() ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
                lock.notify();
                Thread.sleep(2000);
                System.out.println("end notify() ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
