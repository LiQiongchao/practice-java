package com.oio.practice.jdk.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 使用AbstractQueuedSynchronizer实现的锁
 * @Author: LiQiongchao
 * @Date: 2020/4/26 23:15
 */
public class MyLockDemo implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否算是占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当状态为0时获得锁
        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            // 释放锁，把状态设置为0
            assert releases == 1;
            if (getState() == 0) {
                setExclusiveOwnerThread(Thread.currentThread());
                setState(0);
                return true;
            }
            return false;
        }
            // 返回一个Condition, 每个condition都包含了一个condition队列
            Condition newCondition() {
                return new ConditionObject();
            }
    }

    // 利用内部变量声明一个AbstractQueuedSynchronizer子类，一般都在内部类使用
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
}
