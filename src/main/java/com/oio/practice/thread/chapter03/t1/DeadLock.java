package com.oio.practice.thread.chapter03.t1;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 死锁现象
 * 消费唤醒的是消费，所以生产一起在wait()，最后都进入wait()状态了。
 * 解决死锁：把notify()全部改成notifyAll()即可。
 * @author Liqc
 * @date 2020/3/31 14:05
 */
public class DeadLock {
    public static void main(String[] args) {
        try {
            ProviderAndConsumer providerAndConsumer = new ProviderAndConsumer();
            for (int i = 0; i < 2; i++) {
                new Thread(() -> {while(true) providerAndConsumer.setVal();}, "p" + i).start();
                new Thread(() -> {while (true) providerAndConsumer.getVal();}, "c" + i).start();
            }
            Thread.sleep(5000);
            Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
            Thread.currentThread().getThreadGroup().enumerate(threads);
            for (int i = 0; i < threads.length; i++) {
                System.out.println(threads[i].getName() + " " + threads[i].getState());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    ...
    生产者p0 WAITING ★
    消费者c0 RUNNABLE 了
    消费者c0 WAITING ☆
    消费者c1 WAITING ☆
    main RUNNABLE
    Monitor Ctrl-Break RUNNABLE
    p0 WAITING
    c0 WAITING
    p1 WAITING
    c1 WAITING
     */

}

class ProviderAndConsumer {
    private List<String> valList = new ArrayList<>();
    private String lock = "";

    public void setVal() {
        try {
            synchronized (lock) {
                while (!valList.isEmpty()) {
                    System.out.println("生产者" + Thread.currentThread().getName() + " WAITING ★");
                    lock.wait();
                }
                System.out.println("生产者" + Thread.currentThread().getName() + " RUNNABLE 了");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                valList.add(value);
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getVal() {
        try {
            synchronized (lock) {
                while (valList.isEmpty()) {
                    System.out.println("消费者" + Thread.currentThread().getName() + " WAITING ☆");
                    lock.wait();
                }
                System.out.println("消费者" + Thread.currentThread().getName() + " RUNNABLE 了");
                valList.remove(0);
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
