package com.oio.practice.thread.chapter04.t1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock测试
 * @author Liqc
 * @date 2020/4/6 21:49
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantService service = new ReentrantService();
        new Thread(()->service.testMethod()).start();
        new Thread(()->service.testMethod()).start();
        new Thread(()->service.testMethod()).start();
        new Thread(()->service.testMethod()).start();
        new Thread(()->service.testMethod()).start();
    }
    /*
    ThreadName=Thread-0 - 1
    ThreadName=Thread-0 - 2
    ThreadName=Thread-0 - 3
    ThreadName=Thread-0 - 4
    ThreadName=Thread-0 - 5
    ThreadName=Thread-1 - 1
    ThreadName=Thread-1 - 2
     */

}

class ReentrantService {
    private Lock lock = new ReentrantLock();

    public void testMethod() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " - " + (i + 1));
        }
        lock.unlock();
    }
}

