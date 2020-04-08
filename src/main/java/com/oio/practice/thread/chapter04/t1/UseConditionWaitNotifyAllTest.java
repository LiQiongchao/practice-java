package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用多个Condition的实例实现只唤醒部分线程
 * @author Liqc
 * @date 2020/4/8 13:34
 */
public class UseConditionWaitNotifyAllTest {
    public static void main(String[] args) throws InterruptedException {
        ConditionWaitAndNotifyAllService service = new ConditionWaitAndNotifyAllService();
        new Thread(() -> service.awaitA(), "A").start();
        new Thread(() -> service.awaitB(), "B").start();
        Thread.sleep(2000L);
        service.signalA();
//        service.signalB();
    }
    /*
    begin awaitA time=1586324631886 ThreadName=A
    begin awaitB time=1586324631887 ThreadName=B
         signalA time=1586324633887 ThreadName=main
      end awaitA time=1586324633887 ThreadName=A
     */
}

@Data
class ConditionWaitAndNotifyAllService {
    // lock锁
    private Lock lock = new ReentrantLock();
    // 监视器
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA time=" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("  end awaitA time=" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            lock.lock();
            System.out.println("begin awaitB time=" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("  end awaitB time=" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalA() {
        try {
            lock.lock();
            System.out.println("     signalA time=" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalB() {
        try {
            lock.lock();
            System.out.println("    signalB time=" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
