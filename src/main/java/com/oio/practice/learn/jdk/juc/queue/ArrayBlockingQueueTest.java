package com.oio.practice.learn.jdk.juc.queue;

import java.time.Instant;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 测试ArrayBlockingQueue
 * 模拟产生16个日志对象，需要运行16s才能打印完这些日志
 * 使用4个线程分头打印，那样就需要4s打印完。
 * @Author: LiQiongchao
 * @Date: 2020/4/25 22:43
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        final BlockingQueue<String> queue = new ArrayBlockingQueue<>(16);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        String log = queue.take();
                        parseLog(log);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 16; i++) {
            String log = (i +1) + " --> ";
            try {
                queue.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseLog(String log) {
        System.out.println(log + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
