package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock实现多生产多消费
 * 如果使用的是condition.signal()有可能每次通知的是自己方的线程，然后导致上卡死
 * 使用condition.signalAll()，就不会有问题，但是会有性能问题。
 * @author Liqc
 * @date 2020/4/10 13:21
 */
public class MultiProviderAndMultiConsumerTest {
    public static void main(String[] args) {
        MultiProviderAndMultiConsumerService service = new MultiProviderAndMultiConsumerService();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    service.provider();
                }
            }).start();
            new Thread(() -> {
                while (true) {
                    service.consumer();
                }}).start();
        }
    }
    /**
     * 消费了++++
     * 生产了----
     * 消费了++++
     * 生产了----
     * 消费了++++
     * 生产了----
     */
}

@Data
class MultiProviderAndMultiConsumerService {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    boolean hasVal = false;

    public void provider() {
        try {
            lock.lock();
            while (hasVal) {
//                System.out.println("provider wait");
                condition.await();
            }
            hasVal = true;
            System.out.println("生产了----");
//            condition.signal();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        try {
            lock.lock();
            while (!hasVal) {
//                System.out.println("consumer wait");
                condition.await();
            }
            System.out.println("消费了++++");
            hasVal = false;
//            condition.signal();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
