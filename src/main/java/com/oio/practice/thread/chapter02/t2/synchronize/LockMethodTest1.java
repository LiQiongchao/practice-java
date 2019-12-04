package com.oio.practice.thread.chapter02.t2.synchronize;

import java.time.Instant;

/**
 * synchronized加方法锁后，一个对象实例就是一把锁，不影响非同步的方法调用，只对所有synchronized同步方法的访问排序
 *  如：Obj对象下有 A方法有同步锁，B方法没有，C方法有同步锁
 *  在使用同一个对象锁的情况下，有线程访问A的时候，访问B的线程可以访问，访问C的线程则需要排队。
 * @author Liqc
 * @date 2019/12/4 12:19
 */
public class LockMethodTest1 {
    public static void main(String[] args) {
        ViewObjectB viewObject = new ViewObjectB();
        Thread thread = new Thread(() -> {
            viewObject.printNameA();
        });
        ViewObject viewObject1 = new ViewObject();
        Thread thread1 = new Thread(() -> {
            viewObject.printNameB();
        });
        thread.setName("A");
        thread.start();
        thread1.setName("B");
        thread1.start();
    }
    /*
    printNameB()不加synchronized的结果, 在访问同步方法printNameA()的同时不影响printNameB()的访问
        begin printName threadName=A time=1575434950400
        begin printName threadName=B time=1575434950400
        B end time=1575434952400
        A end time=1575434952400
    printNameB() 加synchronized， 在访问同步方法printNameA()的时候访问printNameB()方法需要排队
        begin printName threadName=A time=1575435184221
        A end time=1575435186221
        begin printName threadName=B time=1575435186221
        B end time=1575435188222
     */
}

class ViewObjectB extends Thread {
    synchronized public void printNameA() {
        try {
            System.out.println("begin printName threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            sleep(2000);
            System.out.println(Thread.currentThread().getName() + " end time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public void printNameB() {
        try {
            System.out.println("begin printName threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            sleep(2000);
            System.out.println(Thread.currentThread().getName() + " end time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
