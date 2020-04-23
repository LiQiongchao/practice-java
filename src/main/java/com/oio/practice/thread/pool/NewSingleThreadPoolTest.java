package com.oio.practice.thread.pool;

import javax.annotation.security.RunAs;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用线程池创建单个线程的线程池
 * @Author: LiQiongchao
 * @Date: 2020/4/23 19:34
 */
public class NewSingleThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int no = i;
            Runnable runnable = () -> {
                System.out.println("into" + no);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" end" + no);
            };
            service.submit(runnable);
        }
        service.shutdown();
        System.out.println("Thread Main end!");
    }

}
