package com.oio.practice.jdk.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NewFixedThreadPool 测试
 * @Author: LiQiongchao
 * @Date: 2020/4/27 21:44
 */
public class NewFixedThreadPoolTest {

    public static void main(String[] args) {
        // 一次只能执行5个任务。
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            final int no = i;
            service.execute(() -> {
                try {
                    System.out.println("into " + no);
                    Thread.sleep(1000L);
                    System.out.println(" end " + no);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        System.out.println("Thread Main End!");
    }
}
