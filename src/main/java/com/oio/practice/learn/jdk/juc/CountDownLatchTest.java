package com.oio.practice.learn.jdk.juc;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 测试
 * @Author: LiQiongchao
 * @Date: 2020/4/26 22:53
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        try {
            // 声明一个有3个倒计数的CountDownLatch
            CountDownLatch countDownLatch = new CountDownLatch(3);
            Worker worker1 = new Worker("worker1", countDownLatch);
            Worker worker2 = new Worker("worker2", countDownLatch);
            Worker worker3 = new Worker("worker3", countDownLatch);
            worker1.start();
            worker2.start();
            worker3.start();
            countDownLatch.await();
            System.out.println("All staff have finished their work.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        ...
        worker2 end work.
        worker1 end work.
        All staff have finished their work.
         */
    }
}

@AllArgsConstructor
class Worker extends Thread {
    private String name;
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        try {
            System.out.println(name + " begin work.");
            Thread.sleep(1000);
            System.out.println(name + " end work.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 减少一次计数
        countDownLatch.countDown();
    }
}