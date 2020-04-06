package com.oio.practice.thread.chapter04.t1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition来实现wait效果
 * @author Liqc
 * @date 2020/4/6 22:59
 */
public class UseConditionOnReentrantTest {

    public static void main(String[] args) {
        try {
            ConditionService service = new ConditionService();
            Thread thread = new Thread(() -> service.waitMethod());
            thread.start();
            Thread.sleep(1000);
            System.out.println("sub thread status: " + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    A
    sub thread status: WAITING

     */

}

class ConditionService {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            System.out.println("A");
            condition.await();
            System.out.println("B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("锁释放了");
        }
    }

}

