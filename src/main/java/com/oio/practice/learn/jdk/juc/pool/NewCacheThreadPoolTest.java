package com.oio.practice.learn.jdk.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newCachedThreadPool() 测试
 * @Author: LiQiongchao
 * @Date: 2020/4/27 21:36
 */
public class NewCacheThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
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
        System.out.println("Thread main end");
    }
}
