package com.oio.practice.thread.chapter04.t1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Lock的相关方法
 * getHoldCount(): 查询当前线程保持此锁定的个数，也就是调用lock()方法的次数。
 * @author Liqc
 * @date 2020/4/13 13:38
 */
public class LockMethodTest1 {

    public static void main(String[] args) {
        LockMethodTestService service = new LockMethodTestService();
        service.serviceMethod1();
    }
    /*
    serviceMethod1 getHoldCount=1
    serviceMethod2 getHoldCount=2
     */


}

class LockMethodTestService {
    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod1() {
        try {
            lock.lock();
            System.out.println("serviceMethod1 getHoldCount=" + lock.getHoldCount());
            serviceMethod2();
        } finally {
            lock.unlock();
        }
    }

    private void serviceMethod2() {
        try {
            lock.lock();
            System.out.println("serviceMethod2 getHoldCount=" + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }
}
