package com.oio.practice.thread.chapter02.t2.synchronize;

/**
 * synchronized 加在方法上，默认使用的是对象的实例锁，有几个实例对象就有几个锁
 * 同步单词为synchronized, 异步单词为asynchronized
 * @author Liqc
 * @date 2019/12/3 13:01
 */
public class TwoObjectTwoLockTest {
    public static void main(String[] args) {
        SumNumberTest sumNumberTest = new SumNumberTest();
        SumNumberTest sumNumberTest2 = new SumNumberTest();
        new Thread(() -> {
            sumNumberTest.addI("a");
        }).start();
        new Thread(() -> {
            sumNumberTest2.addI("b");
        }).start();
    }
    /*
    a set over!
    b set over!
    b num = 200
    a num = 100
     */
}

class SumNumberTest {
    private int num = 0;
    synchronized public void addI(String username) {
        try {
            if ("a".equals(username)) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
