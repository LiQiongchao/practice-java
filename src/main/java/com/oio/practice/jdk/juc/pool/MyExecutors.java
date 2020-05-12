package com.oio.practice.jdk.juc.pool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 * @Author: LiQiongchao
 * @Date: 2020/4/27 22:32
 */
public class MyExecutors {

    public static ExecutorService newMyWebThreadPool(int minSpareThreads, int maxThreads, int maxIdleTime) {
        return new ThreadPoolExecutor(minSpareThreads, maxThreads, maxIdleTime, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());
    }

}
