package com.oio.practice.thread.chapter02.t8.lockin.two.classity;

import lombok.Data;

/**
 * 当两个类中的同步锁为同一对象时，两对象的方法会形成同步，会相互阻塞。
 * speedPrintString()使用synchronized(this),效果一样
 * @author Liqc
 * @date 2019/12/30 12:47
 */
public class LockSynTwoObjectTest {
    public static void main(String[] args) {
        MyObject object = new MyObject();
        CrossClassService service = new CrossClassService();
        Thread thread = new Thread(() -> {
            service.testMethod1(object);
        });
        Thread thread1 = new Thread(() -> object.speedPrintString());
        thread.setName("A");
        thread1.setName("B");
        thread.start();
        thread1.start();
    }
    /*
    testMethod1 get lock time=1577681740908 run ThreadName=A
    testMethod1 release lock time=1577681742909 run ThreadName=A
    speedPrintString get lock time=1577681742909 run ThreadName=B
    ---------------------------
    speedPrintString release lock time=1577681742909 run ThreadName=B
     */
}
class MyObject {
    synchronized public void speedPrintString() {
        System.out.println("speedPrintString get lock time=" + System.currentTimeMillis() + " run ThreadName=" + Thread.currentThread().getName());
        System.out.println("---------------------------");
        System.out.println("speedPrintString release lock time=" + System.currentTimeMillis() + " run ThreadName=" + Thread.currentThread().getName());
    }
}
class CrossClassService {
    public void testMethod1(MyObject object) {
        synchronized (object) {
            try {
                System.out.println("testMethod1 get lock time=" + System.currentTimeMillis() + " run ThreadName=" + Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("testMethod1 release lock time=" + System.currentTimeMillis() + " run ThreadName=" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
