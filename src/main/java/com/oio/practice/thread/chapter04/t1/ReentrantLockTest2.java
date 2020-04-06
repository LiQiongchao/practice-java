package com.oio.practice.thread.chapter04.t1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Liqc
 * @date 2020/4/6 21:57
 */
public class ReentrantLockTest2 {
    public static void main(String[] args) {
        ReentrantService2 service2 = new ReentrantService2();
        new Thread(() -> service2.serviceA(), "A").start();
        new Thread(() -> service2.serviceA(), "AA").start();
        new Thread(() -> service2.serviceB(), "B").start();
        new Thread(() -> service2.serviceB(), "BB").start();
    }
    /*
    serviceA begin ThreadName=A time=1586181707870
    serviceA end ThreadName=A time=1586181712872
    serviceA begin ThreadName=AA time=1586181712872
    serviceA end ThreadName=AA time=1586181717873
    serviceB begin ThreadName=B time=1586181717873
    serviceB end ThreadName=B time=1586181722873
    serviceB begin ThreadName=BB time=1586181722873
    serviceB end ThreadName=BB time=1586181727874
     */
}

class ReentrantService2 {
    private Lock lock = new ReentrantLock();

    public void serviceA() {
        try {
            lock.lock();
            System.out.println("serviceA begin ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            Thread.sleep(5000L);
            System.out.println("serviceA end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void serviceB() {
        try {
            lock.lock();
            System.out.println("serviceB begin ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            Thread.sleep(5000L);
            System.out.println("serviceB end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

