package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition交替打印
 * @author Liqc
 * @date 2020/4/8 13:51
 */
public class UseConditionAlternatelyPrint {
    public static void main(String[] args) {
        ConditionAlternatelyPrintService service = new ConditionAlternatelyPrintService();
        new Thread(()-> {while(true) service.set();}).start();
        new Thread(()-> {while(true) service.get();}).start();
    }
    /*
    set-----
    get+++++
    set-----
    get+++++
     */
}

@Data
class ConditionAlternatelyPrintService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set() {
        try {
            lock.lock();
            if (hasValue) {
                condition.await();
            }
            System.out.println("set-----");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            if (!hasValue) {
                condition.await();
            }
            System.out.println("get+++++");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
