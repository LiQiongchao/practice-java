package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.sql.SQLOutput;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁，非公平锁测试
 * 公平锁：基本先启动的线程会优先获得锁，非公平锁则不一定。
 * @author Liqc
 * @date 2020/4/13 13:17
 */
public class FairNoFairTest {

    public static void main(String[] args) {
        // 创建公平锁
//        FairNoFairService service = new FairNoFairService(true);
        // 非公平锁
        FairNoFairService service = new FairNoFairService(false);
        Runnable runnable = () -> {
            System.out.println("*线程" + Thread.currentThread().getName() + "运行了");
            service.serviceMethod();
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
    /* 公平锁
    *线程Thread-0运行了
    *线程Thread-4运行了
    *线程Thread-5运行了
    ThreadName=Thread-4获得锁定
    *线程Thread-3运行了
    *线程Thread-1运行了
    *线程Thread-6运行了
    *线程Thread-7运行了
    *线程Thread-2运行了
    *线程Thread-8运行了
    *线程Thread-9运行了
    ThreadName=Thread-5获得锁定
    ThreadName=Thread-0获得锁定
    ThreadName=Thread-3获得锁定
    ThreadName=Thread-1获得锁定
    ThreadName=Thread-6获得锁定
    ThreadName=Thread-7获得锁定
    ThreadName=Thread-2获得锁定
    ThreadName=Thread-8获得锁定
    ThreadName=Thread-9获得锁定
     */

    /* 非公平锁
    *线程Thread-0运行了
    *线程Thread-3运行了
    *线程Thread-6运行了
    *线程Thread-2运行了
    *线程Thread-7运行了
    *线程Thread-1运行了
    ThreadName=Thread-0获得锁定
    *线程Thread-4运行了
    ThreadName=Thread-4获得锁定
    *线程Thread-5运行了
    ThreadName=Thread-5获得锁定
    *线程Thread-8运行了
    ThreadName=Thread-8获得锁定
    *线程Thread-9运行了
    ThreadName=Thread-9获得锁定
    ThreadName=Thread-3获得锁定
    ThreadName=Thread-6获得锁定
    ThreadName=Thread-2获得锁定
    ThreadName=Thread-7获得锁定
    ThreadName=Thread-1获得锁定
     */
}

@Data
class FairNoFairService {
    private ReentrantLock lock;

    public FairNoFairService(Boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "获得锁定");
        } finally {
            lock.unlock();
        }
    }

}
