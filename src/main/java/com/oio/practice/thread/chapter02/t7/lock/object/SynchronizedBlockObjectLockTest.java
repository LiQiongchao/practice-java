package com.oio.practice.thread.chapter02.t7.lock.object;

/**
 * 当synchronized的锁为this时，也就是当前对象时，会对对象内的所有的synchronized代码块加锁
 * @author Liqc
 * @date 2019/12/16 13:04
 */
public class SynchronizedBlockObjectLockTest {
    public static void main(String[] args) {
        ObjectService service = new ObjectService();
        new Thread(() -> service.serviceMethodA()).start();
        new Thread(() -> service.serviceMethodB()).start();
    }
    /*
    A begin time=1576473028628
    A end time=1576473030628
    B begin time=1576473030628
    B end time=1576473030628
     */
}
class ObjectService {
    public void serviceMethodA() {
        try {
            synchronized (this) {
                System.out.println("A begin time=" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A end time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void serviceMethodB() {
        synchronized (this) {
            System.out.println("B begin time=" + System.currentTimeMillis());
            System.out.println("B end time=" + System.currentTimeMillis());
        }
    }
}
