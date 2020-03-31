package com.oio.practice.thread.chapter03.t1;

import java.util.ArrayList;
import java.util.List;

/**
 * 一生产一消费交替打印
 * @author Liqc
 * @date 2020/3/31 13:25
 */
public class ProviderAndConsumer1 {
    private static List product = new ArrayList(1);
    public static void main(String[] args) {
        String lock = "";
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (!product.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String value = System.currentTimeMillis() + "_" + System.nanoTime();
                    System.out.println("set value: " + value);
                    product.add(value);
                    lock.notify();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (product.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("get value: " + product.get(0));
                    product.remove(0);
                    lock.notify();
                }
            }
        }).start();
    }
    /*
    set value: 1585633220066_1742421324429300
    get value: 1585633220066_1742421324429300
    set value: 1585633220066_1742421324482500
    get value: 1585633220066_1742421324482500
    ……
     */
}

