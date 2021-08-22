package com.oio.practice.interview.atguigu.second.ch03.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 自定义自旋锁，实现类似ReentrantLock的功能。
 * @Author: LiQiongchao
 * @Date: 2020/5/28 22:11
 */
public class SpinLockDemo {

    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unLock();
            }
        }, "A").start();
        new Thread(() -> {
            lock.lock();
            try {
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unLock();
            }
        }, "B").start();
    }
    /**
     * A  time=1590676424447  come in
     * B  time=1590676424448  come in
     * A  time=1590676426448  end
     * B  time=1590676426448  end
     */

}

class MyLock {

    AtomicReference<Thread> reference = new AtomicReference<Thread>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t time="+System.currentTimeMillis()+"\t come in");
        // 不成功一直尝试，直到成功为止。
        while (!reference.compareAndSet(null, thread)) {
        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        // 不成功一直尝试，直到成功为止。
        reference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t time="+System.currentTimeMillis()+"\t end");
    }

}
 
