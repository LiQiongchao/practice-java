package com.oio.practice.thread.chapter04.t1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition.awaitUninterruptibly();
 * 直到sign之后才会释放锁，包括异常中断也不会释放锁。
 * @author Liqc
 * @date 2020/4/16 14:08
 */
public class ConditionAwaitUninterruptibly {

    public static void main(String[] args) throws InterruptedException {
        ConditionAwaitUninterruptiblyTest test = new ConditionAwaitUninterruptiblyTest();
        Thread thread = new Thread(() -> test.awaitUninterrupTest());
        thread.start();
        Thread.sleep(200);
        thread.interrupt();
        Thread.sleep(2000);
        new Thread(() -> test.awaitUninterruptSign()).start();
    }
/*
wait begin
sign begin
sign end
wait end
 */
}

class ConditionAwaitUninterruptiblyTest {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitUninterrupTest() {
        try {
            lock.lock();
            System.out.println("wait begin");
            condition.awaitUninterruptibly();
            System.out.println("wait end");
        } finally {
            lock.unlock();
        }
    }

    public void awaitUninterruptSign() {
        try {
            lock.lock();
            System.out.println("sign begin");
            condition.signal();
            System.out.println("sign end");
        } finally {
            lock.unlock();
        }
    }

}
