package com.oio.practice.thread.chapter01.t3;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

/**
 * @author Liqc
 * @date 2019/8/12 13:01
 */
@Slf4j
public class ThreadRun {
    public static void main(String[] args) {
        Thread thread1 = new MyThread1("A");
        Thread thread2 = new MyThread1("B");
        Thread thread3 = new MyThread1("C");
        thread1.start();
        thread2.start();
        thread3.start();

        MyThread2 myThread1 = new MyThread2("A");
        MyThread2 myThread2 = new MyThread2("B");
        MyThread2 myThread3 = new MyThread2("C");
        MyThread2 myThread4 = new MyThread2("D");
        MyThread2 myThread5 = new MyThread2("E");
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        /*
        13:20:47.833 [E] count value: 4
        13:20:47.833 [A] count value: 4
        13:20:47.833 [D] count value: 4
        13:20:47.833 [B] count value: 4
        13:20:47.833 [C] count value: 4
         */
    }
}

/**
 * 数据隔离
 * 安全
 */
@NoArgsConstructor
class MyThread1 extends Thread {

    int count = 5;

    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (count > 0) {
            count --;
            System.out.println("thread name: "+ currentThread().getName()+", count value: " + count);
        }
    }
}

/**
 * 数据不隔离
 * 不安全
 */
@Slf4j
@NoArgsConstructor
class MyThread2 extends Thread {

    int count = 5;

    public MyThread2(String name) {
        super(name);
    }

    @Override
    public void run() {
        count--;
        log.info("thread name: {}, count value: {}", currentThread().getContextClassLoader(), count);
    }
}
