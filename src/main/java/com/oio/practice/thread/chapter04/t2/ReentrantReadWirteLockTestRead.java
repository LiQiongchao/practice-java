package com.oio.practice.thread.chapter04.t2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试读操作
 * @author Liqc
 * @date 2020/4/17 13:56
 */
public class ReentrantReadWirteLockTestRead {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Runnable runnable = () -> {
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

    public static void main(String[] args) {
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
    /*
    Thread-0 获取锁，time: 1587103182147
    Thread-1 获取锁，time: 1587103182147
     */
}
