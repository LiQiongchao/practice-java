package com.oio.practice.interview.atguigu.second.ch03juc.p05semaphore;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        closeDoor();

    }

   /**
    * 关门案例
    * CountDown 的应用
    *
    * @throws InterruptedException
    */
    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
//        for (int i = 1; i <= 12; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "上完自习");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // CountDownLatch 变为0时，调用唤醒主线程
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长锁门离开教室");
    }

  
} 
