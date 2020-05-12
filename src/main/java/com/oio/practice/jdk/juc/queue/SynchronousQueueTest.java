package com.oio.practice.jdk.juc.queue;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 测试 SynchronousQueue
 * 模拟10个消息者来消费数据，一次只能消费一个，要保存有序
 * @Author: LiQiongchao
 * @Date: 2020/4/26 0:20
 */
public class SynchronousQueueTest {

    public static void main(String[] args) {
        System.out.println("begin: " + System.currentTimeMillis());
        final SynchronousQueue<String> queue = new SynchronousQueue();

        // 定义一个信号量, 相当于一个互斥锁
        final Semaphore sem = new Semaphore(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    sem.acquire();
                    String input = queue.take();
                    String output = TestDo.doSome(input);
                    System.out.println(Thread.currentThread().getName() + ":" + output);
                    sem.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            String input = i + "";
            try {
                // 此方法不能移到take上面去，因为put后没有take就会一直阻塞main线程会导致后面的take线程无法启动。
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
/*
begin: 1587832204437
Thread-0:0:1587832205
Thread-1:1:1587832206
Thread-2:2:1587832207
Thread-4:3:1587832208
Thread-5:4:1587832209
Thread-6:5:1587832210
Thread-3:6:1587832211
Thread-7:7:1587832212
Thread-8:8:1587832213
Thread-9:9:1587832214
 */
}
class TestDo {
    public static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return output;
    }
}
