package com.oio.practice.interview.atguigu.second.ch01volatil;

import java.util.concurrent.TimeUnit;

/**
 * volatile 测试
 *  - 可见性
 *  - 不保证原子性
 *  - 禁止指令重排
 * @Author: LiQiongchao
 * @Date: 2020/5/16 21:59
 */
public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        notAtomic();

    }

    /**
     * 无法保证原子性
     */
    private static void notAtomic() {
        VolatileData data = new VolatileData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.increment();
                }
            }, i + "").start();
        }

        // 等上面的20个线程执行完，查看累加的结果。
        while (Thread.activeCount() > 2) {
            /**
             * 如果不指定线程组，会把默认的线程添加到默认的线程组，线程组会有两个默认的线程，一个是main线程，一个是GC线程
             */
            Thread.yield();
        }
        System.out.println(data.seed);
    }

    /**
     * volatile Visibility可见性测试
     */
    private static void seeOkByVolatile() {
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
        // data.seed要一直使用线程内的变量值。
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

    /**
     * ++操作不保证原子性，所以有没有volatile修饰，都保存不了原子性。
     */
    public void increment() {
        seed++;
    }
}
