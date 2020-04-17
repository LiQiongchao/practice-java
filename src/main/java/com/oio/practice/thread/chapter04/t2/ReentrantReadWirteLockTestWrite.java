package com.oio.practice.thread.chapter04.t2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试写写操作
 * @author Liqc
 * @date 2020/4/17 13:56
 */
public class ReentrantReadWirteLockTestWrite {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Runnable readRunnable = () -> {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获取锁，time: " + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    };
    static Runnable writeRunnable = () -> {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获取锁，time: " + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    };

    public static void main(String[] args) {
        new Thread(readRunnable).start();
        new Thread(writeRunnable).start();
    }
    /*
    Thread-0 获取锁，time: 1587103570476
    Thread-1 获取锁，time: 1587103571476
     */
}
