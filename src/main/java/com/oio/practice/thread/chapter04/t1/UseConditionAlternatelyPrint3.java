package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现3个线程交替打印
 * 不会死锁的原因：因为如果nextPrintId=2, 即使B.signAll已经执行完了，也会B也会继续执行。
 * @author Liqc
 * @date 2020/4/8 13:51
 */
public class UseConditionAlternatelyPrint3 {
    public static void main(String[] args) {
        ConditionAlternatelyPrintService3 service = new ConditionAlternatelyPrintService3();
        new Thread(()-> {
            for (int i = 0; true; i++) {
                System.out.println(i);
                service.printA();
            }
        }).start();
        new Thread(()-> {
            while (true) service.printB();
        }).start();
        new Thread(()-> {while(true) service.printC();}).start();
    }

}

@Data
class ConditionAlternatelyPrintService3 {
    private Lock lock = new ReentrantLock();
    private Condition conditionB = lock.newCondition();
    private Condition conditionA = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int nextPrintId = 1;

    public void printA() {
        try {
            lock.lock();
            while (nextPrintId != 1) {
                System.out.println("a wait");
                conditionA.await();
            }
            System.out.println("A------");
            nextPrintId = 2;
            conditionB.signalAll();
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
            while (nextPrintId != 2) {
                System.out.println("b wait");
                conditionB.await();
            }
            System.out.println("---B---");
            nextPrintId = 3;
            conditionC.signalAll();
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
            while (nextPrintId != 3) {
                System.out.println("c wait");
                conditionC.await();
            }
            System.out.println("------C");
            nextPrintId = 1;
            conditionA.signalAll();
            System.out.println("a signal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
