package com.oio.practice.learn.jdk.juc.pool;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: LiQiongchao
 * @Date: 2020/5/4 20:20
 */
public class ExecutorsTest {

    private final static ExecutorService service = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        service.execute(() -> {
            try {
                Thread.sleep(2000L);
                System.out.println("sub end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void ThreadPoolTest(){
        System.out.println(Thread.currentThread().getName());
        service.execute(() -> {
            try {
                Thread.sleep(2000L);
                System.out.println("sub end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}