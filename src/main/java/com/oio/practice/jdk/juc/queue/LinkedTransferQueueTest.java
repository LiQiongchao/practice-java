package com.oio.practice.jdk.juc.queue;

import javax.annotation.security.RunAs;
import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedTransferQueue 测试
 * @Author: LiQiongchao
 * @Date: 2020/4/26 22:26
 */
public class LinkedTransferQueueTest {

    static LinkedTransferQueue queue = new LinkedTransferQueue();

    static Runnable provider = () -> {
        try {
            while (true) {
                if (queue.hasWaitingConsumer()) {
                    String text = " Your lucky number " + new Random().nextInt(100);
                    queue.transfer(text);
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) {
        Thread producer = new Thread(provider);
        producer.setDaemon(true);
        producer.start();

        for (int i = 0; i < 10; i++) {
            Thread consumer = new Thread(() -> {
                try {
                    System.out.println("Consumer " + Thread.currentThread().getName() + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            consumer.setDaemon(true);
            consumer.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
        Consumer Thread-1 Your lucky number 22
        Consumer Thread-2 Your lucky number 60
        Consumer Thread-3 Your lucky number 47
        Consumer Thread-4 Your lucky number 35
         */
    }
}
