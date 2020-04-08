package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现wait与notify
 * @author Liqc
 * @date 2020/4/8 13:19
 */
public class UseConditionWaitNotifyTest {
    public static void main(String[] args) {
        try {
            ConditionWaitAndNotifyService service = new ConditionWaitAndNotifyService();
            new Thread(() -> service.await()).start();
            Thread.sleep(2000L);
            service.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    await 时间为：1586323485257
    signal 时间为：1586323487256
     */
}

@Data
class ConditionWaitAndNotifyService {
    private Lock lock = new ReentrantLock();
    // 监视器
    public Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println("await 时间为：" + System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal 时间为：" + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
