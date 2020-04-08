package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现3个线程交替打印
 * 注: 会有死锁现象，大家都进入wait了
 * @author Liqc
 * @date 2020/4/8 13:51
 */
public class UseConditionAlternatelyPrint2 {
    public static void main(String[] args) {
        ConditionAlternatelyPrintService2 service = new ConditionAlternatelyPrintService2();
        new Thread(()-> {
            while (true) service.printB();
        }).start();
        new Thread(()-> {while(true) service.printC();}).start();
        new Thread(()-> {
            for (int i = 0; i < 10000; i++) {
                System.out.println(i);
                service.printA();
            }
        }).start();
    }

}

@Data
class ConditionAlternatelyPrintService2 {
    private Lock lock = new ReentrantLock();
    private Condition conditionB = lock.newCondition();
    private Condition conditionA = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private boolean isFirst = true;

    public void printA() {
        try {
            lock.lock();
            if (!isFirst) {
                System.out.println("a wait");
                conditionC.await();
            } else {
                isFirst = false;
            }
            System.out.println("A------");
            conditionA.signal();
            System.out.println("b signal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        try {
            lock.lock();
            System.out.println("b wait");
            conditionA.await();
            System.out.println("---B---");
            conditionB.signal();
            System.out.println("c signal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        try {
            lock.lock();
            System.out.println("c wait");
            conditionB.await();
            System.out.println("------C");
            conditionC.signal();
            System.out.println("a signal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
