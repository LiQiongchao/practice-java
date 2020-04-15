package com.oio.practice.thread.chapter04.t1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock方法测试
 * hasQueuedThread(Thread thread): 查询指定的线程是否是在等待获取锁。
 * hasQueuedThreads(): 查询当前是否是有等待该的锁的线程。
 *
 * @author Liqc
 * @date 2020/4/15 13:05
 */
public class LockMethodTest {
    public static void main(String[] args) throws InterruptedException {
        LockMethodService service = new LockMethodService();
        Thread thread = new Thread(() -> service.waitMethod());
        thread.start();
        Thread.sleep(500);
        Thread thread1 = new Thread(() -> service.waitMethod());
        thread1.start();
        Thread.sleep(200);
        System.out.println(service.lock.hasQueuedThread(thread));
        System.out.println(service.lock.hasQueuedThread(thread1));
        System.out.println(service.lock.hasQueuedThreads());
    }
    /*
    false
    true
    true
     */

}

class LockMethodService {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
