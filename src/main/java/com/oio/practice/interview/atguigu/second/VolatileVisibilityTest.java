package com.oio.practice.interview.atguigu.second;

import java.util.concurrent.TimeUnit;

/**
 * volatile 可见性测试
 * @Author: LiQiongchao
 * @Date: 2020/5/16 21:59
 */
public class VolatileVisibilityTest {

    public static void main(String[] args) throws InterruptedException {
        VolatileData data = new VolatileData();
        System.out.println(Thread.currentThread().getName() + ", seed: " + data.seed);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", seed: " + data.seed);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo60();
            System.out.println(Thread.currentThread().getName() + ", seed: " + data.seed);
        }, "AA").start();
        while (data.seed == 0) {}
        System.out.println(Thread.currentThread().getName() + ", seed: " + data.seed);
    }

}

class VolatileData {

//    int seed = 0;
    volatile int seed = 0;

    public void addTo60() {
        seed = 60;
    }

}
